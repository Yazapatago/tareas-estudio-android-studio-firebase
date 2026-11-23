package com.example.tareasestudio.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabla de Room. "pendiente" y "eliminada" solo sirven para sincronizar.
@Entity(tableName = "tareas")
data class TareaEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val descripcion: String,
    val materia: String,
    val fechaEntrega: String,
    val prioridad: String,
    val completada: Boolean,
    val updatedAt: Long,
    val pendiente: Boolean = true,   // true = cambio que aún no se sube a Firestore
    val eliminada: Boolean = false   // borrado lógico para que no reaparezca al descargar
)