package com.example.marco.weather.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.example.marco.weather.R
import com.example.marco.weather.Data.Location

internal class SearchAdapter(context: Context, viewModel: SearchViewModel) : ArrayAdapter<Location>(context, 0, viewModel.result) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val city = getItem(position)

        if (convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)!!

        val cityName = convertView.findViewById(R.id.list_item_city) as TextView
        val countryName = convertView.findViewById(R.id.list_item_country) as TextView

        cityName.text = city.name
        countryName.text = city.country

        return convertView
    }
}