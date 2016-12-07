package com.example.mikey.maps;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.maps.Trails.DatabaseOperations;
import com.example.mikey.maps.Trails.Trail;
import com.example.mikey.maps.Trails.TrailsList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class trailsActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private TrailAdapter adapter;
    private ListView listView;
    Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(this);
        listView = (ListView) findViewById(R.id.list);
        String filter = mySpinner.getSelectedItem().toString();
        TrailsList trails = new TrailsList(this);
        ArrayList<Trail> trailList = (ArrayList<Trail>)trails.getTrailList();
        System.out.println("Number of trails: " + trailList.size());



        adapter = new TrailAdapter(this, trailList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Trail trail = (Trail) listView.getItemAtPosition(position);
                Intent intent = new Intent(trailsActivity.this,TrailActivity.class);
                intent.putExtra("com.package.Trail",trail);
                startActivity(intent);

            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Trail trail = adapter.getItem(position);

//Here we use the Filtering Feature which we implemented in our Adapter class.
        adapter.getFilter().filter(mySpinner.getSelectedItem().toString(),new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int count) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
