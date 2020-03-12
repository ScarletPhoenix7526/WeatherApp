package com.example.weather.network

import com.example.weather.BuildConfig
import com.example.weather.data.WeatherModel
import com.example.weather.utils.ENDPOINT_CURRENT_WEATHER
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET(ENDPOINT_CURRENT_WEATHER)
    fun getWeatherByName(@Query("q") country: String): Single<WeatherModel>

    @GET(ENDPOINT_CURRENT_WEATHER)
    fun getWeatherByCoords(@Query("lat") latitude: Float,
                           @Query("lon") longitude: Float): Call<WeatherModel>
}