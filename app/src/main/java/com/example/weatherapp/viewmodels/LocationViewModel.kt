package com.example.weatherapp.viewmodels

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LocationViewModel: ViewModel() {
    //val repository = WeatherRepository()
    val locationLiveData: MutableLiveData<Location> = MutableLiveData()
    //val currentModelLiveData : MutableLiveData<CurrentModel> = MutableLiveData()
    //val forecastModelLiveData : MutableLiveData<ForecastModel> = MutableLiveData()

    fun setNewLocation(location: Location){
        locationLiveData.value = location
    }

    /*fun fetchData(){
        viewModelScope.launch {
            try {
                currentModelLiveData.value = repository.fetchCurrentWeatherData(locationLiveData.value!!)
                forecastModelLiveData.value = repository.fetchForecastWeatherData(locationLiveData.value!!)
            }catch (e: Exception){
                Log.d("LocationViewModel", e.localizedMessage)
            }
        }
    }*/
}