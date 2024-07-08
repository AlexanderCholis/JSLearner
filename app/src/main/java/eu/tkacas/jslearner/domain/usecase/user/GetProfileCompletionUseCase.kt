package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result

class GetProfileCompletionUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Result<Boolean> {
        return try {
            authRepository.checkUserProfileCompletion()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}