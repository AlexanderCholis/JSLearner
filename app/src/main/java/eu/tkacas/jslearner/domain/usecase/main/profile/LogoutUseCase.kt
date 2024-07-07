package eu.tkacas.jslearner.domain.usecase.main.profile

import eu.tkacas.jslearner.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend fun execute() {
        authRepository.logout()
    }
}