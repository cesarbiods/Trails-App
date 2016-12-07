package com.example.mikey.maps.Weather.Model;

/**
 * Created by Workstation2.0 on 11/19/2016.
 */

public class Weather {
    public Place local;
    public String iconData;
    public CurrentCond cc = new CurrentCond();
    public Temperature temp = new Temperature();
    public Wind wind = new Wind();
    public Cloud cloud = new Cloud();

}
