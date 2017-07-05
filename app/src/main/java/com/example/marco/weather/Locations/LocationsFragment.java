package com.example.marco.weather.Locations;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marco.weather.R;


public class LocationsFragment extends Fragment {
    private View view;

    private LocationsViewModel viewModel = new LocationsViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_locations, container, false);
        ListView listView = (ListView) view.findViewById(R.id.locations_list);

        LocationsAdapter adapter = new LocationsAdapter(viewModel);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                viewWeather(position);
            }
        });

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(true);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.search_view:
                return viewWeather(position);
            case R.id.search_delete:
                return deleteLocation(position);
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private boolean viewWeather(int position) {
        snackbar(viewModel.getWeather(position));
        return false;
    }
    private boolean deleteLocation(int position) {
        snackbar(viewModel.deleteLocation(position) + " " + getString(R.string.location_deleted));
        return false;
    }
    private void snackbar (String message) {
        Snackbar.make(view.findViewById(R.id.search_activity), message, Snackbar.LENGTH_SHORT).show();
    }


}