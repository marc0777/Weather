package com.example.marco.weather.Tool

import com.example.marco.weather.Data.Forecast
import com.example.marco.weather.Data.Location

interface Storage {
    val locations: List<Location>
    fun getLocation(position: Int): Location
    fun addLocation(location: Location)
    fun removeLocation(id: Int)
    fun findLocation(id: Int): Location?
    fun isLocationPresent(id: Int): Boolean
    fun updateLocationWeather(location: Location, forecast: Forecast, time: Long)
    fun size(): Int
}

