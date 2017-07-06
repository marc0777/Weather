package com.example.marco.weather.Tool

import com.example.marco.weather.Data.Forecast
import com.example.marco.weather.Data.Location

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuweatherAPI {

    @GET("/locations/v1/cities/search?apikey=$API_KEY&")
    fun searchCities(@Query("q") query: String, @Query("language") language: String): Call<List<Location>>

    @GET("/forecasts/v1/daily/5day/{id}?apikey=$API_KEY&metric=true&")
    fun getForecast(@Path("id") id: Int, @Query("language") language: String): Call<Forecast>

    companion object {
        const val BASE_URL = "http://dataservice.accuweather.com/"
        const val API_KEY = "KPG9Olkd338PiKKvGTcURXdjc6sRPIfr"
    }
}