package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.repository.AuthRepository

class GetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): User {
        val userFirebase = authRepository.getUserStats()
        return User(
            experienceLevel = userFirebase.experienceLevel,
            experienceScore = userFirebase.experienceScore?.toInt(),
            currentCourseId = userFirebase.currentCourseId,
            currentLessonId = userFirebase.currentLessonId,
            highScoreDaysInARow = userFirebase.highScoreDaysInARow?.toInt(),
            highScoreCorrectAnswersInARow = userFirebase.highScoreCorrectAnswersInARow?.toInt()
        )
    }
}