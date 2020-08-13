package com.example.epapp_demo.model;

import java.io.Serializable;

public class MonAn implements Serializable {
    private String MonAnID;
    private String NameMonAn;
    private int GiaMonAn;
    private String HinhAnhMonAn;

    public String getMonAnID() {
        return MonAnID;
    }

    public void setMonAnID(String monAnID) {
        MonAnID = monAnID;
    }

    public String getNameMonAn() {
        return NameMonAn;
    }

    public void setNameMonAn(String nameMonAn) {
        NameMonAn = nameMonAn;
    }

    public int getGiaMonAn() {
        return GiaMonAn;
    }

    public void setGiaMonAn(int giaMonAn) {
        GiaMonAn = giaMonAn;
    }

    public String getHinhAnhMonAn() {
        return HinhAnhMonAn;
    }

    public void setHinhAnhMonAn(String hinhAnhMonAn) {
        HinhAnhMonAn = hinhAnhMonAn;
    }

    public MonAn() {
    }

    public MonAn(String monAnID, String nameMonAn, int giaMonAn, String hinhAnhMonAn) {
        MonAnID = monAnID;
        NameMonAn = nameMonAn;
        GiaMonAn = giaMonAn;
        HinhAnhMonAn = hinhAnhMonAn;
    }
}
