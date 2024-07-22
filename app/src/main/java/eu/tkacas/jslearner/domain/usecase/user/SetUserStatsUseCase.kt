package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.model.UserStats
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.AuthRepository

class SetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User) {
        val stats = when (user.experienceLevel) {
            ExperienceLevel.NO_EXPERIENCE -> UserStats(0, 0, 0)
            ExperienceLevel.SOME_EXPERIENCE -> UserStats(600, 0, 0)
            ExperienceLevel.A_LOT_OF_EXPERIENCE -> UserStats(1400, 0, 0)
            else -> UserStats(
                user.experienceScore,
                user.highScoreDaysInARow,
                user.highScoreCorrectAnswersInARow
            )
        }

        return authRepository.setUserStats(
            experienceLevel = user.experienceLevel,
            experienceScore = stats.experienceScore,
            highScoreDaysInARow = stats.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = stats.highScoreCorrectAnswersInARow
        )
    }
}