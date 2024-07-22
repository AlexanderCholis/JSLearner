package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.RoadMapWithUserStats
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserStatsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RoadMapViewModel(
    private val getRoadMapUseCase: GetRoadMapUseCase,
    private val authRepository: AuthRepository,
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
                authRepository.getUserCompletedLessons().collect { completedLessons ->
                    val nodes = getRoadMapUseCase.execute(completedLessons)
                    val stats = getUserStatsUseCase.execute()
                    _uiState.value = Result.Success(RoadMapWithUserStats(nodes, stats))
                }
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}