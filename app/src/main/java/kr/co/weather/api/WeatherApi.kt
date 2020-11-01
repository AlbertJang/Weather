package kr.co.weather.api

import kr.co.weather.model.Location
import kr.co.weather.model.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("location/search")
    fun searchLocation(@Query("query") query: String): Call<List<Location>>

    @GET("location/{woeid}")
    fun searchWeather(@Path("woeid") woeid: Long): Call<WeatherResponse>
}