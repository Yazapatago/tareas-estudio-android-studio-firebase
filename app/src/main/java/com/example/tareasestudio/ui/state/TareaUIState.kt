package com.example.tareasestudio.ui.state

import com.example.tareasestudio.domain.model.TareaModel

data class TareaUIState(
    val isLoading: Boolean = true,
    val isSaving: Boolean = false,
    val isSyncing: Boolean = false,
    val tareas: List<TareaModel> = emptyList(),
    val errorMessage: String? = null,
    val successMessage: String? = null
)