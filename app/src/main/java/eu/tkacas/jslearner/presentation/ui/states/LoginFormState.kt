package eu.tkacas.jslearner.presentation.ui.states

data class LoginFormState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)