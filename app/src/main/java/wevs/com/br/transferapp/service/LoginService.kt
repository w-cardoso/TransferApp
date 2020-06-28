package wevs.com.br.transferapp.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse

interface LoginService {
    @POST("login")
    fun login(@Body login: Login): Call<LoginResponse>
}