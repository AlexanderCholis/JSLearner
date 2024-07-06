package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.repository.AuthRepository

class UpdateUserStatsUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: UserFirebase) {
        authRepository.updateUserStats(user)
    }
}