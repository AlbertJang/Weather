package kr.co.weather.model.weather

data class Source (
    val title: String,
    val slug: String,
    val url: String,
    val crawl_rate: Long
)