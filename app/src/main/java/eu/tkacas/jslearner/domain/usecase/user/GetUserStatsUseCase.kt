package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.Result

class GetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Result<User> {
        return when (val result = authRepository.getUserStats()) {
            is Result.Success -> {
                val userFirebase = result.result
                Result.Success(User(
                    experienceLevel = userFirebase.experienceLevel,
                    experienceScore = userFirebase.experienceScore,
                    currentCourseId = userFirebase.currentCourseId,
                    currentLessonId = userFirebase.currentLessonId,
                    highScoreDaysInARow = userFirebase.highScoreDaysInARow,
                    highScoreCorrectAnswersInARow = userFirebase.highScoreCorrectAnswersInARow
                ))
            }
            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading // TODO
        }
    }
}