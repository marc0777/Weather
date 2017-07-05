package com.example.marco.weather.Search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.Button;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private View view;
    private ListView listView;
    private List<City> searchResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_search, container, false);
        listView = (ListView) view.findViewById(R.id.search_list);

        final Button queryButton = (Button) view.findViewById(R.id.queryButton);
        final EditText searchBox = (EditText) view.findViewById(R.id.searchBox);

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAPI().searchCities(searchBox.getText().toString(), Utils.getLocale()).enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        searchResult = response.body();
                        if (searchResult!=null) {
                            ListAdapter adapter = new SearchAdapter(getContext(), searchResult);
                            listView.setAdapter(adapter);
                            registerForContextMenu(listView);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    viewWeather(getCity(position).getId());
                                }
                            });
                        }
                        else Log.e("Network: ","Unauthorized");
                    }
                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {

                    }
                });

            }
        });
        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.search_view:
                return viewWeather(getCity(position).getId());
            case R.id.search_save:
                return saveLocation(getCity(position));
            case R.id.search_delete:
                return deleteLocation(getCity(position));
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        boolean itemSaved = Storage.isPresent(getCity(((AdapterView.AdapterContextMenuInfo) menuInfo).position));
        menu.getItem(1).setVisible(!itemSaved);
        menu.getItem(2).setVisible(itemSaved);
    }

    private boolean viewWeather(int id) {
        Snackbar.make(view.findViewById(R.id.search_activity), Integer.toString(id), Snackbar.LENGTH_SHORT).show();
        return false;
    }

    private boolean saveLocation(City city) {
        Storage.addCity(city);
        snackbar(city.getName() + " saved to LocationsFragment!");
        return false;
    }
    private boolean deleteLocation(City city) {
        Storage.removeCity(city);
        snackbar(city.getName()+" has been deleted!");
        return false;
    }
    private void snackbar (String message) {
        Snackbar.make(view.findViewById(R.id.search_activity), message, Snackbar.LENGTH_SHORT).show();
    }
    private City getCity (int position) {
        return searchResult.get(position);
    }


    private static AccuweatherAPI getAPI() {
        Gson gson = new GsonBuilder()
                .setLenient().registerTypeAdapter(City.class, new City.CityDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccuweatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(AccuweatherAPI.class);
    }
}