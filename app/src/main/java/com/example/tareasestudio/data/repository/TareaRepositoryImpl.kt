package com.example.tareasestudio.data.repository

import com.example.tareasestudio.data.local.TareaDao
import com.example.tareasestudio.data.mapper.toDomain
import com.example.tareasestudio.data.mapper.toDto
import com.example.tareasestudio.data.mapper.toEntity
import com.example.tareasestudio.data.remote.FirestoreTareaDataSource
import com.example.tareasestudio.domain.model.ResultadoSync
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.domain.repository.TareaRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class TareaRepositoryImpl @Inject constructor(
    private val dao: TareaDao,
    private val remote: FirestoreTareaDataSource,
    private val auth: FirebaseAuth
) : TareaRepository {

    // Evita que dos sincronizaciones corran al mismo tiempo.
    private val syncMutex = Mutex()

    // La UI siempre lee de Room.
    override fun observarTareas(): Flow<List<TareaModel>> =
        dao.observarVisibles().map { filas -> filas.map { it.toDomain() } }

    // Se guarda primero en Room (funciona sin internet) y queda como pendiente.
    override suspend fun guardar(tarea: TareaModel) {
        dao.upsert(tarea.copy(updatedAt = System.currentTimeMillis()).toEntity())
    }

    // Borrado lógico: se marca eliminada para que el cambio llegue a Firestore.
    override suspend fun eliminar(id: String) {
        val actual = dao.buscar(id) ?: return
        dao.upsert(
            actual.copy(
                eliminada = true,
                pendiente = true,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun sincronizar(): ResultadoSync = syncMutex.withLock {
        // Sin internet, Firestore se queda esperando; por eso ponemos un límite de 15 s.
        withTimeout(15_000) {
            // 1. Asegurar sesión anónima (la primera vez necesita internet).
            val uid = auth.currentUser?.uid
                ?: auth.signInAnonymously().await().user?.uid
                ?: error("No fue posible iniciar sesión anónima")

            // 2. Subir los cambios pendientes.
            var enviadas = 0
            for (tareaLocal in dao.obtenerPendientes()) {
                remote.subir(uid, tareaLocal.toDto())
                dao.marcarSincronizada(tareaLocal.id, tareaLocal.updatedAt)
                enviadas++
            }

            // 3. Descargar lo que hay en la nube.
            val enLaNube = remote.descargarDelServidor(uid)
            for (dto in enLaNube) {
                val remota = dto.toEntity()
                val tareaLocal = dao.buscar(remota.id)
                // Nunca pisar una edición local que sigue pendiente.
                if (tareaLocal?.pendiente == true) continue
                if (tareaLocal == null || remota.updatedAt > tareaLocal.updatedAt) {
                    dao.upsert(remota)
                }
            }

            ResultadoSync(
                enviadas = enviadas,
                recibidas = enLaNube.count { !it.eliminada }
            )
        }
    }
}