package kr.co.weather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kr.co.weather.api.WeatherRepository
import kr.co.weather.model.response.WeatherResponse

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val weatherLiveData = MutableLiveData<List<WeatherResponse?>>()

    fun searchWeather(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val locationResponse = weatherRepository.searchLocation(query)
                    val weatherList = ArrayList<WeatherResponse?>()

                    if (locationResponse.isSuccessful) {
                        val responseList = locationResponse.body()?.map { location ->
                            async {
                                weatherRepository.searchWeather(location.woeid)
                            }
                        }?.awaitAll()

                        responseList?.forEach { response ->
                            if (response.isSuccessful) {
                                weatherList.add(response.body())
                            } else {
                                // search weather error
                            }
                        }

                        weatherLiveData.postValue(weatherList)
                    } else {
                        // search location error
                    }
                } catch (e: Exception) {
                    // error
                    e.printStackTrace()
                }
            }
        }
    }
}