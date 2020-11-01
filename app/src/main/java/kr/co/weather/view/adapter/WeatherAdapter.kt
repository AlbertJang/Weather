package kr.co.weather.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_weather.view.*
import kr.co.weather.R
import kr.co.weather.model.response.WeatherResponse
import java.util.zip.Inflater

class WeatherAdapter(private val weatherList: List<WeatherResponse?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == R.layout.item_header) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
            WeatherViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position != 0)
            (holder as WeatherViewHolder).bind(weatherList[position-1])
    }

    override fun getItemCount(): Int = weatherList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            R.layout.item_header
        } else {
            R.layout.item_weather
        }
    }

    inner class HeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(response: WeatherResponse?) {
            itemView.cityTextView.text = response?.title

            Glide.with(itemView.context)
                .load("https://www.metaweather.com/static/img/weather/png/${response?.consolidated_weather?.get(0)?.weather_state_abbr}.png")
                .into(itemView.todayWeatherImageView)

            itemView.todayWeatherTextView.text = response?.consolidated_weather?.get(0)?.weather_state_name
            itemView.todayTempTextView.text = "${response?.consolidated_weather?.get(0)?.the_temp?.toLong()}°C"
            itemView.todayHumidityTextView.text = "${response?.consolidated_weather?.get(0)?.humidity}%"

            Glide.with(itemView.context)
                .load("https://www.metaweather.com/static/img/weather/png/${response?.consolidated_weather?.get(1)?.weather_state_abbr}.png")
                .into(itemView.tomorrowWeatherImageView)

            itemView.tomorrowWeatherTextView.text = response?.consolidated_weather?.get(1)?.weather_state_name
            itemView.tomorrowTempTextView.text = "${response?.consolidated_weather?.get(1)?.the_temp?.toLong()}°C"
            itemView.tomorrowHumidityTextView.text = "${response?.consolidated_weather?.get(1)?.humidity}%"
        }
    }
}
