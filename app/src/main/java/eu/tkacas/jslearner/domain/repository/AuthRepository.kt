package eu.tkacas.jslearner.domain.repository

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.LeaderboardUser
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.data.model.UserFirestore
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(
        email: String,
        password: String
    ): FirebaseUser

    suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser

    suspend fun setUserProfile(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<String>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )

    suspend fun getUserProfile(): UserFirestore
    suspend fun updateUserProfile(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<String>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )

    suspend fun setUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )

    suspend fun getUserStats(): UserFirebase
    suspend fun updateUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    )

    suspend fun checkUserProfileCompletion(): Boolean
    fun logout()
    suspend fun getUserCompletedLessons(): List<String>

    suspend fun getLeaderboard(): List<LeaderboardUser>

    suspend fun setUserScore(score: Int)

    suspend fun setCompletedLesson(lessonId: String)
}