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
import eu.tkacas.jslearner.domain.Result

class RoadMapViewModel(
    authRepository: AuthRepository,
    private val getRoadMapUseCase: GetRoadMapUseCase
) : ViewModel() {

    val user = authRepository.currentUser

    private val _uiState = MutableStateFlow<Result<List<RoadMapNodeState>>>(Result.Loading)
    val uiState: StateFlow<Result<List<RoadMapNodeState>>> = _uiState.asStateFlow()

    init {
        loadRoadMapNodes()
    }

    fun loadRoadMapNodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = Result.Loading
                val nodes = user?.let { getRoadMapUseCase.execute(it.uid) }
                _uiState.value = Result.Success(nodes!!)
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}