package eu.tkacas.jslearner.domain.use_case

class ValidatePassword {
    fun execute(password: String): ValidateResult {
        if(password.length < 8) {
            return ValidateResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLetterAndDigits = password.any { it.isDigit() } && password.any() { it.isLetter() }
        if(!containsLetterAndDigits) {
            return ValidateResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and one digit"
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}