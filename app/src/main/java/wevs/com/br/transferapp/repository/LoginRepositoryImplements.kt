package wevs.com.br.transferapp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse
import wevs.com.br.transferapp.model.StatementResponse
import wevs.com.br.transferapp.service.LoginService

class LoginRepositoryImplements(private val loginService: LoginService) : LoginRepository {
    override suspend fun sendLogin(
        login: Login,
        onComplete: (LoginResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        loginService.login(login).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(Throwable("Não foi possível realizar a requisição"))
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { onComplete(it) }
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }
        })
    }

    override suspend fun getUser(
        idUser: Int,
        onComplete: (StatementResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        loginService.getListStatement(idUser).enqueue(object : Callback<StatementResponse> {

            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                onError(Throwable("Não foi possível realizar a requisição"))
            }

            override fun onResponse(
                call: Call<StatementResponse>,
                response: Response<StatementResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listStatment -> onComplete(listStatment) }
                } else {
                    onError(Throwable("Não foi possível realizar a requisição"))
                }
            }

        })
    }
}