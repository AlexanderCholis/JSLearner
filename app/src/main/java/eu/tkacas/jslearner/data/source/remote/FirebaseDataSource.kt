package eu.tkacas.jslearner.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.data.model.LeaderboardUser
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
            val highScoreCorrectAnswersInARow =
                snapshot.child("high_score_correct_answers_in_a_row").getValue(Long::class.java)
            val highScoreDaysInARow =
                snapshot.child("high_score_days_in_a_row").getValue(Long::class.java)
            val experienceLevel =
                snapshot.child("experience_level").getValue(ExperienceLevel::class.java)
            val experienceScore = snapshot.child("experience_score").getValue(Long::class.java)

            return UserFirebase(
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

    suspend fun getLeaderboard(): List<LeaderboardUser> {
        val snapshot = firebase.getReference("leaderboard").get().await()
        val leaderboardUsers = mutableListOf<LeaderboardUser>()
        snapshot.children.forEach { dataSnapshot ->
            val uid = dataSnapshot.key ?: ""
            val firstname = dataSnapshot.child("firstname").getValue(String::class.java) ?: ""
            val lastname = dataSnapshot.child("lastname").getValue(String::class.java) ?: ""
            val experienceScore = dataSnapshot.child("score").getValue(Long::class.java) ?: 0L
            leaderboardUsers.add(
                LeaderboardUser(
                    uid = uid,
                    firstName = firstname,
                    lastName = lastname,
                    score = experienceScore.toInt(
                    )
                )
            )
        }
        return leaderboardUsers
    }

    suspend fun setUserScore(userId: String, score: Int) {
        firebase.getReference("users").child(userId).child("experience_score").setValue(score).await()
    }
}