package com.example.mikey.maps.Trails;

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
    String[] type;
    String description;

    public Trail(String name, String latitude, String longtitude, String type){
        this.name = name;
        setLocation(latitude,longtitude);
        this.type = type.split(",");
        //System.out.println(type);
        this.description = " ";
    }
    public Trail(String name, String latitude, String longtitude, String type, String description){
        this.name = name;
        setLocation(latitude,longtitude);
        this.type = type.split(",");
        //System.out.println(type);
        this.description = description;
    }

    public Trail(String name, double latitude, double longtitude, String type){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type.split(",");
        //System.out.println(type);
        this.description = " ";
    }


    public Trail(String name, double latitude, double longtitude, String[] type, String description){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type;
        System.out.println(type);
        this.description = description;
    }


    public String getName(){
        return this.name;
    }

    private void setLocation(String latitude, String longtitude){

        location.setLatitude(Double.parseDouble(latitude));
        this.latitude = Double.parseDouble(latitude);


        location.setLongitude(Double.parseDouble(longtitude));

        this.longtitude = Double.parseDouble(longtitude);

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

    public String[] getType(){
        return this.type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
