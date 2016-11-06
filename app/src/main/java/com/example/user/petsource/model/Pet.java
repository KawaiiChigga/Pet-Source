package com.example.user.petsource.model;

/**
 * Created by Daniel on 11/6/2016.
 */

public class Pet {
    private String id;
    private String name;
    private String birthdate;
    private String race;
    private String userid;
    private boolean isMale;
    private boolean isDog;
    private boolean isCertified;

    public Pet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isDog() {
        return isDog;
    }

    public void setDog(boolean dog) {
        isDog = dog;
    }

    public boolean isCertified() {
        return isCertified;
    }

    public void setCertified(boolean certified) {
        isCertified = certified;
    }
}
