package kr.co.weather.di

import kr.co.weather.viewModel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { WeatherViewModel(get()) }
}