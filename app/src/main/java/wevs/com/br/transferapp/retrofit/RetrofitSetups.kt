package wevs.com.br.transferapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wevs.com.br.transferapp.service.LoginService

//private const val BASE_URL = "http://192.168.0.17:8080/"
const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

class RetrofitSetups {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)

    }
}