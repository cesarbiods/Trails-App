package com.example.mikey.maps;

import android.location.Location;

/**
 * Created by Workstation2.0 on 11/9/2016.
 */

public class Trail {
    String name;
    double latitude;
    double longtitude;
    Location location = new Location("");
    int rating;
    String type;

    public Trail(String name, String latitude, String longtitude, String type){
        this.name = name;
        setLocation(latitude,longtitude);
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    private void setLocation(String latitude, String longtitude){
        String orentation;
        //latitude parsing
        String[] latTokens = latitude.split("°");
        String newLat = latTokens[0] + "." + latTokens[1];
        latTokens = newLat.split("'");
        newLat = latTokens[0] + latTokens[1];
        latTokens = newLat.split("\"");
        orentation = latTokens[1];
        newLat = latTokens[0];//this adds the lat without the last decmial
        latTokens = newLat.split(".");
        newLat = latTokens[0];
        if(orentation == "S"){
            newLat = "-" + newLat;
        }
        location.setLatitude(Double.parseDouble(newLat));

        //longitude parsing
        String[] longTokens = longtitude.split("°");
        String newLong = longTokens[0] + "." + longTokens[1];
        longTokens = newLong.split("'");
        newLong = longTokens[0] + longTokens[1];
        longTokens = newLong.split("\"");
        orentation = longTokens[1];
        newLong = longTokens[0];//this adds the lat without the last decmial
        longTokens = newLong.split(".");
        newLong = longTokens[0];
        if(orentation == "W"){
            newLong = "-" + newLong;
        }
        location.setLongitude(Double.parseDouble(newLong));
    }
    public double getLatitude(){
        return this.latitude;
    }

    public double getLongtitude(){
        return this.longtitude;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getRating(){
        return this.rating;
    }

    public String getType(){
        return this.type;
    }
}
