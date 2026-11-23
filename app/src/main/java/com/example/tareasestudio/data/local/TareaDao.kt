package com.example.tareasestudio.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {

    // Room vuelve a emitir la lista cada vez que cambia la tabla.
    @Query("SELECT * FROM tareas WHERE eliminada = 0 ORDER BY completada ASC, fechaEntrega ASC")
    fun observarVisibles(): Flow<List<TareaEntity>>

    @Query("SELECT * FROM tareas WHERE id = :id LIMIT 1")
    suspend fun buscar(id: String): TareaEntity?

    @Query("SELECT * FROM tareas WHERE pendiente = 1")
    suspend fun obtenerPendientes(): List<TareaEntity>

    // Inserta si no existe, actualiza si ya existe.
    @Upsert
    suspend fun upsert(tarea: TareaEntity)

    // Solo marca como sincronizada la versión que se subió.
    @Query("UPDATE tareas SET pendiente = 0 WHERE id = :id AND updatedAt = :version")
    suspend fun marcarSincronizada(id: String, version: Long)
}