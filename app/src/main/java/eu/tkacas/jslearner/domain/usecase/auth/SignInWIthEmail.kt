package eu.tkacas.jslearner.domain.usecase

import eu.tkacas.jslearner.data.repository.AuthRepositoryImpl
import eu.tkacas.jslearner.domain.Result

class SignInWithEmail(
    private val authRepositoryImpl: AuthRepositoryImpl
) {
    suspend fun execute(email: String, password: String): Result<Unit> {
        return try {
            authRepositoryImpl.login(email, password)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}