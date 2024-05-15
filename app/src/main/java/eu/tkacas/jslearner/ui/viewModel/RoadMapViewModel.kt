package eu.tkacas.jslearner.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.repositories.RoadmapRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoadMapViewModel(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    sealed class RoadMapUiState {
        object Loading : RoadMapUiState()
        data class Success(val nodes: List<RoadMapNodeState>) : RoadMapUiState()
        data class Error(val message: String) : RoadMapUiState()
    }

    private val _uiState = MutableStateFlow<RoadMapUiState>(RoadMapUiState.Loading)
    val uiState: StateFlow<RoadMapUiState> = _uiState.asStateFlow()

    init {
        loadRoadMapNodes()
    }

    private fun loadRoadMapNodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nodes = roadmapRepository.getRoadMapNodes()
                _uiState.value = RoadMapUiState.Success(nodes)
            } catch (e: Exception) {
                _uiState.value = RoadMapUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        fun provideFactory(roadmapRepository: RoadmapRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(RoadMapViewModel::class.java)) {
                    return RoadMapViewModel(roadmapRepository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
