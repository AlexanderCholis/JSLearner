package eu.tkacas.jslearner.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.data.model.UserFirestore
import eu.tkacas.jslearner.data.source.remote.FirebaseDataSource
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

class AuthRepositoryImpl (
    private val firebaseDataSource: FirebaseDataSource,
    private val firestoreDataSource: FirestoreDataSource
): AuthRepository {
    override val currentUser
        get() = firebaseDataSource.currentUser

    override suspend fun login(email: String, password: String) = firebaseDataSource.login(email, password)

    override suspend fun signup(firstName: String, lastName: String, email: String, password: String): Result<FirebaseUser> {
        val result = firebaseDataSource.signup(email, password)
        if (result is Result.Success) {
            val uid = result.result.uid
            val user = UserFirestore(firstName, lastName, dateRegistered = getCurrentDate(), profileCompleted = false)
            firestoreDataSource.setUserProfile(uid, user)
        }
        return result
    }

    override suspend fun setUserProfile(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<String>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        try {
            val uid = currentUser?.uid ?: return
            // Update the user profile in Firestore
            val user = UserFirestore(
                firstName = firstName,
                lastName = lastName,
                learningReason = learningReason,
                profileCompleted = profileCompleted,
                lessonsCompleted = lessonsCompleted)
            firestoreDataSource.setUserProfile(uid, user)
            // Update the user stats in Firebase
            val userStats = UserFirebase(
                    experienceLevel = experienceLevel,
                    experienceScore = experienceScore,
                    currentCourseId = lessonsCompleted?.firstOrNull()?.toString(),
                    currentLessonId = lessonsCompleted?.firstOrNull()?.toString(),
                    highScoreDaysInARow = highScoreDaysInARow,
                    highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow,
                )
            firebaseDataSource.setUserStats(uid, userStats)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user profile.", e)
        }
    }

    override suspend fun getUserProfile(): Result<UserFirestore> {
        try {
            val uid = currentUser?.uid ?: return Result.Error(Exception("User not logged in."))
            return Result.Success(firestoreDataSource.getUserProfile(uid)!!)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error getting user profile.", e)
            return Result.Error(e)
        }
    }

    override suspend fun updateUserProfile(
        firstName: String?,
        lastName: String?,
        experienceScore: Int?,
        learningReason: LearningReason?,
        profileCompleted: Boolean?,
        experienceLevel: ExperienceLevel?,
        lessonsCompleted: List<String>?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        try {
            val uid = currentUser?.uid ?: return
            val user = UserFirestore(
                firstName = firstName,
                lastName = lastName,
                learningReason = learningReason,
                profileCompleted = profileCompleted,
                lessonsCompleted = lessonsCompleted)
            firestoreDataSource.updateUserProfile(uid, user)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user profile.", e)
        }
    }

    override suspend fun setUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        currentCourseId: String?,
        currentLessonId: String?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        try {
            val uid = currentUser?.uid ?: return
            val userStats = UserFirebase(
                experienceLevel = experienceLevel,
                experienceScore = experienceScore,
                currentCourseId = currentCourseId,
                currentLessonId = currentLessonId,
                highScoreDaysInARow = highScoreDaysInARow,
                highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow
            )
            firebaseDataSource.setUserStats(uid, userStats)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user stats.", e)
        }
    }

    override suspend fun getUserStats(): Result<UserFirebase> {
        try {
            val uid = currentUser?.uid ?: return Result.Error(Exception("User not logged in."))
            return Result.Success(firebaseDataSource.getUserStats(uid)!!)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error getting user stats.", e)
            return Result.Error(e)
        }
    }

    override suspend fun updateUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        currentCourseId: String?,
        currentLessonId: String?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        try {
            val uid = currentUser?.uid ?: return
            val userStats = UserFirebase(
                experienceLevel = experienceLevel,
                experienceScore = experienceScore,
                currentCourseId = currentCourseId,
                currentLessonId = currentLessonId,
                highScoreDaysInARow = highScoreDaysInARow,
                highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow
            )
            firebaseDataSource.updateUserStats(uid, userStats)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user stats.", e)
        }
    }

    override suspend fun checkUserProfileCompletion(): Boolean {
        val uid = currentUser?.uid ?: return false
        val profileCompletion = firestoreDataSource.checkIfProfileCompleted(uid)
        return profileCompletion
    }

    private fun getCurrentDate(): String {
        return System.currentTimeMillis().toString()
    }
    override fun logout() = firebaseDataSource.logout()
}