package wevs.com.br.transferapp.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse
import wevs.com.br.transferapp.service.LoginService

class LoginRepositoryImplements(private val loginService: LoginService) : LoginRepository {
    override suspend fun sendLogin(
        login: Login,
        onComplete: (LoginResponse) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        loginService.login(login).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t)
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
}