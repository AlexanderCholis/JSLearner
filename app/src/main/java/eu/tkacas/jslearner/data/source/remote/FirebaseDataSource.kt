package eu.tkacas.jslearner.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel

class FirebaseDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val firebase: FirebaseDatabase
) {

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Login failed")
    }

    suspend fun signup(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Signup failed")
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    suspend fun setUserStats(userId: String, user: UserFirebase?) {
        val userMap = user?.toMap()?.filterValues { it != null }
        firebase.getReference("users").child(userId).setValue(userMap).await()
    }

    suspend fun getUserStats(userId: String): UserFirebase? {
        val snapshot = firebase.getReference("users").child(userId).get().await()
        if (snapshot.exists()) {
            val currentLessonId = snapshot.child("current_lesson_id").getValue(String::class.java)
            val currentCourseId = snapshot.child("current_course_id").getValue(String::class.java)
            val highScoreCorrectAnswersInARow =
                snapshot.child("high_score_correct_answers_in_a_row").getValue(Long::class.java)
            val highScoreDaysInARow =
                snapshot.child("high_score_days_in_a_row").getValue(Long::class.java)
            val experienceLevel =
                snapshot.child("experience_level").getValue(ExperienceLevel::class.java)
            val experienceScore = snapshot.child("experience_score").getValue(Long::class.java)

            return UserFirebase(
                currentLessonId = currentLessonId,
                currentCourseId = currentCourseId,
                highScoreCorrectAnswersInARow = highScoreCorrectAnswersInARow,
                highScoreDaysInARow = highScoreDaysInARow,
                experienceLevel = experienceLevel,
                experienceScore = experienceScore
            )
        }
        return null
    }

    suspend fun updateUserStats(userId: String, user: UserFirebase?) {
        val userMap = user?.toMap()?.filterValues { it != null }?.mapValues { it.value!! }
        firebase.getReference("users").child(userId).updateChildren(userMap as Map<String, Any>)
            .await()
    }
}