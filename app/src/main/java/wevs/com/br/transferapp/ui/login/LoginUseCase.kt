package wevs.com.br.transferapp.ui.login

import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.validator.ValidatorFields

class LoginUseCase() {

    fun verifyUserAccount(userAccount: UserAccount): Boolean {
        val array: Array<UserAccount>? = arrayOf(userAccount)
        return array.isNullOrEmpty()
    }

    fun verifyUserPasswordIsNull(user: String, password: String): Boolean =
        user.isNotEmpty() && password.isNotEmpty()

    fun validateAllFields(validators: ArrayList<ValidatorFields>): Boolean {
        var formIsValid = true
        for (validator in validators) {
            if (!validator.isValid()) formIsValid = false
        }
        return formIsValid
    }
}