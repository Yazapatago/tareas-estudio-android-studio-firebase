package com.example.tareasestudio.domain.model

// Modelo del dominio: no sabe nada de Room ni de Firebase.
data class TareaModel(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val materia: String,
    val fechaEntrega: String,   // Formato AAAA-MM-DD
    val prioridad: String,      // Alta, Media o Baja
    val completada: Boolean = false,
    val updatedAt: Long = 0L
)

object Prioridad {
    const val ALTA = "Alta"
    const val MEDIA = "Media"
    const val BAJA = "Baja"
    val todas = listOf(ALTA, MEDIA, BAJA)
}