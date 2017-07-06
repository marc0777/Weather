package com.example.marco.weather.Locations

import com.example.marco.weather.Data.Location
import com.example.marco.weather.Tool.RealmStorage

internal class LocationsViewModel {
    private val storage = RealmStorage()

    val locations: List<Location>
        get() = storage.locations

    fun deleteLocation(position: Int): String {
        val name = storage.getLocation(position).name
        storage.removeLocation(storage.getLocation(position).id)
        return name
    }

    fun getWeather(position: Int): String {
        return storage.getLocation(position).getForecastAt(0).toString()
    }
}
