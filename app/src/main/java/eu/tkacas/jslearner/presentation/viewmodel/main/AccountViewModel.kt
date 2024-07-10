package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.usecase.user.GetUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserStatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserStatsUseCase: GetUserStatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<User?>>(Result.Loading)
    val uiState: StateFlow<Result<User?>> = _uiState

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            try {
                val userProfile = getUserProfileUseCase.execute()
                val userStats = getUserStatsUseCase.execute()
                val user = mergeUsers(userProfile, userStats)
                _uiState.value = Result.Success(user)
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }

    private fun mergeUsers(userProfile: User?, userStats: User?): User? {
        if (userProfile == null && userStats == null) return null

        return User(
            firstName = userProfile?.firstName ?: userStats?.firstName,
            lastName = userProfile?.lastName ?: userStats?.lastName,
            learningReason = userProfile?.learningReason ?: userStats?.learningReason,
            lessonsCompleted = userProfile?.lessonsCompleted ?: userStats?.lessonsCompleted,
            experienceLevel = userProfile?.experienceLevel ?: userStats?.experienceLevel,
            experienceScore = userProfile?.experienceScore ?: userStats?.experienceScore,
            currentCourseId = userProfile?.currentCourseId ?: userStats?.currentCourseId,
            currentLessonId = userProfile?.currentLessonId ?: userStats?.currentLessonId,
            highScoreDaysInARow = userProfile?.highScoreDaysInARow
                ?: userStats?.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = userProfile?.highScoreCorrectAnswersInARow
                ?: userStats?.highScoreCorrectAnswersInARow
        )
    }
}