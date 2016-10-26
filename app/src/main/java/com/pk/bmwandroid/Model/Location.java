package com.pk.bmwandroid.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Location implements Serializable {
    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String arrivalTime;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getId() { return id;}
    public void setId(String id) { this.id = id; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Location() {}

    public static Location fromJsonObject(JSONObject object) throws JSONException {
        Location location = new Location();
        location.setId(object.getString("ID"));
        location.setName(object.getString("Name"));
        location.setLatitude(object.getString("Latitude"));
        location.setLongitude(object.getString("Longitude"));
        location.setAddress(object.getString("Address"));
        location.setArrivalTime(object.getString("ArrivalTime"));
        return location;
    }

}


