package com.example.marco.weather.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.City;

import java.util.List;

class SearchAdapter extends ArrayAdapter<City> {
    SearchAdapter(Context context, List<City> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

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