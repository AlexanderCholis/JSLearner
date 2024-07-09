package eu.tkacas.jslearner.data.source.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.await
import eu.tkacas.jslearner.data.model.UserFirebase

class FirebaseDataSource(private val firebaseAuth: FirebaseAuth, private val firebase: FirebaseDatabase) {

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
        return snapshot.getValue(UserFirebase::class.java)
    }

    suspend fun updateUserStats(userId: String, user: UserFirebase?) {
        val userMap = user?.toMap()?.filterValues { it != null }?.mapValues { it.value!! }
        firebase.getReference("users").child(userId).updateChildren(userMap as Map<String, Any>).await()
    }
}