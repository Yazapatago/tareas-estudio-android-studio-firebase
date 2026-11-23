package com.example.tareasestudio.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareasestudio.ui.composable.BotonPrincipal
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.composable.Titulo
import com.example.tareasestudio.ui.theme.MoradoClaro
import com.example.tareasestudio.ui.theme.MoradoPrincipal

@Composable
fun BienvenidaSection(
    onVerTareas: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .background(MoradoClaro, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = null,
                    tint = MoradoPrincipal,
                    modifier = Modifier.size(90.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Titulo(
                texto = "Tareas de Estudio",
                tamano = 28.sp,
                alineacion = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Parrafo(
                texto = "Organiza tus pendientes por materia y fecha de entrega. " +
                        "Se guardan en tu celular y se sincronizan con Firebase.",
                alineacion = TextAlign.Center
            )
        }
        BotonPrincipal(
            texto = "Ver mis tareas",
            color = MoradoPrincipal,
            icono = Icons.AutoMirrored.Filled.List,
            onClick = onVerTareas
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}