package com.example.mikey.maps.Trails;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by Workstation2.0 on 11/9/2016.
 */

public class Trail implements Parcelable {
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
    public Trail(Parcel in){
        this.name = in.readString();
        this.latitude = in.readDouble();
        this.longtitude = in.readDouble();
        this.type = in.readString().split(",");
        this.description = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longtitude);
        dest.writeString(Arrays.toString(this.type));
        dest.writeString(this.description);
    }


    public static final Parcelable.Creator<Trail> CREATOR = new Parcelable.Creator<Trail>() {

        public Trail createFromParcel(Parcel in) {
            return new Trail(in);
        }

        public Trail[] newArray(int size) {
            return new Trail[size];
        }
    };


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
