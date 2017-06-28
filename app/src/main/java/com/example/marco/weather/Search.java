package com.example.marco.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Search extends Fragment {

    EditText emailText;
    ListView responseView;
    final String savedResult = "[\n" +
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_search, container, false);

        responseView = (ListView) v.findViewById(R.id.result_list);
        emailText = (EditText) v.findViewById(R.id.searchBox);

        Button queryButton = (Button) v.findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Cities list = null;
                try {
                    list = new Cities(new DataRetriever().execute("locations/v1/cities/search","q="+emailText.getText().toString()).get(), getContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                final Cities list = new Cities(savedResult, getContext());

                ListAdapter adapter = new SimpleAdapter(
                        getContext(), list.getCities(),
                        R.layout.list_item, new String[]{"name", "country"}, new int[]{R.id.city,
                        R.id.country});
                responseView.setAdapter(adapter);

                responseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                        Toast.makeText(getContext(), list.getCities().get(position).get("id"), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return v;
    }

}