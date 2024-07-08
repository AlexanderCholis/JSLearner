package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.Result

class GetUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Result<UserFirebase> {
        return authRepository.getUserStats()
    }
}