package eu.tkacas.jslearner.ui.viewModel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.usecase.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.ValidateTerms
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.ISignUpActions
import eu.tkacas.jslearner.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.ui.states.SignUpFormState
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateTerms: ValidateTerms = ValidateTerms(),
    val signUpActions: ISignUpActions
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

    companion object {
        fun provideFactory(
            validateFirstName: ValidateFirstName,
            validateLastName: ValidateLastName,
            validateEmail: ValidateEmail,
            validatePassword: ValidatePassword,
            validateTerms: ValidateTerms,
            signUpActions: ISignUpActions
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return SignUpViewModel(validateFirstName, validateLastName, validateEmail, validatePassword, validateTerms, signUpActions) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}