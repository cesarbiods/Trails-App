package com.example.mikey.maps;

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

    public Trail(String name, double latitude, double longtitude, String type){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        location.setLatitude(latitude);
        location.setLongitude(longtitude);
        this.type = type;
    }

    public String getName(){
        return this.name;
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
