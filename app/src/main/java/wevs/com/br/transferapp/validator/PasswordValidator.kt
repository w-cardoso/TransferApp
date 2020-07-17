package wevs.com.br.transferapp.validator

class PasswordValidator(private val fieldPassword: String) : ValidatorFields {
    private fun standardValidate(password: String): Boolean {
        if (password.matches("((?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,16})".toRegex())) {
            return true
        }
        return false
    }

    override fun isValid(): Boolean = standardValidate(fieldPassword)
}