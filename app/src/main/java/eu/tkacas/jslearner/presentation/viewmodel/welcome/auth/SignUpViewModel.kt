package eu.tkacas.jslearner.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateTerms
import eu.tkacas.jslearner.presentation.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.presentation.ui.state.SignUpFormState
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val validateFirstName: ValidateFirstName,
    private val validateLastName: ValidateLastName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateTerms: ValidateTerms
): BaseAuthViewModel() {
    var state by mutableStateOf(SignUpFormState())

    fun onEvent(event: SignUpFormEvent) {
        when(event) {
            is SignUpFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is SignUpFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is SignUpFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignUpFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password, isLogin = false)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            termsResult
        ).any { it.errorMessage != null}

        if(hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
}