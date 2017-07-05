package com.example.marco.weather.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marco.weather.R;
import com.example.marco.weather.Data.Location;

class SearchAdapter extends ArrayAdapter<Location> {

    SearchAdapter(Context context, SearchViewModel viewModel) {
        super(context, 0, viewModel.getResult());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Location city = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView cityName = (TextView) convertView.findViewById(R.id.list_item_city);
        TextView countryName = (TextView) convertView.findViewById(R.id.list_item_country);

        cityName.setText(city.getName());
        countryName.setText(city.getCountry());

        return convertView;
    }
}