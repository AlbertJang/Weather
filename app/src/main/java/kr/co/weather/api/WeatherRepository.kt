package kr.co.weather.api

import retrofit2.awaitResponse

class WeatherRepository (val weatherApi: WeatherApi) {
    suspend fun searchLocation(query: String) = weatherApi.searchLocation(query).awaitResponse()
    suspend fun searchWeather(woeid: Long) = weatherApi.searchWeather(woeid).awaitResponse()
}