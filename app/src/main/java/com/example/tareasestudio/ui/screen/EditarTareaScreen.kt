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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.ui.composable.BarraSuperior
import com.example.tareasestudio.ui.composable.MensajeEstado
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.section.FormularioTareaSection
import com.example.tareasestudio.ui.theme.FondoApp
import com.example.tareasestudio.ui.theme.RojoClaro
import com.example.tareasestudio.ui.theme.RojoPrincipal
import com.example.tareasestudio.ui.viewModel.TareaViewModel

@Composable
fun EditarTareaScreen(
    id: String,
    viewModel: TareaViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val tarea = uiState.tareas.find { it.id == id }

    LaunchedEffect(Unit) { viewModel.limpiarMensajes() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoApp)
    ) {
        BarraSuperior(titulo = "Editar tarea", onVolver = onVolver)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            uiState.errorMessage?.let { mensaje ->
                MensajeEstado(texto = mensaje, colorTexto = RojoPrincipal, colorFondo = RojoClaro)
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (tarea != null) {
                FormularioTareaSection(
                    tarea = tarea,
                    textoBoton = "Actualizar",
                    guardando = uiState.isSaving,
                    onGuardar = { tareaEditada -> viewModel.guardarTarea(tareaEditada, onGuardada = onVolver) },
                    onCancelar = onVolver
                )
            } else {
                Parrafo(texto = "Tarea no encontrada.")
            }
        }
    }
}