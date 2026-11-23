package com.example.tareasestudio.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.composable.TareaItem

@Composable
fun ListaTareasSection(
    tareas: List<TareaModel>,
    onCambiarEstado: (TareaModel) -> Unit,
    onVerDetalle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tareas.isEmpty()) {
        Parrafo(
            texto = "Aún no tienes tareas. Agrega la primera con \"Nueva tarea\".",
            modifier = modifier
        )
        return
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        tareas.forEach { tarea ->
            TareaItem(
                titulo = tarea.titulo,
                materia = tarea.materia,
                fechaEntrega = tarea.fechaEntrega,
                prioridad = tarea.prioridad,
                completada = tarea.completada,
                onCambiarEstado = { onCambiarEstado(tarea) },
                onVerDetalle = { onVerDetalle(tarea.id) }
            )
        }
    }
}