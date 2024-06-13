package eu.tkacas.jslearner.domain.usecase

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
