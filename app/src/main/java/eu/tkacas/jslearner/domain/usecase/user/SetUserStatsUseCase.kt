package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.AuthRepository

class SetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User) {
        val stats = when (user.experienceLevel) {
            ExperienceLevel.NO_EXPERIENCE -> UserStats(0, "01", "01", 0, 0)
            ExperienceLevel.SOME_EXPERIENCE -> UserStats(500, "06", "01", 0, 0)
            ExperienceLevel.A_LOT_OF_EXPERIENCE -> UserStats(1000, "9", "01", 0, 0)
            else -> UserStats(user.experienceScore, user.currentCourseId, user.currentLessonId, user.highScoreDaysInARow, user.highScoreCorrectAnswersInARow)
        }

        return authRepository.setUserStats(
            experienceLevel = user.experienceLevel,
            experienceScore = stats.experienceScore,
            currentCourseId = stats.currentCourseId,
            currentLessonId = stats.currentLessonId,
            highScoreDaysInARow = stats.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = stats.highScoreCorrectAnswersInARow
        )
    }
    data class UserStats(
        val experienceScore: Int?,
        val currentCourseId: String?,
        val currentLessonId: String?,
        val highScoreDaysInARow: Int?,
        val highScoreCorrectAnswersInARow: Int?
    )
}