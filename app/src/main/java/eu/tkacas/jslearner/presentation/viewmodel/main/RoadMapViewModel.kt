package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoadMapViewModel(
    authRepository: AuthRepository,
    private val getRoadMapUseCase: GetRoadMapUseCase
) : ViewModel() {

    val user = authRepository.currentUser

    sealed class RoadMapUiState { //TODO: Move to separate file
        object Loading : RoadMapUiState()
        data class Success(val nodes: List<RoadMapNodeState>) : RoadMapUiState()
        data class Error(val message: String) : RoadMapUiState()
    }

    private val _uiState = MutableStateFlow<RoadMapUiState>(RoadMapUiState.Loading)
    val uiState: StateFlow<RoadMapUiState> = _uiState.asStateFlow()

    init {
        loadRoadMapNodes()
    }

    fun loadRoadMapNodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nodes = user?.let { getRoadMapUseCase.execute(it.uid) }
                _uiState.value = nodes?.let { RoadMapUiState.Success(it) }!!
            } catch (e: Exception) {
                _uiState.value = RoadMapUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}