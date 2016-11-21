package com.petsource.model;

/**
 * Created by Daniel on 11/21/2016.
 */

public class Rescue {
    private String id;
    private String petid;
    private String userid;
    private double latitude;
    private double longitude;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
