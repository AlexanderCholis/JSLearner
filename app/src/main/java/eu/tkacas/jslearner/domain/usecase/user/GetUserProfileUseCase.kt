package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.Result

class GetUserProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Result<User> {
        return when (val result = authRepository.getUserProfile()) {
            is Result.Success -> {
                val userFirestore = result.result
                Result.Success(User(
                    firstName = userFirestore.firstName,
                    lastName = userFirestore.lastName,
                    lessonsCompleted = userFirestore.lessonsCompleted,
                ))
            }
            is Result.Error -> Result.Error(result.exception)
            is Result.Loading -> Result.Loading // TODO
        }
    }
}
