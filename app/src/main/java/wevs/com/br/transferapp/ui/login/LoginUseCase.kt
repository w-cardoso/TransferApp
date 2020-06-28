package wevs.com.br.transferapp.ui.login

import wevs.com.br.transferapp.model.UserAccount

class LoginUseCase() {

    fun verifyUserAccount(userAccount: UserAccount): Boolean {
        val array: Array<UserAccount>? = arrayOf(userAccount)
        return array.isNullOrEmpty()
    }
}