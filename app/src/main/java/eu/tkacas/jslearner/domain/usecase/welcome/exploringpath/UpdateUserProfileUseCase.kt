package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.repository.AuthRepository

class UpdateUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<String>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        authRepository.updateUserProfile(
            firstName = firstName,
            lastName = lastName,
            experienceScore = experienceScore,
            learningReason = learningReason,
            profileCompleted = profileCompleted,
            experienceLevel = experienceLevel,
            lessonsCompleted = lessonsCompleted,
            highScoreDaysInARow = highScoreDaysInARow,
            highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow
        )
    }
}