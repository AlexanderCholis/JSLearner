package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.repository.AuthRepository

class SetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User) {
        authRepository.setUserStats(
            experienceLevel = user.experienceLevel,
            experienceScore = user.experienceScore,
            currentCourseId = user.currentCourseId,
            currentLessonId = user.currentLessonId,
            highScoreDaysInARow = user.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = user.highScoreCorrectAnswersInARow
        )
    }
}