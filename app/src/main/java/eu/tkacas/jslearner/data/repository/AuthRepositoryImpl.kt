package eu.tkacas.jslearner.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.data.model.UserFirestore
import eu.tkacas.jslearner.data.source.remote.FirebaseDataSource
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result

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

    suspend fun updateUserProfile(userId: String, reasonOfUsingTheApp: String, profileCompleted: Boolean) {
        try { //Check for overwriting the user profile
            val user = UserFirestore(reasonOfUsingTheApp = reasonOfUsingTheApp, profileCompleted = profileCompleted)
            firestoreDataSource.setUserProfile(userId, user)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user profile.", e)
        }
    }

    suspend fun updateUserStats(userId: String, user: UserFirebase) {
        try {
            firebaseDataSource.setUserStats(userId, user)
        } catch (e: Exception) {
            Log.w("AuthRepositoryImpl", "Error updating user stats.", e)
        }
}



    private fun getCurrentDate(): String {
        return System.currentTimeMillis().toString()
    }
    override fun logout() = firebaseDataSource.logout()
}