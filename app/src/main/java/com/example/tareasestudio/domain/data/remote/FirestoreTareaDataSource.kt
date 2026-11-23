package com.example.tareasestudio.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Única clase que conoce las rutas de Firestore: users/{uid}/tareas/{id}
class FirestoreTareaDataSource @Inject constructor(
    private val db: FirebaseFirestore
) {
    private fun tareas(uid: String) =
        db.collection("users").document(uid).collection("tareas")

    suspend fun subir(uid: String, dto: TareaDto) {
        val datos = mapOf(
            "id" to dto.id,
            "titulo" to dto.titulo,
            "descripcion" to dto.descripcion,
            "materia" to dto.materia,
            "fechaEntrega" to dto.fechaEntrega,
            "prioridad" to dto.prioridad,
            "completada" to dto.completada,
            "updatedAt" to dto.updatedAt,
            "eliminada" to dto.eliminada
        )
        // El id del documento es el mismo id de la tarea en Room.
        tareas(uid).document(dto.id).set(datos).await()
    }

    suspend fun descargarDelServidor(uid: String): List<TareaDto> =
        tareas(uid).get(Source.SERVER).await().documents.mapNotNull { doc ->
            val id = doc.getString("id") ?: return@mapNotNull null
            TareaDto(
                id = id,
                titulo = doc.getString("titulo").orEmpty(),
                descripcion = doc.getString("descripcion").orEmpty(),
                materia = doc.getString("materia").orEmpty(),
                fechaEntrega = doc.getString("fechaEntrega").orEmpty(),
                prioridad = doc.getString("prioridad") ?: "Media",
                completada = doc.getBoolean("completada") ?: false,
                updatedAt = doc.getLong("updatedAt") ?: 0L,
                eliminada = doc.getBoolean("eliminada") ?: false
            )
        }
}