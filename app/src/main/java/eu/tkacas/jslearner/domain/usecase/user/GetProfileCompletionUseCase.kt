package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result

class GetProfileCompletionUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Boolean {
        return authRepository.checkUserProfileCompletion()
    }
}