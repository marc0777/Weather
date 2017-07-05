package com.example.marco.weather.Locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.City;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

class LocationsAdapter extends RealmBaseAdapter<City> implements ListAdapter {


    private static class ViewHolder {
        TextView name;
        TextView country;
    }

    LocationsAdapter(OrderedRealmCollection<City> realmResults) {
        super(realmResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.list_item_city);
            viewHolder.country = (TextView) convertView.findViewById(R.id.list_item_country);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (adapterData != null) {
            final City item = adapterData.get(position);
            viewHolder.name.setText(item.getName());
            viewHolder.country.setText(item.getCountry());
        }

        return convertView;
    }
}