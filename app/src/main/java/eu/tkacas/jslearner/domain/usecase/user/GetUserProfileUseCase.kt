package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.data.model.UserFirestore
import eu.tkacas.jslearner.domain.Result

class GetUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Result<UserFirestore> {
        return authRepository.getUserProfile()
    }
}