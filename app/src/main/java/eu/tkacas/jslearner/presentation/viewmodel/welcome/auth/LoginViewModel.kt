package eu.tkacas.jslearner.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.user.GetProfileCompletionUseCase
import eu.tkacas.jslearner.domain.usecase.user.LoginUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidatePassword
import eu.tkacas.jslearner.presentation.ui.events.auth.LoginFormEvent
import eu.tkacas.jslearner.presentation.ui.state.auth.LoginFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val getProfileCompletionUseCase: GetProfileCompletionUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
) : ViewModel() {
    private var _state by mutableStateOf(LoginFormState())
    val state: LoginFormState get() = _state

    private val _loginFlow = MutableStateFlow<Result<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Result<FirebaseUser>?> = _loginFlow

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                _state = _state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                _state = _state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(_state.email)
        val passwordResult = validatePassword.execute(_state.password, isLogin = true)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _state = _state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        loginUser(_state.email, _state.password)
    }

    private fun loginUser(email: String, password: String) = viewModelScope.launch {
        try {
            val user = loginUseCase.execute(email, password)
            _loginFlow.value = Result.Success(user)
        } catch (e: Exception) {
            _state = _state.copy(errorMessage = e.message)
            _loginFlow.value = Result.Error(e)
        }
    }

    suspend fun determineDestination(): String {
        return try {
            if (getProfileCompletionUseCase.execute()) "mainActivity" else "experienceLevel"
        } catch (e: Exception) {
            "experienceLevel"
        }
    }
}