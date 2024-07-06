package eu.tkacas.jslearner.data.model

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel

data class UserFirestore(
    val firstName: String? = null,
    val lastName: String? = null,
    val dateRegistered: String? = null,
    val reasonOfUsingTheApp: String? = null,
    val profileCompleted: Boolean? = false,
    val coursesCompleted: List<Lesson>? = null
)

data class UserFirebase(
    val experienceLevel: ExperienceLevel? = null,
    val experienceScore: Int? = null,
    val currentCourseId: String? = null,
    val currentLessonId: String? = null,
    val highScoreDaysInARow: Int? = null,
    val highScoreCorrectAnswersInARow: Int? = null
)