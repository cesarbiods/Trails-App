package com.example.mikey.maps;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.mikey.maps.Trails.Trail;

import java.util.Arrays;

public class TrailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        Bundle b = getIntent().getExtras();
        Trail trail = b.getParcelable("com.package.Trail");

        TextView trailName = (TextView)findViewById(R.id.trailName);
        TextView trailType = (TextView)findViewById(R.id.trailType);
        TextView trailDescription = (TextView) findViewById(R.id.trailDescription);
        String trailActivities = Arrays.toString(trail.getType());
        trailActivities = trailActivities.replace("[","");
        trailActivities = trailActivities.replace("]","");
        trailName.setText("Name: " + trail.getName());
        trailType.setText("Activities: " + trailActivities);
        trailDescription.setText("Description: " + trail.getDescription());
    }

}
