package com.example.tareasestudio.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.ui.composable.DatoConIcono
import com.example.tareasestudio.ui.composable.EtiquetaPrioridad
import com.example.tareasestudio.ui.composable.Parrafo
import com.example.tareasestudio.ui.composable.Titulo
import com.example.tareasestudio.ui.theme.NaranjaPrincipal
import com.example.tareasestudio.ui.theme.VerdePrincipal

@Composable
fun InfoTareaSection(
    tarea: TareaModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Titulo(texto = tarea.titulo)
            Spacer(modifier = Modifier.height(8.dp))
            EtiquetaPrioridad(prioridad = tarea.prioridad)
            Spacer(modifier = Modifier.height(16.dp))
            DatoConIcono(icono = Icons.Filled.School, texto = "Materia: ${tarea.materia}")
            Spacer(modifier = Modifier.height(8.dp))
            DatoConIcono(icono = Icons.Filled.DateRange, texto = "Entrega: ${tarea.fechaEntrega}")
            Spacer(modifier = Modifier.height(8.dp))
            DatoConIcono(
                icono = if (tarea.completada) Icons.Filled.CheckCircle else Icons.Filled.Schedule,
                texto = if (tarea.completada) "Completada" else "Pendiente",
                color = if (tarea.completada) VerdePrincipal else NaranjaPrincipal
            )
            Spacer(modifier = Modifier.height(16.dp))
            Titulo(texto = "Descripción", tamano = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Parrafo(texto = tarea.descripcion.ifBlank { "Sin descripción." })
        }
    }
}