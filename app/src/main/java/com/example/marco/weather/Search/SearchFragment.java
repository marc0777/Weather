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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.Button;

import com.example.marco.weather.R;

public class SearchFragment extends Fragment {

    private View view;
    private ListView listView;

    private SearchViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new SearchViewModel();

        view = inflater.inflate(R.layout.activity_search, container, false);
        listView = (ListView) view.findViewById(R.id.search_list);
        final Button queryButton = (Button) view.findViewById(R.id.queryButton);
        final EditText searchBox = (EditText) view.findViewById(R.id.searchBox);

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.search(searchBox.getText().toString());
                ListAdapter adapter = new SearchAdapter(getContext(), viewModel);
                listView.setAdapter(adapter);
                registerForContextMenu(listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        viewWeather(position);
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
                return viewWeather(position);
            case R.id.search_save:
                return saveLocation(position);
            case R.id.search_delete:
                return deleteLocation(position);
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        boolean itemSaved = viewModel.isLocationSaved(  ((AdapterView.AdapterContextMenuInfo) menuInfo).position     );
        menu.getItem(1).setVisible(!itemSaved);
        menu.getItem(2).setVisible(itemSaved);
    }

    private boolean viewWeather(int position) {
        snackbar(viewModel.getWeather(position));
        return false;
    }

    private boolean saveLocation(int position) {
        snackbar(viewModel.saveLocation(position) + " " + getString(R.string.location_saved));
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