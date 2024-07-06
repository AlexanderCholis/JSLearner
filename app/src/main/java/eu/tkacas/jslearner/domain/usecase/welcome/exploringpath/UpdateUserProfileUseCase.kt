package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.repository.AuthRepository

class UpdateUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(learningReason: LearningReason, profileCompleted: Boolean, experienceLevel: ExperienceLevel) {
        authRepository.updateUserProfile(learningReason, profileCompleted, experienceLevel)
    }
}