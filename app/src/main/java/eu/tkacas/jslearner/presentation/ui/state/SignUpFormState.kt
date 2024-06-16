package eu.tkacas.jslearner.presentation.ui.state

import com.google.firebase.auth.FirebaseUser

data class SignUpFormState(
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null,
    val submitStatus: Result<FirebaseUser>? = null,
    val errorMessage: String? = null
)
