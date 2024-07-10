package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.repository.AuthRepository

class UpdateUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User) {
        return authRepository.updateUserStats(
            experienceLevel = user.experienceLevel,
            experienceScore = user.experienceScore,
            currentCourseId = user.currentCourseId,
            currentLessonId = user.currentLessonId,
            highScoreDaysInARow = user.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = user.highScoreCorrectAnswersInARow
        )
    }
}