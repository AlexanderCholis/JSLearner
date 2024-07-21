package eu.tkacas.jslearner.domain.model

data class LeaderboardCardData(
    val firstName: String,
    val lastName: String,
    val userScore: Int,
    val position: Int
)