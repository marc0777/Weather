package com.example.marco.weather.Locations

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

import com.example.marco.weather.R


class LocationsFragment : Fragment() {

    private val viewModel = LocationsViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.activity_locations, container, false)
        val listView = view.findViewById(R.id.locations_list) as ListView

        val adapter = LocationsAdapter(viewModel)
        listView.adapter = adapter
        registerForContextMenu(listView)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> viewWeather(position) }

        return view
    }

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo)
        val inflater = activity.menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        menu.getItem(1).isVisible = false
        menu.getItem(2).isVisible = true
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val info = item!!.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            R.id.search_view -> viewWeather(position)
            R.id.search_delete -> deleteLocation(position)
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun viewWeather(position: Int): Boolean {
        snackbar(viewModel.getWeather(position))
        return false
    }

    private fun deleteLocation(position: Int): Boolean {
        snackbar(viewModel.deleteLocation(position) + " " + getString(R.string.location_deleted))
        return false
    }

    private fun snackbar(message: String) {
        Snackbar.make(view!!.findViewById(R.id.search_activity), message, Snackbar.LENGTH_SHORT).show()
    }


}