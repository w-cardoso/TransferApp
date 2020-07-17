package wevs.com.br.transferapp.validator

class EmailValidator(private val fieldEmail: String) : ValidatorFields {
    private fun standardValidate(email: String): Boolean {
        if (email.matches(".+@.+\\..+".toRegex())) {
            return true
        }
        return false
    }

    override fun isValid(): Boolean = standardValidate(fieldEmail)
}