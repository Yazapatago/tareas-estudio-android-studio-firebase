package com.example.tareasestudio.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.ui.composable.BotonPrincipal
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.RojoPrincipal
import com.example.tareasestudio.ui.theme.VerdePrincipal

@Composable
fun AccionesTareaSection(
    completada: Boolean,
    onCambiarEstado: () -> Unit,
    onEditar: () -> Unit,
    onEliminar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BotonPrincipal(
            texto = if (completada) "Marcar como pendiente" else "Marcar como completada",
            color = VerdePrincipal,
            icono = Icons.Filled.CheckCircle,
            onClick = onCambiarEstado
        )
        BotonPrincipal(texto = "Editar", color = MoradoPrincipal, icono = Icons.Filled.Edit, onClick = onEditar)
        BotonPrincipal(texto = "Eliminar", color = RojoPrincipal, icono = Icons.Filled.Delete, onClick = onEliminar)
    }
}