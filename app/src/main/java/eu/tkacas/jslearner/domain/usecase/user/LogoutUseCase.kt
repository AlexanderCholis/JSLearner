package eu.tkacas.jslearner.domain.usecase.user

import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.Result

class LogoutUseCase(private val authRepository: AuthRepository) {
    fun execute(): Result<Unit> {
        return authRepository.logout()
    }
}