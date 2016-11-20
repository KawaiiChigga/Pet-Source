package com.petsource.model;

/**
 * Created by USER on 20/11/2016.
 */

public class Shop {
    private String iduser;
    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;
    private double latitude;
    private double longitude;
    private int isWash;
    private int isTrim;
    private int isClip;
    private int isCare;
    private String price;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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

    public int getIsWash() {
        return isWash;
    }

    public void setIsWash(int isWash) {
        this.isWash = isWash;
    }

    public int getIsTrim() {
        return isTrim;
    }

    public void setIsTrim(int isTrim) {
        this.isTrim = isTrim;
    }

    public int getIsClip() {
        return isClip;
    }

    public void setIsClip(int isClip) {
        this.isClip = isClip;
    }

    public int getIsCare() {
        return isCare;
    }

    public void setIsCare(int isCare) {
        this.isCare = isCare;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
