package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository

class GetProfileCompletionUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Boolean {
        return authRepository.checkUserProfileCompletion()
    }
}