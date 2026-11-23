package com.example.tareasestudio.data.mapper

import com.example.tareasestudio.data.local.TareaEntity
import com.example.tareasestudio.data.remote.TareaDto

// Room <-> Firestore. "pendiente" nunca se sube a la nube.
fun TareaEntity.toDto() = TareaDto(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    materia = materia,
    fechaEntrega = fechaEntrega,
    prioridad = prioridad,
    completada = completada,
    updatedAt = updatedAt,
    eliminada = eliminada
)

fun TareaDto.toEntity() = TareaEntity(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    materia = materia,
    fechaEntrega = fechaEntrega,
    prioridad = prioridad,
    completada = completada,
    updatedAt = updatedAt,
    pendiente = false,
    eliminada = eliminada
)