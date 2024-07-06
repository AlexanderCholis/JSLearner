package eu.tkacas.jslearner.domain.repository

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.data.model.UserFirebase
import eu.tkacas.jslearner.domain.Result

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun signup(firstName: String, lastName: String, email: String, password: String): Result<FirebaseUser>
    suspend fun updateUserProfile(reasonOfUsingTheApp: String, profileCompleted: Boolean)
    suspend fun updateUserStats(user: UserFirebase)
    fun logout()
}