package wevs.com.br.transferapp.validator

import android.widget.EditText

class PasswordValidator(private val fieldPassword: EditText) : ValidatorFields {
    private val standardValidator: StandardValidator = StandardValidator(fieldPassword)

    private fun standardValidate(password: String): Boolean {
        if (password.matches("((?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,16})".toRegex())) {
            return true
        }
        return false
    }

    override fun isValid(): Boolean {
        if (!standardValidator.isValid()) return false;
        val password = fieldPassword.text.toString()
        return standardValidate(password)
    }
}