package eu.tkacas.jslearner.data.model

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel

data class UserFirestore(
    val firstName: String = "",
    val lastName: String = "",
    val dateRegistered: String = "",
    val reasonOfUsingTheApp: String = "",
    val profileCompleted: Boolean = false
)

data class UserFirebase(
    val experienceLevel: ExperienceLevel = ExperienceLevel.NO_EXPERIENCE,
    val experienceScore: Int = 0,
    val currentCourseId: String = "",
    val currentLessonId: String = "",
    val highScoreDaysInARow: Int = 0,
    val highScoreCorrectAnswersInARow: Int = 0
)