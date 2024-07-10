package eu.tkacas.jslearner.data.model

import com.google.firebase.firestore.PropertyName
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

data class UserFirestore(
    @get:PropertyName("first_name") @set:PropertyName("first_name") var firstName: String? = null,
    @get:PropertyName("last_name") @set:PropertyName("last_name") var lastName: String? = null,
    @get:PropertyName("date_registered") @set:PropertyName("date_registered") var dateRegistered: String? = null,
    @get:PropertyName("learning_reason") @set:PropertyName("learning_reason") var learningReason: LearningReason? = null,
    @get:PropertyName("profile_completed") @set:PropertyName("profile_completed") var profileCompleted: Boolean? = false,
    @get:PropertyName("lessons_completed") @set:PropertyName("lessons_completed") var lessonsCompleted: List<String>? = null // List of lesson IDs
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "first_name" to firstName,
            "last_name" to lastName,
            "date_registered" to dateRegistered,
            "learning_reason" to learningReason?.name,
            "profile_completed" to profileCompleted,
            "lessons_completed" to lessonsCompleted
        )
    }
}

data class UserFirebase(
    @PropertyName("current_lesson_id") var currentLessonId: String? = null,
    @PropertyName("current_course_id") var currentCourseId: String? = null,
    @PropertyName("high_score_correct_answers_in_a_row") var highScoreCorrectAnswersInARow: Long? = null,
    @PropertyName("high_score_days_in_a_row") var highScoreDaysInARow: Long? = null,
    @PropertyName("experience_level") var experienceLevel: ExperienceLevel? = null,
    @PropertyName("experience_score") var experienceScore: Long? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "experience_level" to experienceLevel?.name,
            "experience_score" to experienceScore,
            "current_course_id" to currentCourseId,
            "current_lesson_id" to currentLessonId,
            "high_score_days_in_a_row" to highScoreDaysInARow,
            "high_score_correct_answers_in_a_row" to highScoreCorrectAnswersInARow,
        )
    }
}
