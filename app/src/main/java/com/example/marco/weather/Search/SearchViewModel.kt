package com.example.marco.weather.Search

import android.content.Context
import android.util.Log
import android.widget.ListView

import com.example.marco.weather.Data.Location
import com.example.marco.weather.Tool.RealmStorage
import com.example.marco.weather.Tool.Utils

import java.io.IOException

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

internal class SearchViewModel {
    var result: List<Location> = ArrayList()
    private val storage = RealmStorage()

    fun isLocationSaved(position: Int): Boolean {
        return storage.isLocationPresent(result[position].id)
    }

    fun saveLocation(position: Int): String {
        val location = result[position]
        storage.addLocation(location)
        return location.name
    }

    fun deleteLocation(position: Int): String {
        val name = storage.getLocation(position).name
        storage.removeLocation(storage.getLocation(position).id)
        return name
    }

    fun getWeather(position: Int): String {
        return storage.getLocation(position).getForecastAt(0).toString()
    }

    fun search(query: String, adapter: SearchAdapter) {
        val call = Utils.searchAPI.searchCities(query, Utils.locale)
        call.enqueue(object : Callback<List<Location>> {
            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                if (response.isSuccessful) {
                    result = response.body()!!
                    adapter.clear()
                    adapter.addAll(result)
                } else {
                    try {
                        Log.e("Network: ", response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {

            }
        })
    }
}
