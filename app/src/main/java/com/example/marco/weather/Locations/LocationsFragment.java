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
import com.example.marco.weather.Tool.City;
import com.example.marco.weather.Tool.Storage;

import io.realm.Realm;
import io.realm.RealmResults;


public class LocationsFragment extends Fragment {
    private View view;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_locations, container, false);
        ListView listView = (ListView) view.findViewById(R.id.locations_list);

        realm = Realm.getDefaultInstance();

        RealmResults<City> cities = realm.where(City.class).findAll();
        LocationsAdapter adapter = new LocationsAdapter(cities);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                viewWeather(getCity(position).getId());
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
                return viewWeather(getCity(position).getId());
            case R.id.search_delete:
                return deleteLocation(getCity(position));
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private boolean viewWeather(int id) {
        Snackbar.make(view.findViewById(R.id.search_activity), Integer.toString(id), Snackbar.LENGTH_SHORT).show();
        return false;
    }
    private boolean deleteLocation(City city) {
        String name = city.getName();
        Storage.removeCity(city);
        snackbar(name+" has been deleted!");
        return false;
    }
    private void snackbar (String message) {
        Snackbar.make(view.findViewById(R.id.search_activity), message, Snackbar.LENGTH_SHORT).show();
    }

    private City getCity (int position) {
        return realm.where(City.class).findAll().get(position);
    }


}