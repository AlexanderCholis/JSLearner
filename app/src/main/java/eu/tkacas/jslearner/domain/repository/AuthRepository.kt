package eu.tkacas.jslearner.domain.repository

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(
        email: String,
        password: String
    ): Result<FirebaseUser>
    suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Result<FirebaseUser>
    suspend fun updateUserProfile(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<Lesson>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )
    suspend fun updateUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        currentCourseId: String?,
        currentLessonId: String?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )
    suspend fun checkUserProfileCompletion(): Boolean
    fun logout()
}