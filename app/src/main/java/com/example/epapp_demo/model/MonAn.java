package com.example.epapp_demo.model;

import java.io.Serializable;

public class MonAn implements Serializable {
    private String MonAnID;
    private String NameMonAn;
    private int GiaMonAn;
    private String HinhAnhMonAn;
    private String StoreID;
    private String PhanLoaiID;
    private String MoTa;

    public MonAn() {
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "MonAnID='" + MonAnID + '\'' +
                ", NameMonAn='" + NameMonAn + '\'' +
                ", GiaMonAn=" + GiaMonAn +
                ", HinhAnhMonAn='" + HinhAnhMonAn + '\'' +
                ", StoreID='" + StoreID + '\'' +
                ", PhanLoaiID='" + PhanLoaiID + '\'' +
                ", MoTa='" + MoTa + '\'' +
                '}';
    }

    public String getMonAnID(String maSach) {
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

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getPhanLoaiID() {
        return PhanLoaiID;
    }

    public void setPhanLoaiID(String phanLoaiID) {
        PhanLoaiID = phanLoaiID;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public MonAn(String monAnID, String nameMonAn, int giaMonAn, String hinhAnhMonAn, String storeID, String phanLoaiID, String moTa) {
        MonAnID = monAnID;
        NameMonAn = nameMonAn;
        GiaMonAn = giaMonAn;
        HinhAnhMonAn = hinhAnhMonAn;
        StoreID = storeID;
        PhanLoaiID = phanLoaiID;
        MoTa = moTa;
    }
}
