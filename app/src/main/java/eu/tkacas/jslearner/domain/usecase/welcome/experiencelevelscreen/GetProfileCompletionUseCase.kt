package eu.tkacas.jslearner.domain.usecase.welcome.experiencelevelscreen

import eu.tkacas.jslearner.domain.repository.AuthRepository

class GetProfileCompletionUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Boolean {
        return authRepository.checkUserProfileCompletion()
    }
}