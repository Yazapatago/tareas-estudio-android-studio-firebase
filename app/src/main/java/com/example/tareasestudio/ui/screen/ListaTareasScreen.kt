package com.example.tareasestudio.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.ui.composable.BarraSuperior
import com.example.tareasestudio.ui.composable.MensajeEstado
import com.example.tareasestudio.ui.section.EncabezadoListaSection
import com.example.tareasestudio.ui.section.ListaTareasSection
import com.example.tareasestudio.ui.theme.FondoApp
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.RojoClaro
import com.example.tareasestudio.ui.theme.RojoPrincipal
import com.example.tareasestudio.ui.theme.VerdeClaro
import com.example.tareasestudio.ui.theme.VerdePrincipal
import com.example.tareasestudio.ui.viewModel.TareaViewModel

@Composable
fun ListaTareasScreen(
    viewModel: TareaViewModel,
    onVolver: () -> Unit,
    onVerDetalle: (String) -> Unit,
    onNuevaTarea: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val completadas = uiState.tareas.count { it.completada }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoApp)
    ) {
        BarraSuperior(titulo = "Mis tareas", onVolver = onVolver)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            EncabezadoListaSection(
                totalPendientes = uiState.tareas.size - completadas,
                totalCompletadas = completadas,
                sincronizando = uiState.isSyncing,
                onNuevaTarea = onNuevaTarea,
                onSincronizar = { viewModel.sincronizar() }
            )
            Spacer(modifier = Modifier.height(16.dp))

            uiState.successMessage?.let { mensaje ->
                MensajeEstado(texto = mensaje, colorTexto = VerdePrincipal, colorFondo = VerdeClaro)
                Spacer(modifier = Modifier.height(16.dp))
            }
            uiState.errorMessage?.let { mensaje ->
                MensajeEstado(texto = mensaje, colorTexto = RojoPrincipal, colorFondo = RojoClaro)
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = MoradoPrincipal,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                ListaTareasSection(
                    tareas = uiState.tareas,
                    onCambiarEstado = { tarea -> viewModel.cambiarEstado(tarea) },
                    onVerDetalle = onVerDetalle
                )
            }
        }
    }
}