package com.example.tareasestudio.domain.useCase

import com.example.tareasestudio.domain.model.Prioridad
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.domain.repository.TareaRepository
import java.time.LocalDate
import javax.inject.Inject

// Sirve para crear y para editar. Aquí viven las reglas de validación.
class GuardarTareaUseCase @Inject constructor(
    private val repository: TareaRepository
) {
    suspend operator fun invoke(tarea: TareaModel) {
        require(tarea.titulo.isNotBlank()) { "El título es obligatorio" }
        require(tarea.titulo.length <= 80) { "El título no puede superar 80 caracteres" }
        require(tarea.materia.isNotBlank()) { "La materia es obligatoria" }
        require(tarea.materia.length <= 60) { "La materia no puede superar 60 caracteres" }
        require(tarea.descripcion.length <= 500) { "La descripción no puede superar 500 caracteres" }
        require(tarea.prioridad in Prioridad.todas) { "Prioridad no válida" }
        require(esFechaValida(tarea.fechaEntrega)) { "La fecha debe tener el formato AAAA-MM-DD" }
        repository.guardar(tarea)
    }

    private fun esFechaValida(fecha: String): Boolean =
        runCatching { LocalDate.parse(fecha) }.isSuccess
}