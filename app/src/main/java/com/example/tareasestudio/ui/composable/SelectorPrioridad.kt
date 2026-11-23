package com.example.tareasestudio.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareasestudio.domain.model.Prioridad
import com.example.tareasestudio.ui.theme.MoradoClaro
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.TextoGris

@Composable
fun SelectorPrioridad(
    seleccionada: String,
    onSeleccionar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Prioridad", color = TextoGris, fontSize = 14.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Prioridad.todas.forEach { opcion ->
                FilterChip(
                    selected = opcion == seleccionada,
                    onClick = { onSeleccionar(opcion) },
                    label = { Text(text = opcion) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MoradoClaro,
                        selectedLabelColor = MoradoPrincipal
                    )
                )
            }
        }
    }
}