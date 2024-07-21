package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository

class SetUserScoreUseCase (
    private val authRepository: AuthRepository,
    private val getUserStatsUseCase: GetUserStatsUseCase
) {
    suspend fun execute(score: Int) {
        val userStats = getUserStatsUseCase.execute()
        val newScore = userStats.experienceScore!!.toInt() + score
        authRepository.setUserScore(newScore)
    }
}