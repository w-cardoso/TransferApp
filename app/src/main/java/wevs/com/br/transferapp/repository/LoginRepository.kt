package wevs.com.br.transferapp.repository

import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse

interface LoginRepository {

    suspend fun sendLogin(
        login: Login,
        onComplete: (LoginResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}