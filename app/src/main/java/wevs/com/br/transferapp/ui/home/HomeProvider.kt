package wevs.com.br.transferapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import wevs.com.br.transferapp.ui.login.LoginUseCase
import wevs.com.br.transferapp.ui.login.LoginViewModel

fun providerHomeViewModel(activity: AppCompatActivity): HomeViewModel =
    ViewModelProvider(activity).get(HomeViewModel::class.java)

fun providerHomeUseCase(): HomeUseCase = HomeUseCase()