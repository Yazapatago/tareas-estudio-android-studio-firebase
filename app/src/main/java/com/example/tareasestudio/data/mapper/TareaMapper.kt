package com.example.tareasestudio.data.mapper

import com.example.tareasestudio.data.local.TareaEntity
import com.example.tareasestudio.domain.model.TareaModel

// Room <-> Dominio
fun TareaEntity.toDomain() = TareaModel(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    materia = materia,
    fechaEntrega = fechaEntrega,
    prioridad = prioridad,
    completada = completada,
    updatedAt = updatedAt
)

fun TareaModel.toEntity() = TareaEntity(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    materia = materia,
    fechaEntrega = fechaEntrega,
    prioridad = prioridad,
    completada = completada,
    updatedAt = updatedAt,
    pendiente = true,
    eliminada = false
)