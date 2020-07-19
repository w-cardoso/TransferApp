package wevs.com.br.transferapp.ui.home

import android.content.Intent
import wevs.com.br.transferapp.model.UserAccount

sealed class HomeStates {
    data class IntentOk(val user: UserAccount?) : HomeStates()
    object IntentNOk : HomeStates()
}

sealed class HomeInteractor {
    data class ValidateIntent(val intent: Intent?) : HomeInteractor()
}