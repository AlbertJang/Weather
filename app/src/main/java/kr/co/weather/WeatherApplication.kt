package kr.co.weather

import android.app.Application
import kr.co.weather.di.networkModule
import kr.co.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}