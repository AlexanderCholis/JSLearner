package eu.tkacas.jslearner.domain.usecase.validateregex

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
