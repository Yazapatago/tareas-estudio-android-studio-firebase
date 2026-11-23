package com.example.tareasestudio.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.ui.composable.BotonPrincipal
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.VerdePrincipal

@Composable
fun EncabezadoListaSection(
    totalPendientes: Int,
    totalCompletadas: Int,
    sincronizando: Boolean,
    onNuevaTarea: () -> Unit,
    onSincronizar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Parrafo(texto = "Pendientes: $totalPendientes  ·  Completadas: $totalCompletadas")
        BotonPrincipal(
            texto = "Nueva tarea",
            color = MoradoPrincipal,
            icono = Icons.Filled.Add,
            onClick = onNuevaTarea
        )
        BotonPrincipal(
            texto = if (sincronizando) "Sincronizando..." else "Sincronizar con Firebase",
            color = VerdePrincipal,
            icono = Icons.Filled.Sync,
            habilitado = !sincronizando,
            onClick = onSincronizar
        )
    }
}