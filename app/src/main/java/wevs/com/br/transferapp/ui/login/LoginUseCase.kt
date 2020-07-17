package wevs.com.br.transferapp.ui.login

import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.validator.EmailValidator
import wevs.com.br.transferapp.validator.PasswordValidator

class LoginUseCase {
    fun verifyUserAccount(userAccount: UserAccount): Boolean {
        val array: Array<UserAccount>? = arrayOf(userAccount)
        return array.isNullOrEmpty()
    }

    fun validateUser(username: String): Boolean {
        val validateUser = EmailValidator(username)
        return validateUser.isValid()
    }

    fun validatePassword(password: String): Boolean {
        val validatePassword = PasswordValidator(password)
        return validatePassword.isValid()
    }

    fun verifyUserPasswordIsNull(username: String?, password: String?): Boolean =
        !username.isNullOrEmpty() && !password.isNullOrEmpty()
}