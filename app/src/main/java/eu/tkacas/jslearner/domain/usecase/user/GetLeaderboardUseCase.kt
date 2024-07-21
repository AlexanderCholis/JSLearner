package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.data.model.LeaderboardUser
import eu.tkacas.jslearner.domain.repository.AuthRepository

class GetLeaderboardUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): List<LeaderboardUser> {
        return authRepository.getLeaderboard()
    }
}