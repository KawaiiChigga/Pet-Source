package com.petsource.model;

/**
 * Created by Daniel on 11/22/2016.
 */

public class Transaction {
    private String iduser;
    private String idpet;
    private String date;
    private String idshop;
    private String price;
    private int type;
    private int isWashing;
    private int isNailclipping;
    private int isTrimming;
    private String status;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdpet() {
        return idpet;
    }

    public void setIdpet(String idpet) {
        this.idpet = idpet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdshop() {
        return idshop;
    }

    public void setIdshop(String idshop) {
        this.idshop = idshop;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsWashing() {
        return isWashing;
    }

    public void setIsWashing(int isWashing) {
        this.isWashing = isWashing;
    }

    public int getIsNailclipping() {
        return isNailclipping;
    }

    public void setIsNailclipping(int isNailclipping) {
        this.isNailclipping = isNailclipping;
    }

    public int getIsTrimming() {
        return isTrimming;
    }

    public void setIsTrimming(int isTrimming) {
        this.isTrimming = isTrimming;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
