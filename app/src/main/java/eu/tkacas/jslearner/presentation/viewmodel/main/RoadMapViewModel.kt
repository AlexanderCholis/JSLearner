package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.RoadMapWithUserStats
import eu.tkacas.jslearner.domain.usecase.user.GetUserStatsUseCase

class RoadMapViewModel(
    private val getRoadMapUseCase: GetRoadMapUseCase,
    private val getUserStatsUseCase: GetUserStatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<RoadMapWithUserStats>>(Result.Loading)
    val uiState: StateFlow<Result<RoadMapWithUserStats>> = _uiState.asStateFlow()

    init {
        loadRoadMapScreen()
    }

    private fun loadRoadMapScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = Result.Loading
                val nodes = getRoadMapUseCase.execute()
                val stats = getUserStatsUseCase.execute()
                _uiState.value = Result.Success(RoadMapWithUserStats(nodes, stats))
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}