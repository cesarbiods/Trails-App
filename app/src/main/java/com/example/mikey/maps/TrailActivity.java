package com.example.mikey.maps;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mikey.maps.Trails.Trail;

import java.util.Arrays;

public class TrailActivity extends Activity {
    TextView trailName;
    TextView trailType;
    TextView trailDescription;
    TextView txtLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        Bundle b = getIntent().getExtras();
        final Trail trail = b.getParcelable("com.package.Trail");

        trailName = (TextView)findViewById(R.id.trailName);
        trailType = (TextView)findViewById(R.id.trailType);
        trailDescription = (TextView) findViewById(R.id.trailDescription);

        String trailActivities = Arrays.toString(trail.getType());
        trailActivities = trailActivities.replace("[","");
        trailActivities = trailActivities.replace("]","");
        trailName.setText(trail.getName());
        trailType.setText(trailActivities);
        trailDescription.setText(trail.getDescription());


        Button button = (Button) findViewById(R.id.SeeOnMap);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(TrailActivity.this,MapsActivity.class);
                intent.putExtra("com.package.Trail",trail);
                startActivity(intent);
            }
        });
    }


}
