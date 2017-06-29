package com.example.marco.weather.Search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.Button;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.*;

import java.util.HashMap;

public class Search extends Fragment {

    private View view;
    private ListView listView;
    private final String savedResult = "[\n" +
            "  {\n" +
            "    \"Version\": 1,\n" +
            "    \"Key\": \"216517\",\n" +
            "    \"Type\": \"City\",\n" +
            "    \"Rank\": 45,\n" +
            "    \"LocalizedName\": \"Mestre\",\n" +
            "    \"EnglishName\": \"Mestre\",\n" +
            "    \"PrimaryPostalCode\": \"\",\n" +
            "    \"Region\": {\n" +
            "      \"ID\": \"EUR\",\n" +
            "      \"LocalizedName\": \"Europe\",\n" +
            "      \"EnglishName\": \"Europe\"\n" +
            "    },\n" +
            "    \"Country\": {\n" +
            "      \"ID\": \"IT\",\n" +
            "      \"LocalizedName\": \"Italy\",\n" +
            "      \"EnglishName\": \"Italy\"\n" +
            "    },\n" +
            "    \"AdministrativeArea\": {\n" +
            "      \"ID\": \"34\",\n" +
            "      \"LocalizedName\": \"Veneto\",\n" +
            "      \"EnglishName\": \"Veneto\",\n" +
            "      \"Level\": 1,\n" +
            "      \"LocalizedType\": \"Region\",\n" +
            "      \"EnglishType\": \"Region\",\n" +
            "      \"CountryID\": \"IT\"\n" +
            "    },\n" +
            "    \"TimeZone\": {\n" +
            "      \"Code\": \"CEST\",\n" +
            "      \"Name\": \"Europe/Rome\",\n" +
            "      \"GmtOffset\": 2,\n" +
            "      \"IsDaylightSaving\": true,\n" +
            "      \"NextOffsetChange\": \"2017-10-29T01:00:00Z\"\n" +
            "    },\n" +
            "    \"GeoPosition\": {\n" +
            "      \"Latitude\": 45.473,\n" +
            "      \"Longitude\": 12.239,\n" +
            "      \"Elevation\": {\n" +
            "        \"Metric\": {\n" +
            "          \"Value\": 2,\n" +
            "          \"Unit\": \"m\",\n" +
            "          \"UnitType\": 5\n" +
            "        },\n" +
            "        \"Imperial\": {\n" +
            "          \"Value\": 6,\n" +
            "          \"Unit\": \"ft\",\n" +
            "          \"UnitType\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"IsAlias\": false,\n" +
            "    \"SupplementalAdminAreas\": [\n" +
            "      {\n" +
            "        \"Level\": 2,\n" +
            "        \"LocalizedName\": \"Venezia\",\n" +
            "        \"EnglishName\": \"Venezia\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Level\": 3,\n" +
            "        \"LocalizedName\": \"Venezia\",\n" +
            "        \"EnglishName\": \"Venezia\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"DataSets\": [\n" +
            "      \"Alerts\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"Version\": 1,\n" +
            "    \"Key\": \"1456856\",\n" +
            "    \"Type\": \"City\",\n" +
            "    \"Rank\": 85,\n" +
            "    \"LocalizedName\": \"Mestre\",\n" +
            "    \"EnglishName\": \"Mestre\",\n" +
            "    \"PrimaryPostalCode\": \"\",\n" +
            "    \"Region\": {\n" +
            "      \"ID\": \"EUR\",\n" +
            "      \"LocalizedName\": \"Europe\",\n" +
            "      \"EnglishName\": \"Europe\"\n" +
            "    },\n" +
            "    \"Country\": {\n" +
            "      \"ID\": \"ES\",\n" +
            "      \"LocalizedName\": \"Spain\",\n" +
            "      \"EnglishName\": \"Spain\"\n" +
            "    },\n" +
            "    \"AdministrativeArea\": {\n" +
            "      \"ID\": \"GA\",\n" +
            "      \"LocalizedName\": \"Galicia\",\n" +
            "      \"EnglishName\": \"Galicia\",\n" +
            "      \"Level\": 1,\n" +
            "      \"LocalizedType\": \"Autonomous Community\",\n" +
            "      \"EnglishType\": \"Autonomous Community\",\n" +
            "      \"CountryID\": \"ES\"\n" +
            "    },\n" +
            "    \"TimeZone\": {\n" +
            "      \"Code\": \"CEST\",\n" +
            "      \"Name\": \"Europe/Madrid\",\n" +
            "      \"GmtOffset\": 2,\n" +
            "      \"IsDaylightSaving\": true,\n" +
            "      \"NextOffsetChange\": \"2017-10-29T01:00:00Z\"\n" +
            "    },\n" +
            "    \"GeoPosition\": {\n" +
            "      \"Latitude\": 43.226,\n" +
            "      \"Longitude\": -7.189,\n" +
            "      \"Elevation\": {\n" +
            "        \"Metric\": {\n" +
            "          \"Value\": 393,\n" +
            "          \"Unit\": \"m\",\n" +
            "          \"UnitType\": 5\n" +
            "        },\n" +
            "        \"Imperial\": {\n" +
            "          \"Value\": 1289,\n" +
            "          \"Unit\": \"ft\",\n" +
            "          \"UnitType\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"IsAlias\": false,\n" +
            "    \"SupplementalAdminAreas\": [\n" +
            "      {\n" +
            "        \"Level\": 3,\n" +
            "        \"LocalizedName\": \"Ribeira de Piquín\",\n" +
            "        \"EnglishName\": \"Ribeira de Piquín\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"DataSets\": [\n" +
            "      \"Alerts\",\n" +
            "      \"MinuteCast\"\n" +
            "    ]\n" +
            "  }\n" +
            "]";
    private CitiesParser searchResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_search, container, false);

        listView = (ListView) view.findViewById(R.id.search_list);

        Button queryButton = (Button) view.findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            /*try {
                EditText searchBox = (EditText) view.findViewById(R.id.searchBox);
                searchResult = new CitiesParser(new DataRetriever().execute("locations/v1/cities/search","q="+searchBox.getText().toString()).get());
            } catch (Exception e) {
               searchResult = null;
            }*/

            searchResult = new CitiesParser(savedResult);

            ListAdapter adapter = new SimpleAdapter(
                getContext(), searchResult.getParsedCities(),
                R.layout.list_item, new String[]{"name", "country"}, new int[]{R.id.list_item_city,
                R.id.list_item_country});
            listView.setAdapter(adapter);

            registerForContextMenu(listView);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                    viewWeather(Integer.parseInt(getCity(position).get("id")));
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
                return viewWeather(Integer.parseInt(getCity(position).get("id")));
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

    public boolean viewWeather (int id) {
        Snackbar.make(view.findViewById(R.id.search_activity), Integer.toString(id), Snackbar.LENGTH_SHORT).show();
        return false;
    }

    public boolean saveLocation (HashMap<String,String> city) {
        Storage.addCity(city);
        snackbar(city.get("name") + " saved to Locations!");
        return false;
    }
    public boolean deleteLocation (HashMap<String,String> city) {
        Storage.removeCity(city);
        snackbar(city.get("name")+" has been deleted!");
        return false;
    }
    private void snackbar (String message) {
        Snackbar.make(view.findViewById(R.id.search_activity), message, Snackbar.LENGTH_SHORT).show();
    }
    private HashMap<String,String> getCity (int position) {
        return searchResult.getParsedCities().get(position);
    }
}