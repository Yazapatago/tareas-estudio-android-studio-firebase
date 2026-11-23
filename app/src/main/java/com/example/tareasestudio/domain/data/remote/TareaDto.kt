package com.example.tareasestudio.data.remote

// Así se ve un documento en Firestore. No tiene anotaciones de Room.
data class TareaDto(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val materia: String,
    val fechaEntrega: String,
    val prioridad: String,
    val completada: Boolean,
    val updatedAt: Long,
    val eliminada: Boolean
)