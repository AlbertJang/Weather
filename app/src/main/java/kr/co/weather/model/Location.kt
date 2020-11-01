package kr.co.weather.model

data class Location(
        val title: String,
        val location_type: String,
        val latt_long: String,
        val woeid: Long,
        val distance: Long
)