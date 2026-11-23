package com.example.tareasestudio.domain.useCase

import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.domain.repository.TareaRepository
import javax.inject.Inject

// Marca una tarea como completada o la vuelve a dejar pendiente.
class CambiarEstadoTareaUseCase @Inject constructor(
    private val repository: TareaRepository
) {
    suspend operator fun invoke(tarea: TareaModel) =
        repository.guardar(tarea.copy(completada = !tarea.completada))
}