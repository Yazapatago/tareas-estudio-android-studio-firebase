package com.example.tareasestudio.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tareasestudio.domain.model.TareaModel
import com.example.tareasestudio.domain.useCase.CambiarEstadoTareaUseCase
import com.example.tareasestudio.domain.useCase.EliminarTareaUseCase
import com.example.tareasestudio.domain.useCase.GuardarTareaUseCase
import com.example.tareasestudio.domain.useCase.ObtenerTareasUseCase
import com.example.tareasestudio.domain.useCase.SincronizarTareasUseCase
import com.example.tareasestudio.ui.state.TareaUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val obtenerTareasUseCase: ObtenerTareasUseCase,
    private val guardarTareaUseCase: GuardarTareaUseCase,
    private val eliminarTareaUseCase: EliminarTareaUseCase,
    private val cambiarEstadoTareaUseCase: CambiarEstadoTareaUseCase,
    private val sincronizarTareasUseCase: SincronizarTareasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TareaUIState())
    val uiState: StateFlow<TareaUIState> = _uiState.asStateFlow()

    init {
        observarTareas()
        sincronizar(mostrarMensaje = false)
    }

    // La lista se actualiza sola cada vez que Room cambia.
    private fun observarTareas() {
        viewModelScope.launch {
            obtenerTareasUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "No se pudieron cargar las tareas") }
                }
                .collect { lista ->
                    _uiState.update { it.copy(isLoading = false, tareas = lista) }
                }
        }
    }

    fun sincronizar(mostrarMensaje: Boolean = true) {
        viewModelScope.launch {
            if (mostrarMensaje) limpiarMensajes()
            _uiState.update { it.copy(isSyncing = true) }
            runCatching { sincronizarTareasUseCase() }
                .onSuccess { resultado ->
                    _uiState.update {
                        it.copy(
                            isSyncing = false,
                            successMessage = if (mostrarMensaje) {
                                "Sincronizado: ${resultado.enviadas} enviadas, ${resultado.recibidas} en la nube"
                            } else {
                                it.successMessage
                            }
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isSyncing = false,
                            errorMessage = "Sin conexión con Firebase. Tus cambios quedan guardados en el celular."
                        )
                    }
                }
        }
    }

    fun guardarTarea(tarea: TareaModel, onGuardada: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, errorMessage = null, successMessage = null) }
            runCatching { guardarTareaUseCase(tarea) }
                .onSuccess {
                    _uiState.update { it.copy(isSaving = false, successMessage = "Tarea guardada correctamente") }
                    onGuardada()
                    sincronizar(mostrarMensaje = false)
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isSaving = false, errorMessage = e.message ?: "No se pudo guardar la tarea") }
                }
        }
    }

    fun eliminarTarea(id: String) {
        viewModelScope.launch {
            runCatching { eliminarTareaUseCase(id) }
                .onSuccess {
                    _uiState.update { it.copy(successMessage = "Tarea eliminada correctamente", errorMessage = null) }
                    sincronizar(mostrarMensaje = false)
                }
                .onFailure { e ->
                    _uiState.update { it.copy(errorMessage = e.message ?: "No se pudo eliminar la tarea") }
                }
        }
    }

    fun cambiarEstado(tarea: TareaModel) {
        viewModelScope.launch {
            runCatching { cambiarEstadoTareaUseCase(tarea) }
                .onSuccess { sincronizar(mostrarMensaje = false) }
                .onFailure { e ->
                    _uiState.update { it.copy(errorMessage = e.message ?: "No se pudo actualizar la tarea") }
                }
        }
    }

    fun limpiarMensajes() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}