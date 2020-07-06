package wevs.com.br.transferapp.validator

import android.widget.EditText

class EmailValidator(private val fieldEmail: EditText) : ValidatorFields {
    private fun standardValidate(email: String): Boolean {
        if (email.matches(".+@.+\\..+".toRegex())) {
            return true
        }
        return false
    }

    override fun isValid(): Boolean {
        val email = fieldEmail.text.toString()
        return standardValidate(email)
    }
}