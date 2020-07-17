package wevs.com.br.transferapp.ui.login

import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.UserAccount

sealed class LoginStates {
    data class CallSucess(val user: UserAccount?) : LoginStates()
    data class CallError(val mesage: String) : LoginStates()
    object UserSucess : LoginStates()
    object UserError : LoginStates()
    object PasswordSucess : LoginStates()
    object PasswordError : LoginStates()
    object EnableButton : LoginStates()
    object DisableButton : LoginStates()
    data class GetValuesSharedPreference(val user: String?, val password: String?) : LoginStates()
    data class SaveLoginSecurePreferences(val user: String?, val password: String?) : LoginStates()
}

sealed class LoginEvent {
    data class goToHomeActivity(val user: UserAccount) : LoginEvent()
}

sealed class LoginInteractor {
    data class GetValues(val username: String?, val password: String?) : LoginInteractor()
    data class ValidateFieldUser(val user: String) : LoginInteractor()
    data class ValidateFieldPassword(val password: String) : LoginInteractor()
    data class SaveDataSharedPreferences(val user: String, val password: String) : LoginInteractor()
    data class EnableButtonLogin(val user: String, val password: String) : LoginInteractor()
    data class PostLogin(val login: Login) : LoginInteractor()
}