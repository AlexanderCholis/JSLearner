package eu.tkacas.jslearner.data.source.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.Result

class FirebaseDataSource(private val firebaseAuth: FirebaseAuth, private val firebase: FirebaseDatabase) {

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!)
        } catch (e: Exception) {
            Log.w("FirebaseDataSource", "Error logging in user.", e)
            e.printStackTrace()
            Result.Error(e)
        }
    }

    suspend fun signup(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return Result.Success(result.user!!)
        } catch (e: Exception) {
            Log.w("FirebaseDataSource", "Error signing up user.", e)
            e.printStackTrace()
            Result.Error(e)
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    suspend fun setUserStats(userId: String, user: UserFirebase?) {
        try {
            val userMap = user?.toMap()?.filterValues { it != null }
            firebase.getReference("users").child(userId).setValue(userMap).await()
        } catch (e: Exception) {
            Log.w("FirebaseDataSource", "Error setting user.", e)
        }
    }

    suspend fun getUserStats(userId: String): UserFirebase? {
        return try {
            val snapshot = firebase.getReference("users").child(userId).get().await()
            snapshot.getValue(UserFirebase::class.java)
        } catch (e: Exception) {
            Log.w("FirebaseDataSource", "Error getting user.", e)
            null
        }
    }
}