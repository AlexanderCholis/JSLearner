package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.model.User

class SetUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User) {
        authRepository.setUserProfile(
            firstName = user.firstName,
            lastName = user.lastName,
            learningReason = user.learningReason,
            experienceLevel = user.experienceLevel,
            experienceScore = user.experienceScore,
            highScoreDaysInARow = user.highScoreDaysInARow,
            highScoreCorrectAnswersInARow = user.highScoreCorrectAnswersInARow,
            lessonsCompleted = user.lessonsCompleted,
            profileCompleted = user.experienceLevel != null && user.learningReason != null
        )
    }
}