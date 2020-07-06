package wevs.com.br.transferapp.ui.login

import android.widget.EditText
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.UserAccount

sealed class LoginStates {
    data class CallSucess(val user: UserAccount?) : LoginStates()
    data class CallError(val mesage: String) : LoginStates()
    data class UserSucess(val editText: EditText) : LoginStates()
    object UserError : LoginStates()
    data class PasswordSucess(val editText: EditText) : LoginStates()
    object PasswordError : LoginStates()
    object EnableButton : LoginStates()
    object DisableButton : LoginStates()
    object ContainsDateSharedPreferences : LoginStates()
    object SharePreferencesEmpty : LoginStates()
    object SaveUserAndPasswordSharedPreferences : LoginStates()
}

sealed class LoginEvent {
    data class goToHomeActivity(val user: UserAccount) : LoginEvent()
}

sealed class LoginInteractor {
    data class PostLogin(val login: Login) : LoginInteractor()
    data class ValidateFieldUser(val user: EditText) : LoginInteractor()
    data class ValidateFieldPassword(val password: EditText) : LoginInteractor()
    data class SaveDataSharedPreferences(val user: EditText) : LoginInteractor()
    object EnableButtonLogin : LoginInteractor()
}