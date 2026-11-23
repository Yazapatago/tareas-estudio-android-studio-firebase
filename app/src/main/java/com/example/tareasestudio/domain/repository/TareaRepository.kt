package com.example.tareasestudio.domain.repository

import com.example.tareasestudio.domain.model.ResultadoSync
import com.example.tareasestudio.domain.model.TareaModel
import kotlinx.coroutines.flow.Flow

// Contrato: la UI y los casos de uso solo conocen esta interfaz.
interface TareaRepository {
    fun observarTareas(): Flow<List<TareaModel>>

    suspend fun guardar(tarea: TareaModel)

    suspend fun eliminar(id: String)

    suspend fun sincronizar(): ResultadoSync
}