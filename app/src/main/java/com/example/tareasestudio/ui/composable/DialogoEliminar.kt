package com.example.tareasestudio.ui.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.tareasestudio.ui.theme.RojoPrincipal
import com.example.tareasestudio.ui.theme.TextoGris

@Composable
fun DialogoEliminar(
    nombreTarea: String,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(text = "Eliminar tarea") },
        text = { Text(text = "¿Seguro que deseas eliminar \"$nombreTarea\"?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text(text = "Eliminar", color = RojoPrincipal)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text(text = "Cancelar", color = TextoGris)
            }
        }
    )
}