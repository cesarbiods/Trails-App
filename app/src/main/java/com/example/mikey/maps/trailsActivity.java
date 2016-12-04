package com.example.mikey.maps;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mikey.maps.Trails.DatabaseOperations;
import com.example.mikey.maps.Trails.Trail;
import com.example.mikey.maps.Trails.TrailsList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class trailsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String filter = mySpinner.getSelectedItem().toString();
        TrailsList trails = new TrailsList(this);
        ArrayList<Trail> trailList = (ArrayList<Trail>)trails.getTrailList(filter);
        System.out.println("Number of trails: " + trailList.size());
        ArrayList<String> trailName = new ArrayList<String>();
        for(Trail x: trailList){
            trailName.add(x.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,trailName);
        getListView().setAdapter(adapter);
    }




}
