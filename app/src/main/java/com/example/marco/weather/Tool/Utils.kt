package com.example.marco.weather.Tool

import com.example.marco.weather.Data.Location
import com.example.marco.weather.Data.Weather
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object Utils {

    val locale: String
        get() = Locale.getDefault().toString().toLowerCase().replace("_", "-")

    val searchAPI: AccuweatherAPI
        get() {
            val gson = GsonBuilder()
                    .setLenient().registerTypeAdapter(Location::class.java, Location.CityDeserializer())
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(AccuweatherAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create<AccuweatherAPI>(AccuweatherAPI::class.java)
        }

    val forecastAPI: AccuweatherAPI
        get() {
            val gson = GsonBuilder()
                    .setLenient().registerTypeAdapter(Weather::class.java, Weather.WeatherDeserializer())
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(AccuweatherAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create<AccuweatherAPI>(AccuweatherAPI::class.java)
        }
}
