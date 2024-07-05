package eu.tkacas.jslearner.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.domain.Result

class FirebaseDataSource(private val firebaseAuth: FirebaseAuth) {

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    suspend fun signup(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return Result.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}