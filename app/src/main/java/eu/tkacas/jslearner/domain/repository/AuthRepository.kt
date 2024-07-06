package eu.tkacas.jslearner.domain.repository

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun signup(firstName: String, lastName: String, email: String, password: String): Result<FirebaseUser>
    suspend fun updateUserProfile(learningReason: LearningReason, profileCompleted: Boolean, experienceLevel: ExperienceLevel)
    fun logout()
}