package wevs.com.br.transferapp.ui.home

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wevs.com.br.transferapp.utils.USER_ACCOUNT_KEY

class HomeViewModel : ViewModel() {

    private val useCase: HomeUseCase by lazy { providerHomeUseCase() }
    private val state: MutableLiveData<HomeStates> = MutableLiveData()
    val viewStates: LiveData<HomeStates> = state

    fun interpret(interactor: HomeInteractor) {
        when (interactor) {
            is HomeInteractor.ValidateIntent -> {
                interactor.intent?.let { validateIntent(it) }
            }
        }
    }

    private fun validateIntent(intent: Intent) {
        if (useCase.validateIntent(intent)) {
            state.postValue(HomeStates.IntentOk(intent.getParcelableExtra(USER_ACCOUNT_KEY)))
        } else {
            state.postValue(HomeStates.IntentNOk)
        }
    }
}