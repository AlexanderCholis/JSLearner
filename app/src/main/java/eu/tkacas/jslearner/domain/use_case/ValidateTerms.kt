package eu.tkacas.jslearner.domain.use_case

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidateResult {
        if(!acceptedTerms) {
            return ValidateResult(
                successful = false,
                errorMessage = "You need to accept the terms"
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}