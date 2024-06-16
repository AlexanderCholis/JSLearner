package eu.tkacas.jslearner.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateTerms
import eu.tkacas.jslearner.presentation.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.presentation.ui.state.SignUpFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val validateFirstName: ValidateFirstName,
    private val validateLastName: ValidateLastName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateTerms: ValidateTerms,
): ViewModel() {
    private var _state by mutableStateOf(SignUpFormState())
    val state: SignUpFormState get() = _state

    private val _signupFlow = MutableStateFlow<Result<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Result<FirebaseUser>?> = _signupFlow

    fun onEvent(event: SignUpFormEvent) {
        when(event) {
            is SignUpFormEvent.FirstNameChanged -> {
                _state = _state.copy(firstName = event.firstName)
            }
            is SignUpFormEvent.LastNameChanged -> {
                _state = _state.copy(lastName = event.lastName)
            }
            is SignUpFormEvent.EmailChanged -> {
                _state = _state.copy(email = event.email)
            }
            is SignUpFormEvent.PasswordChanged -> {
                _state = _state.copy(password = event.password)
            }
            is SignUpFormEvent.AcceptTerms -> {
                _state = _state.copy(acceptedTerms = event.isAccepted)
            }
            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(_state.firstName)
        val lastNameResult = validateLastName.execute(_state.lastName)
        val emailResult = validateEmail.execute(_state.email)
        val passwordResult = validatePassword.execute(_state.password, isLogin = false)
        val termsResult = validateTerms.execute(_state.acceptedTerms)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            termsResult
        ).any { it.errorMessage != null}

        if(hasError) {
            _state = _state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        signupUser(_state.firstName, _state.lastName, _state.email, _state.password)
    }

    private fun signupUser(firstName: String, lastName: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Result.Loading
        val result = authRepository.signup(firstName, lastName, email, password)
        if (result is Result.Error) {
            _state = _state.copy(errorMessage = result.exception.message)
        }
        _signupFlow.value = result
    }
}