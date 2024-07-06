package eu.tkacas.jslearner.domain.usecase.welcome.validateregex

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
