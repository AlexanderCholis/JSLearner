package eu.tkacas.jslearner.ui.states

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
    val termsError: String? = null
)
