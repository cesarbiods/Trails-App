package com.example.mikey.maps;

/**
 * Created by Workstation2.0 on 11/9/2016.
 */

public class Trail {
    String name;
    int latitude;
    int longtitude;
    int rating;
    String type;

    public Trail(String name,int latitude, int longtitude, String type){
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public int getLatitude(){
        return this.latitude;
    }

    public int getLongtitude(){
        return this.longtitude;
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
