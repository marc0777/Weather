package com.example.marco.weather.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.marco.weather.Data.Location;
import com.example.marco.weather.databinding.ListItemBinding;

class SearchAdapter extends ArrayAdapter<Location> {
    private Context context;
    SearchAdapter(Context context, SearchViewModel viewModel) {
        super(context, 0, viewModel.getResult());
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        binding.setLocation(getItem(position));
        return binding.getRoot();
    }
}