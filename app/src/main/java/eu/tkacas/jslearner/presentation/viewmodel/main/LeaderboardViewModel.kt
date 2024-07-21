package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.data.model.LeaderboardUser
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.user.GetLeaderboardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val getLeaderboardUseCase: GetLeaderboardUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<LeaderboardUser>>>(Result.Loading)
    val uiState: StateFlow<Result<List<LeaderboardUser>>> = _uiState

    init {
        loadLeaderboardData()
    }

    private fun loadLeaderboardData() {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            try {
                val leaderboardUsers = getLeaderboardUseCase()
                _uiState.value = Result.Success(leaderboardUsers)
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}