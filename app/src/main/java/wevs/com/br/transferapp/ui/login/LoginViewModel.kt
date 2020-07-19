package wevs.com.br.transferapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.repository.LoginRepositoryImplements

class LoginViewModel : ViewModel() {
    private val repository: LoginRepositoryImplements by lazy { providerLoginReposytory() }
    private val useCase: LoginUseCase by lazy { providerLoginUseCase() }
    private val state: MutableLiveData<LoginStates> = MutableLiveData()
    val viewStates: LiveData<LoginStates> = state

    fun interpret(interactor: LoginInteractor) = when (interactor) {
        is LoginInteractor.PostLogin -> sendPostLogin(interactor.login)

        is LoginInteractor.ValidateFieldUser -> validateFieldUser(interactor.user)

        is LoginInteractor.ValidateFieldPassword -> validateFieldPassword(interactor.password)

        is LoginInteractor.EnableButtonLogin -> enableButton(interactor.user, interactor.password)

        is LoginInteractor.GetValues -> getValuesSharedPreferences(
            interactor.username,
            interactor.password
        )

        is LoginInteractor.SaveDataSharedPreferences -> saveLoginSecurePreferences(
            interactor.user,
            interactor.password
        )
    }

    private fun saveLoginSecurePreferences(user: String, password: String) {
        if (useCase.validateUser(user) && useCase.validatePassword(password)) {
            state.postValue(LoginStates.SaveLoginSecurePreferences(user, password))
        }
    }

    private fun getValuesSharedPreferences(username: String?, password: String?) {
        if (useCase.verifyUserPasswordIsNull(username, password)) {
            state.postValue(LoginStates.GetValuesSharedPreference(username, password))
        }
    }

    private fun enableButton(user: String, password: String) {
        if (useCase.validateUser(user) && useCase.validatePassword(password)) {
            state.postValue(LoginStates.EnableButton)
        } else {
            state.postValue(LoginStates.DisableButton)
        }
    }

    private fun validateFieldUser(user: String) {
        if (useCase.validateUser(user)) {
            state.postValue(LoginStates.UserSucess)
        } else {
            state.postValue(LoginStates.UserError)
        }

    }

    private fun validateFieldPassword(password: String) {
        if (useCase.validatePassword(password)) {
            state.postValue(LoginStates.PasswordSucess)
        } else {
            state.postValue(LoginStates.PasswordError)
        }
    }

    private fun sendPostLogin(login: Login) {
        viewModelScope.launch {
            repository.sendLogin(
                login,
                {
                    if (!useCase.verifyUserAccount(it.userAccount)) {
                        state.postValue(LoginStates.CallSucess(it.userAccount))
                    } else {
                        state.postValue(LoginStates.CallError(it.errorResponse.message))
                    }
                },
                {
                    state.postValue(LoginStates.CallError(it.message ?: ""))
                })
        }
    }
}