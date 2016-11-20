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
    String description;

    public Trail(String name, String latitude, String longtitude, String type){
        this.name = name;
        setLocation(latitude,longtitude);
        this.type = type;
        this.description = " ";
    }
    public Trail(String name, String latitude, String longtitude, String type, String description){
        this.name = name;
        setLocation(latitude,longtitude);
        this.type = type;
        this.description = description;
    }

    public Trail(String name, double latitude, double longtitude, String type){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type;
        this.description = " ";
    }


    public Trail(String name, double latitude, double longtitude, String type, String description){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type;
        this.description = description;
    }


    public String getName(){
        return this.name;
    }

    private void setLocation(String latitude, String longtitude){
        char orentation;
        //latitude parsing
        System.out.println(latitude);
        String[] latTokens = latitude.split("°");
        String newLat = latTokens[0] + "." + latTokens[1];
        latTokens = newLat.split("\\'");
        //System.out.println("latTokens index 0 "+ latTokens[0]);
        newLat = latTokens[0]+latTokens[1];//this adds the lat without the last decmial
        latTokens = newLat.split("\\.");
        newLat = latTokens[0] + "." + latTokens[1];
        orentation = latTokens[2].charAt(latTokens[2].length()-1);
        if(orentation == 'S'){
            newLat = "-" + newLat;
        }
        //System.out.println("new lat to be stored "+Double.parseDouble(newLat));
        location.setLatitude(Double.parseDouble(newLat));
        this.latitude = Double.parseDouble(newLat);
        //longitude parsing
        String[] longTokens = longtitude.split("°");
        String newLong = longTokens[0] + "." + longTokens[1];
        longTokens = newLong.split("'");
        newLong = longTokens[0] + longTokens[1];
        longTokens = newLong.split("\\.");
        //orentation = longTokens[1];
        newLong = longTokens[0] + "." + longTokens[1];
        //newLong = longTokens[0];//this adds the lat without the last decmial
        orentation = longTokens[2].charAt(longTokens[2].length()-1);
        if(orentation == 'W'){
            newLong = "-" + newLong;
        }
        //System.out.println("new long to be stored " + Double.parseDouble(newLong));
        location.setLongitude(Double.parseDouble(newLong));
        this.longtitude = Double.parseDouble(newLong);
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

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
