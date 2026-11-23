package com.example.tareasestudio.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareasestudio.domain.model.Prioridad
import com.example.tareasestudio.ui.theme.NaranjaClaro
import com.example.tareasestudio.ui.theme.NaranjaPrincipal
import com.example.tareasestudio.ui.theme.RojoClaro
import com.example.tareasestudio.ui.theme.RojoPrincipal
import com.example.tareasestudio.ui.theme.VerdeClaro
import com.example.tareasestudio.ui.theme.VerdePrincipal

// Etiqueta de color según la prioridad: Alta = rojo, Media = naranja, Baja = verde.
@Composable
fun EtiquetaPrioridad(
    prioridad: String,
    modifier: Modifier = Modifier
) {
    val (colorTexto, colorFondo) = when (prioridad) {
        Prioridad.ALTA -> RojoPrincipal to RojoClaro
        Prioridad.BAJA -> VerdePrincipal to VerdeClaro
        else -> NaranjaPrincipal to NaranjaClaro
    }
    Text(
        text = prioridad,
        modifier = modifier
            .background(colorFondo, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        color = colorTexto,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    )
}