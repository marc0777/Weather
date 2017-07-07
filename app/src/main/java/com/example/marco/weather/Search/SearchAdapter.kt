package com.example.marco.weather.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.example.marco.weather.Data.Location
import com.example.marco.weather.databinding.ListItemBinding

internal class SearchAdapter(context: Context, viewModel: SearchViewModel) : ArrayAdapter<Location>(context, 0, viewModel.result) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.location = getItem(position)
        return binding.root
    }
}