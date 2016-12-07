package com.example.mikey.maps.Weather.Model;

/**
 * Created by Workstation2.0 on 11/19/2016.
 */

public class Temperature {
    private double temp;
    private float minTemp;
    private float maxTemp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }
}
