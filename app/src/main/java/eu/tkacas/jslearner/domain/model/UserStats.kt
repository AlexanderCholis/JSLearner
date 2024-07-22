package eu.tkacas.jslearner.domain.model


data class UserStats(
    val experienceScore: Int?,
    val currentCourseId: String?,
    val currentLessonId: String?,
    val highScoreDaysInARow: Int?,
    val highScoreCorrectAnswersInARow: Int?
)