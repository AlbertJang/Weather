package kr.co.weather.model.response

import kr.co.weather.model.weather.ConsolidatedWeather
import kr.co.weather.model.weather.Parent
import kr.co.weather.model.weather.Source

data class WeatherResponse(
    val consolidated_weather: List<ConsolidatedWeather>,
    val time: String,
    val sun_rise: String,
    val sun_set: String,
    val timezone_name: String,
    val parent: Parent,
    val sources: List<Source>,
    val title: String,
    val location_type: String,
    val woeid: Long,
    val latt_long: String,
    val timezone: String
)