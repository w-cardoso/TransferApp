package wevs.com.br.transferapp.repository

import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse
import wevs.com.br.transferapp.model.StatementResponse

interface LoginRepository {

    suspend fun sendLogin(
        login: Login,
        onComplete: (LoginResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    )

    suspend fun getUser(
        idUser: Int,
        onComplete: (StatementResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}