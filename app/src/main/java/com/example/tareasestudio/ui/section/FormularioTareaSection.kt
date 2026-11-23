package com.example.tareasestudio.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tareasestudio.domain.model.Prioridad
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.ui.composable.BotonPrincipal
import com.example.tareasestudio.ui.composable.CampoTexto
import com.example.tareasestudio.ui.composable.SelectorPrioridad
import com.example.tareasestudio.ui.theme.MoradoPrincipal
import com.example.tareasestudio.ui.theme.TextoGris
import java.util.UUID

@Composable
fun FormularioTareaSection(
    tarea: TareaModel?,
    textoBoton: String,
    guardando: Boolean,
    onGuardar: (TareaModel) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Si tarea es null es "crear"; si trae datos es "editar".
    var titulo by remember { mutableStateOf(tarea?.titulo ?: "") }
    var descripcion by remember { mutableStateOf(tarea?.descripcion ?: "") }
    var materia by remember { mutableStateOf(tarea?.materia ?: "") }
    var fechaEntrega by remember { mutableStateOf(tarea?.fechaEntrega ?: "") }
    var prioridad by remember { mutableStateOf(tarea?.prioridad ?: Prioridad.MEDIA) }

    val formularioValido = titulo.isNotBlank() && materia.isNotBlank() && fechaEntrega.isNotBlank()

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CampoTexto(valor = titulo, onValorChange = { titulo = it }, etiqueta = "Título")
            CampoTexto(
                valor = descripcion,
                onValorChange = { descripcion = it },
                etiqueta = "Descripción",
                lineasMinimas = 3
            )
            CampoTexto(valor = materia, onValorChange = { materia = it }, etiqueta = "Materia")
            CampoTexto(
                valor = fechaEntrega,
                onValorChange = { fechaEntrega = it },
                etiqueta = "Fecha de entrega (AAAA-MM-DD)"
            )
            SelectorPrioridad(seleccionada = prioridad, onSeleccionar = { prioridad = it })
            BotonPrincipal(
                texto = if (guardando) "Guardando..." else textoBoton,
                color = MoradoPrincipal,
                icono = Icons.Filled.Check,
                habilitado = formularioValido && !guardando,
                onClick = {
                    onGuardar(
                        TareaModel(
                            id = tarea?.id ?: UUID.randomUUID().toString(),
                            titulo = titulo.trim(),
                            descripcion = descripcion.trim(),
                            materia = materia.trim(),
                            fechaEntrega = fechaEntrega.trim(),
                            prioridad = prioridad,
                            completada = tarea?.completada ?: false,
                            updatedAt = tarea?.updatedAt ?: 0L
                        )
                    )
                }
            )
            BotonPrincipal(texto = "Cancelar", color = TextoGris, onClick = onCancelar)
        }
    }
}