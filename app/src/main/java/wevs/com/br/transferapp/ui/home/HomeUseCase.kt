package wevs.com.br.transferapp.ui.home

import android.content.Intent
import wevs.com.br.transferapp.utils.USER_ACCOUNT_KEY

class HomeUseCase {

    fun validateIntent(intent: Intent): Boolean = intent.hasExtra(USER_ACCOUNT_KEY)

}