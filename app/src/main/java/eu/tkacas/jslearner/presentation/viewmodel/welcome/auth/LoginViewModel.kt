package eu.tkacas.jslearner.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.usecase.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.ValidatePassword
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.actions.ILoginActions
import eu.tkacas.jslearner.presentation.ui.events.LoginFormEvent
import eu.tkacas.jslearner.presentation.ui.state.LoginFormState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    val loginActions: ILoginActions
): BaseAuthViewModel()  {
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

    companion object {
        fun provideFactory(
            validateEmail: ValidateEmail,
            validatePassword: ValidatePassword,
            loginActions: ILoginActions
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return LoginViewModel(validateEmail, validatePassword, loginActions) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}