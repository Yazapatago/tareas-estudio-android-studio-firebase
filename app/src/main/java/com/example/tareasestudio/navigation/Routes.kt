package com.example.tareasestudio.navigation

import kotlinx.serialization.Serializable

@Serializable
data object InicioRoute

@Serializable
data object ListaTareasRoute

@Serializable
data class DetalleTareaRoute(val id: String)

@Serializable
data object CrearTareaRoute

@Serializable
data class EditarTareaRoute(val id: String)