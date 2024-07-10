package eu.tkacas.jslearner.domain.usecase.user

import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.domain.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser {
        return authRepository.signup(firstName, lastName, email, password)
    }
}