package eu.tkacas.jslearner.data.repository

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.User

class AuthRepositoryImpl (
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
): AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    @SuppressLint("RestrictedApi")
    override suspend fun signup(firstName: String, lastName: String, email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName("$firstName $lastName").build())?.await()

            // Save user to Firebase Realtime Database
            val user = User(firstName, lastName)
            firebaseDatabase.getReference("users").child(result.user!!.uid).setValue(user)

            return Result.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}