package wevs.com.br.transferapp.validator

import android.widget.EditText

class StandardValidator(private val field: EditText) : ValidatorFields {

    override fun isValid(): Boolean {
        if (!validateFieldRequired()) return false
        removeError()
        return false
    }

    private fun validateFieldRequired(): Boolean {
        val text = field.text.toString()
        if (text.isEmpty()) {
            return false
        }
        return true
    }

    private fun removeError() {
        field.error = null
    }
}