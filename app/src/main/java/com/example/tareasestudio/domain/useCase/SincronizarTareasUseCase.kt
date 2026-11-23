package com.example.tareasestudio.domain.useCase

import com.example.tareasestudio.domain.model.ResultadoSync
import com.example.tareasestudio.domain.repository.TareaRepository
import javax.inject.Inject

class SincronizarTareasUseCase @Inject constructor(
    private val repository: TareaRepository
) {
    suspend operator fun invoke(): ResultadoSync = repository.sincronizar()
}