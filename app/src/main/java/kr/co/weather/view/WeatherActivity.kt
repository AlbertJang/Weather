package kr.co.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_weather.*
import kr.co.weather.R
import kr.co.weather.view.adapter.WeatherAdapter
import kr.co.weather.viewModel.WeatherViewModel
import org.koin.android.ext.android.inject

class WeatherActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        configureObservables()

        swipeRefreshLayout?.setOnRefreshListener {
            runOnUiThread {
                progress?.visibility = View.VISIBLE
            }

            weatherViewModel.searchWeather("se")
        }

        weatherViewModel.searchWeather("se")
    }

    private fun configureObservables() {
        weatherViewModel.weatherLiveData.observe(this, Observer { weatherList ->
            recyclerView?.apply {
                layoutManager = LinearLayoutManager(this@WeatherActivity, LinearLayoutManager.VERTICAL, false)
                adapter = WeatherAdapter(weatherList)
                addItemDecoration(DividerItemDecoration(this@WeatherActivity, LinearLayoutManager.VERTICAL))
            }

            swipeRefreshLayout?.isRefreshing = false

            runOnUiThread {
                progress?.visibility = View.GONE
            }
        })
    }
}