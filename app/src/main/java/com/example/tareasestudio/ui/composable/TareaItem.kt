package com.example.tareasestudio.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.TextoGris
import com.example.tareasestudio.ui.theme.TextoOscuro
import com.example.tareasestudio.ui.theme.VerdePrincipal

@Composable
fun TareaItem(
    titulo: String,
    materia: String,
    fechaEntrega: String,
    prioridad: String,
    completada: Boolean,
    onCambiarEstado: () -> Unit,
    onVerDetalle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = completada,
                onCheckedChange = { onCambiarEstado() },
                colors = CheckboxDefaults.colors(checkedColor = VerdePrincipal)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    color = if (completada) TextoGris else TextoOscuro,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    // Si está completada, el título se ve tachado.
                    textDecoration = if (completada) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(text = materia, color = TextoGris, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DatoConIcono(icono = Icons.Filled.DateRange, texto = fechaEntrega, tamano = 13.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    EtiquetaPrioridad(prioridad = prioridad)
                }
            }
            IconButton(onClick = onVerDetalle) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Ver detalle",
                    tint = MoradoPrincipal
                )
            }
        }
    }
}