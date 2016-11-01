package com.example.user.petsource.users;

/**
 * Created by USER on 01/11/2016.
 */

public class User {

    private String id;
    private String username;
    private String password;
    private String Email;
    private String Phone;
    private String Address;
    private String DP;
    private String Birth;
    private Integer Rating;
    private Boolean isStaff;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }
}
