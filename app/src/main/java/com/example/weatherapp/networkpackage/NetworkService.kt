package com.example.weatherapp.networkpackage

import com.example.weatherapp.models.CurrentModel
import com.example.weatherapp.models.ForecastModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.text.SimpleDateFormat
import java.util.*

const val weather_api_key = "581c7ea90f3e3a90353a046ce8a1ad15"
const val base_url = "https://api.openweathermap.org/data/2.5/"
const val icon_prefix = "https://openweathermap.org/img/wn/"
const val icon_suffix = "@2x.png"

fun  getFormattedDate(dt: Long, pattern: String) =
    SimpleDateFormat(pattern).format(Date(dt*1000))

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface WeatherServiceAPI{
    @GET
    suspend fun getCurrentWeatherData(@Url endUrl: String) : CurrentModel

    @GET
    suspend fun getForecastWeatherData(@Url endUrl: String) : ForecastModel
}

object NetworkService {
    val weatherServiceAPI : WeatherServiceAPI by lazy {
        retrofit.create(WeatherServiceAPI::class.java)
    }
}
