package com.example.tareasestudio.domain.useCase

import com.example.tareasestudio.domain.repository.TareaRepository
import javax.inject.Inject

class EliminarTareaUseCase @Inject constructor(
    private val repository: TareaRepository
) {
    suspend operator fun invoke(id: String) = repository.eliminar(id)
}