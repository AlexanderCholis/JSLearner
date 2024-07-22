package eu.tkacas.jslearner.domain.model

import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

data class User(
    // Firestore
    val firstName: String? = null,
    val lastName: String? = null,
    val learningReason: LearningReason? = null,
    val lessonsCompleted: List<String>? = null,
    // Firebase
    val experienceLevel: ExperienceLevel? = null,
    val experienceScore: Int? = null,
    val highScoreDaysInARow: Int? = null,
    val highScoreCorrectAnswersInARow: Int? = null
)