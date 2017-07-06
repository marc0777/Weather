package com.example.marco.weather.Weather

import android.util.Log
import android.widget.TextView

import com.example.marco.weather.Data.Forecast
import com.example.marco.weather.Data.Location
import com.example.marco.weather.Tool.RealmStorage
import com.example.marco.weather.Tool.Utils

import java.io.IOException

import retrofit2.Call
import retrofit2.Response

internal class WeatherViewModel {
    private val storage = RealmStorage()

    val locationsNumber: Int
        get() = storage.size()

    fun getLocation(position: Int): Location {
        return storage.getLocation(position)
    }

    fun setWeather(position: Int, textView: TextView) {
        val location = getLocation(position)
        if (System.currentTimeMillis() - location.updateTime > 300000) {
            Utils.forecastAPI.getForecast(location.id, Utils.locale).enqueue(object : retrofit2.Callback<Forecast> {
                override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                    if (response.isSuccessful) {
                        storage.updateLocationWeather(location, response.body()!!, System.currentTimeMillis())
                        textView.text = getWeather(location)
                    } else {
                        try {
                            Log.e("Network: ", response.errorBody()!!.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }
                }
                override fun onFailure(call: Call<Forecast>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } else
            textView.text = getWeather(location)
    }

    private fun getWeather(location: Location): String {
        return "Location ID: " + location.id + "\nLocation name: " + location.name + "\nLocation country: " + location.country + "\nActual weather: " + location.getForecastAt(0).dayText
    }
}
