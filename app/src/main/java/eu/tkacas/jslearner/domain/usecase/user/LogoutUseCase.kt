package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    fun execute() {
        return authRepository.logout()
    }
}