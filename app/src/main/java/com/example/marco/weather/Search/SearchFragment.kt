package com.example.marco.weather.Search

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView

import android.widget.Button

import com.example.marco.weather.R

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.activity_search, container, false)
        val listView = view.findViewById(R.id.list) as ListView
        val queryButton = view.findViewById(R.id.queryButton) as Button
        val searchBox = view.findViewById(R.id.searchBox) as EditText
        val adapter = SearchAdapter(context, viewModel)
        listView.adapter = adapter
        registerForContextMenu(listView)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> viewWeather(position) }

        queryButton.setOnClickListener {
            viewModel.search(searchBox.text.toString(),adapter)
        }

        return view
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        return when (item.itemId) {
            R.id.search_view -> viewWeather(info.position)
            R.id.search_save -> saveLocation(info.position)
            R.id.search_delete -> deleteLocation(info.position)
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo)
        val inflater = activity.menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        val itemSaved = viewModel.isLocationSaved((menuInfo as AdapterView.AdapterContextMenuInfo).position)
        menu.getItem(1).isVisible = !itemSaved
        menu.getItem(2).isVisible = itemSaved
    }

    private fun viewWeather(position: Int): Boolean {
        snackbar(viewModel.getWeather(position))
        return false
    }

    private fun saveLocation(position: Int): Boolean {
        snackbar(viewModel.saveLocation(position) + " " + getString(R.string.location_saved))
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