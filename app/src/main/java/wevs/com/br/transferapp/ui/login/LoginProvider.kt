package wevs.com.br.transferapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import wevs.com.br.transferapp.repository.LoginRepositoryImplements
import wevs.com.br.transferapp.retrofit.RetrofitSetups
import wevs.com.br.transferapp.service.LoginService

private val service: LoginService = RetrofitSetups().loginService

fun providerLoginViewModel(activity: AppCompatActivity): LoginViewModel =
    ViewModelProviders.of(activity).get(LoginViewModel::class.java)

fun providerLoginUseCase(): LoginUseCase = LoginUseCase()

fun providerLoginReposytory(): LoginRepositoryImplements = LoginRepositoryImplements(service)


