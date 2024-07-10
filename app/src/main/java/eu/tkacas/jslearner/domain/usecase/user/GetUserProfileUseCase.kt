package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.repository.AuthRepository

class GetUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): User {
        val userFirestore = authRepository.getUserProfile()
        return User(
            firstName = userFirestore.firstName,
            lastName = userFirestore.lastName,
            lessonsCompleted = userFirestore.lessonsCompleted,
        )
    }
}