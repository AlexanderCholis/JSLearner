package eu.tkacas.jslearner.presentation.ui.state.auth

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    var errorMessage: String? = null
)