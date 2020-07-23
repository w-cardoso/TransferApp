package wevs.com.br.transferapp.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.LoginResponse
import wevs.com.br.transferapp.model.StatementResponse

interface LoginService {
    @POST("login")
    fun login(@Body login: Login): Call<LoginResponse>

    @GET("/api/statements/{url}")
    fun getListStatement(@Path("url") userId: Int): Call<StatementResponse>
}