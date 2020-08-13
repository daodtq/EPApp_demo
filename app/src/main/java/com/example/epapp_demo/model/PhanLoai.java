package com.example.epapp_demo.model;

import java.io.Serializable;

public class PhanLoai implements Serializable {
    private String LoaiID;
    private String NameLoai;
    private String mota;

    public PhanLoai() {
    }

    public PhanLoai(String loaiID, String nameLoai, String mota) {
        LoaiID = loaiID;
        NameLoai = nameLoai;
        this.mota = mota;
    }
    public String getLoaiID() {
        return LoaiID;
    }

    public void setLoaiID(String loaiID) {
        LoaiID = loaiID;
    }

    public String getNameLoai() {
        return NameLoai;
    }

    public void setNameLoai(String nameLoai) {
        NameLoai = nameLoai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void getLaoiID(String malaoi) {
    }

    public void getLoaiID(String maSach) {
    }
}

