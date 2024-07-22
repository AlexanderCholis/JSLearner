package eu.tkacas.jslearner.domain.model


data class UserStats(
    val experienceScore: Int?,
    val highScoreDaysInARow: Int?,
    val highScoreCorrectAnswersInARow: Int?
)