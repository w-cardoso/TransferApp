package wevs.com.br.transferapp.ui.home

import android.content.Intent

class HomeUseCase {

    fun validateIntent(intent: Intent): Boolean {
        return intent.hasExtra("USER_ACCOUNT")
    }
}