package wevs.com.br.transferapp

import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse
import wevs.com.br.transferapp.model.UserAccount

sealed class LoginStates {
    data class CallSucess(val user: UserAccount?) : LoginStates()
    data class CallError(val mesage: String) : LoginStates()
}

sealed class LoginEvent {
    data class goToHomeActivity(val user: UserAccount) : LoginEvent()
}

sealed class LoginInteractor {
    data class PostLogin(val login: Login) : LoginInteractor()
}