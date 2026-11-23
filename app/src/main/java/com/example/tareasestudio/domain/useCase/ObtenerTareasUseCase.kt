package com.example.tareasestudio.domain.useCase

import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.domain.repository.TareaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtenerTareasUseCase @Inject constructor(
    private val repository: TareaRepository
) {
    operator fun invoke(): Flow<List<TareaModel>> = repository.observarTareas()
}