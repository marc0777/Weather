package com.example.marco.weather.Locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView

import com.example.marco.weather.R
import com.example.marco.weather.Data.Location

import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

internal class LocationsAdapter(viewModel: LocationsViewModel) : RealmBaseAdapter<Location>(viewModel.locations as OrderedRealmCollection<Location>), ListAdapter {

    private class ViewHolder {
        internal var name: TextView? = null
        internal var country: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
            viewHolder = ViewHolder()
            viewHolder.name = convertView!!.findViewById(R.id.list_item_city) as TextView
            viewHolder.country = convertView.findViewById(R.id.list_item_country) as TextView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        if (adapterData != null) {
            val location = adapterData!![position]
            viewHolder.name!!.text = location.name
            viewHolder.country!!.text = location.country
        }

        return convertView
    }
}