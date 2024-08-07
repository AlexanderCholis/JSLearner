package eu.tkacas.jslearner.domain.usecase.user

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): FirebaseUser {
        return authRepository.login(email, password)
    }
}