package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.domain.repository.AuthRepository

class UpdateUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(reasonOfUsingTheApp: String, profileCompleted: Boolean) {
        authRepository.updateUserProfile(reasonOfUsingTheApp, profileCompleted)
    }
}