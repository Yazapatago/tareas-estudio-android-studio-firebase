package com.example.tareasestudio.domain.model

// Resumen que devuelve la sincronización para mostrarlo en pantalla.
data class ResultadoSync(
    val enviadas: Int,
    val recibidas: Int
)