package com.petsource.model;

import android.net.Uri;

/**
 * Created by USER on 01/11/2016.
 */

public class User {

    private String id;
    private String username;
    private String name;

    private int isStaff;
    private int isApprove;
    private String joinDate;

    private String address;
    private String city;
    private String birthday;
    private String job;
    private Uri url;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(int isStaff) {
        this.isStaff = isStaff;
    }

    public int getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(int isApprove) {
        this.isApprove = isApprove;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    public User(String id, String username, String name, int isStaff, int isApprove, String joinDate, String address, String city, String birthday, String job, Uri url) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.isStaff = isStaff;
        this.isApprove = isApprove;
        this.joinDate = joinDate;
        this.address = address;
        this.city = city;
        this.birthday = birthday;
        this.job = job;
        this.url = url;
    }
}
