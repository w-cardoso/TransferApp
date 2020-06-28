package wevs.com.br.transferapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import wevs.com.br.transferapp.LoginEvent
import wevs.com.br.transferapp.LoginInteractor
import wevs.com.br.transferapp.LoginStates
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.repository.LoginRepositoryImplements


class LoginViewModel : ViewModel() {
    private val repository: LoginRepositoryImplements by lazy { providerLoginReposytory() }
    private val useCase: LoginUseCase by lazy { providerLoginUseCase() }
    private val state: MutableLiveData<LoginStates> = MutableLiveData()
    private val event: MutableLiveData<LoginEvent> = MutableLiveData()

    val viewStates: LiveData<LoginStates> = state
    val viewEvent: LiveData<LoginEvent> = event

    fun interpret(interactor: LoginInteractor) {
        when (interactor) {
            is LoginInteractor.PostLogin -> {
                sendPostLogin(interactor.login)
            }
        }
    }

    private fun sendPostLogin(login: Login) {
        GlobalScope.launch {
            repository.sendLogin(login,
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