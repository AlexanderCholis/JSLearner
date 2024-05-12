package eu.tkacas.jslearner.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.use_case.ValidateEmail
import eu.tkacas.jslearner.domain.use_case.ValidatePassword
import eu.tkacas.jslearner.ui.events.LoginFormEvent
import eu.tkacas.jslearner.ui.states.LoginFormState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword()
): BaseAuthViewModel() {
    var state by mutableStateOf(LoginFormState())

    fun onEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password, isLogin = true)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { it.errorMessage != null }

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
}