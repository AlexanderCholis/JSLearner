package eu.tkacas.jslearner.data.repository

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.LeaderboardUser
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.data.model.UserFirestore
import eu.tkacas.jslearner.data.source.remote.FirebaseDataSource
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val firestoreDataSource: FirestoreDataSource
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseDataSource.currentUser

    override suspend fun login(email: String, password: String): FirebaseUser {
        return firebaseDataSource.login(email, password)
    }

    override suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser {
        val user = firebaseDataSource.signup(email, password)
        val uid = user.uid
        val userProfile = UserFirestore(
            firstName,
            lastName,
            dateRegistered = getCurrentDate(),
            profileCompleted = false
        )
        firestoreDataSource.setUserProfile(uid, userProfile)
        return user
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
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        val user = UserFirestore(
            firstName = firstName,
            lastName = lastName,
            learningReason = learningReason,
            profileCompleted = profileCompleted,
            lessonsCompleted = lessonsCompleted
        )
        firestoreDataSource.setUserProfile(uid, user)
    }

    override suspend fun getUserProfile(): UserFirestore {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        return firestoreDataSource.getUserProfile(uid) ?: throw Exception("User profile not found.")
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
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        val user = UserFirestore(
            firstName = firstName,
            lastName = lastName,
            learningReason = learningReason,
            profileCompleted = profileCompleted,
            lessonsCompleted = lessonsCompleted,
        )
        firestoreDataSource.updateUserProfile(uid, user)
    }

    override suspend fun setUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        val userStats = UserFirebase(
            experienceLevel = experienceLevel,
            experienceScore = experienceScore?.toLong(),
            highScoreDaysInARow = highScoreDaysInARow?.toLong(),
            highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow?.toLong()
        )
        firebaseDataSource.setUserStats(uid, userStats)
    }

    override suspend fun getUserStats(): UserFirebase {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        return firebaseDataSource.getUserStats(uid) ?: throw Exception("User stats not found.")
    }

    override suspend fun updateUserStats(
        experienceLevel: ExperienceLevel?,
        experienceScore: Int?,
        highScoreDaysInARow: Int?,
        highScoreCorrectAnswersInARow: Int?
    ) {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        val userStats = UserFirebase(
            experienceLevel = experienceLevel,
            experienceScore = experienceScore?.toLong(),
            highScoreDaysInARow = highScoreDaysInARow?.toLong(),
            highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow?.toLong()
        )
        firebaseDataSource.updateUserStats(uid, userStats)
    }

    override suspend fun checkUserProfileCompletion(): Boolean {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        return firestoreDataSource.checkIfProfileCompleted(uid)
    }

    private fun getCurrentDate(): String {
        return System.currentTimeMillis().toString()
    }

    override fun logout() {
        firebaseDataSource.logout()
    }

    override suspend fun getUserCompletedLessons(): Flow<List<String>> {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        return firestoreDataSource.getUserCompletedLessons(uid)
    }

    override suspend fun getLeaderboard(): List<LeaderboardUser> {
        return firebaseDataSource.getLeaderboard()
    }

    override suspend fun setUserScore(score: Int) {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        firebaseDataSource.setUserScore(uid, score)
    }

    override suspend fun setCompletedLesson(lessonId: String) {
        val uid = currentUser?.uid ?: throw Exception("User not logged in.")
        firestoreDataSource.setCompletedLesson(uid, lessonId)
    }
}