package com.example.epapp_demo.model;

import java.io.Serializable;
import java.util.List;

public class DonHang implements Serializable {
    private String DHID;
    private String UserID;
    private String StoreID;
    private String DHTrangThai;
    private String DHThoiGian;

    public String getDHID() {
        return DHID;
    }

    public void setDHID(String DHID) {
        this.DHID = DHID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getDHTrangThai() {
        return DHTrangThai;
    }

    public void setDHTrangThai(String DHTrangThai) {
        this.DHTrangThai = DHTrangThai;
    }

    public String getDHThoiGian() {
        return DHThoiGian;
    }

    public void setDHThoiGian(String DHThoiGian) {
        this.DHThoiGian = DHThoiGian;
    }

    public DonHang() {
    }

    public DonHang(String DHID, String userID, String storeID, String DHTrangThai, String DHThoiGian) {
        this.DHID = DHID;
        UserID = userID;
        StoreID = storeID;
        this.DHTrangThai = DHTrangThai;
        this.DHThoiGian = DHThoiGian;
    }
}
