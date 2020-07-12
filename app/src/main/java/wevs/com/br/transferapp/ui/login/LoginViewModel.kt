package wevs.com.br.transferapp.ui.login

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.repository.LoginRepositoryImplements
import wevs.com.br.transferapp.validator.EmailValidator
import wevs.com.br.transferapp.validator.PasswordValidator
import wevs.com.br.transferapp.validator.ValidatorFields

class LoginViewModel : ViewModel() {
    private val repository: LoginRepositoryImplements by lazy { providerLoginReposytory() }
    private val useCase: LoginUseCase by lazy { providerLoginUseCase() }
    private val state: MutableLiveData<LoginStates> = MutableLiveData()
    private val event: MutableLiveData<LoginEvent> = MutableLiveData()

    val viewStates: LiveData<LoginStates> = state
    val viewEvent: LiveData<LoginEvent> = event

    private val validators = ArrayList<ValidatorFields>()

    fun interpret(interactor: LoginInteractor) {
        when (interactor) {
            is LoginInteractor.PostLogin -> {
                sendPostLogin(interactor.login)
            }

            is LoginInteractor.ValidateFieldUser -> {
                validateFieldUser(interactor.user)
            }

            is LoginInteractor.ValidateFieldPassword -> {
                validateFieldPassword(interactor.password)
            }

            is LoginInteractor.EnableButtonLogin -> {
                enableButton()
            }

            is LoginInteractor.GetValuesUserAndPassword -> {
                getValuesSharedPreferences(interactor.username, interactor.password)
            }

            is LoginInteractor.SaveDataSharedPreferences -> {
                saveLoginSecurePreferences(interactor.user, interactor.password)
            }
        }
    }

    private fun saveLoginSecurePreferences(user: String, password: String) {
        if (useCase.validateAllFields(validators) && useCase.ammountFieldsValidate(validators)) {
            state.postValue(LoginStates.SaveLoginSecurePreferences(user, password))
        }
    }

    private fun getValuesSharedPreferences(username: String?, password: String?) {
        if (useCase.verifyUserPasswordIsNull(username, password)) {
            state.postValue(LoginStates.GetValuesSharedPreference(username, password))
        } else {
            state.postValue(LoginStates.SharePreferencesEmpty)
        }
    }

    private fun enableButton() {
        if (useCase.validateAllFields(validators) && useCase.ammountFieldsValidate(validators)) {
            state.postValue(LoginStates.EnableButton)
        } else {
            state.postValue(LoginStates.DisableButton)
        }
    }

    private fun sendPostLogin(login: Login) {
        GlobalScope.launch {
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

    private fun validateFieldUser(edtUser: EditText) {
        val validator = EmailValidator(edtUser)
        validators.add(validator)
        edtUser.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validator.isValid()) {
                    state.postValue(LoginStates.UserSucess(edtUser))
                } else {
                    state.postValue(LoginStates.UserError)
                }
            }
        }
    }

    private fun validateFieldPassword(edtPassword: EditText) {
        val validator = PasswordValidator(edtPassword)
        validators.add(validator)
        edtPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (validator.isValid()) {
                    state.postValue(LoginStates.PasswordSucess(edtPassword))
                } else {
                    state.postValue(LoginStates.PasswordError)
                }
            }
        }
    }
}