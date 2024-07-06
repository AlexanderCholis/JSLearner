package eu.tkacas.jslearner.data.model

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import kotlin.collections.mapOf

data class UserFirestore(
    val firstName: String? = null,
    val lastName: String? = null,
    val dateRegistered: String? = null,
    val learningReason: LearningReason? = null,
    val profileCompleted: Boolean? = false,
    val coursesCompleted: List<Lesson>? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "first_name" to firstName,
            "last_name" to lastName,
            "date_registered" to dateRegistered,
            "learning_reason" to learningReason?.name,
            "profile_completed" to profileCompleted,
            "courses_completed" to coursesCompleted?.map { it.id }
        )
    }
}

data class UserFirebase(
    val experienceLevel: ExperienceLevel? = null,
    val experienceScore: Int? = null,
    val currentCourseId: String? = null,
    val currentLessonId: String? = null,
    val highScoreDaysInARow: Int? = null,
    val highScoreCorrectAnswersInARow: Int? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "experience_level" to experienceLevel?.name,
            "experience_score" to experienceScore,
            "current_course_id" to currentCourseId,
            "current_lesson_id" to currentLessonId,
            "high_score_days_in_a_row" to highScoreDaysInARow,
            "high_score_correct_answers_in_a_row" to highScoreCorrectAnswersInARow
        )
    }
}