package com.example.tareasestudio.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.ui.composable.BarraSuperior
import com.example.tareasestudio.ui.composable.DialogoEliminar
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.section.AccionesTareaSection
import com.example.tareasestudio.ui.section.InfoTareaSection
import com.example.tareasestudio.ui.theme.FondoApp
import com.example.tareasestudio.ui.viewModel.TareaViewModel

@Composable
fun DetalleTareaScreen(
    id: String,
    viewModel: TareaViewModel,
    onVolver: () -> Unit,
    onEditar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val tarea = uiState.tareas.find { it.id == id }
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoApp)
    ) {
        BarraSuperior(titulo = "Detalle", onVolver = onVolver)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            if (tarea != null) {
                InfoTareaSection(tarea = tarea)
                Spacer(modifier = Modifier.height(24.dp))
                AccionesTareaSection(
                    completada = tarea.completada,
                    onCambiarEstado = { viewModel.cambiarEstado(tarea) },
                    onEditar = onEditar,
                    onEliminar = { mostrarDialogo = true }
                )
            } else {
                Parrafo(texto = "Tarea no encontrada.")
            }
        }
    }

    if (mostrarDialogo && tarea != null) {
        DialogoEliminar(
            nombreTarea = tarea.titulo,
            onConfirmar = {
                mostrarDialogo = false
                viewModel.eliminarTarea(tarea.id)
                onVolver()
            },
            onCancelar = { mostrarDialogo = false }
        )
    }
}