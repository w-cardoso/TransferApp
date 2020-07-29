@file:JvmName("HomeProvider")
package wevs.com.br.transferapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

fun providerHomeViewModel(activity: AppCompatActivity): HomeViewModel =
    ViewModelProvider(activity).get(HomeViewModel::class.java)

fun providerHomeUseCase(): HomeUseCase = HomeUseCase()