package com.example.epapp_demo.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int gioHangId;
    private String DonHangID;
    private String MonAnID;
//    private String NameMonAn;
    private int SoLuong;
//    private int Gia;
//    private String HinhAnhGioHang;

    public int getGioHangId() {
        return gioHangId;
    }

    public void setGioHangId(int gioHangId) {
        this.gioHangId = gioHangId;
    }

    public String getStoreID() {
        return DonHangID;
    }

    public void setStoreID(String storeID) {
        DonHangID = storeID;
    }

    public String getMonAnID() {
        return MonAnID;
    }

    public void setMonAnID(String monAnID) {
        MonAnID = monAnID;
    }

//    public String getNameMonAn() {
//        return NameMonAn;
//    }
//
//    public void setNameMonAn(String nameMonAn) {
//        NameMonAn = nameMonAn;
//    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

//    public int getGia() {
//        return Gia;
//    }
//
//    public void setGia(int gia) {
//        Gia = gia;
//    }
//
//    public String getHinhAnhGioHang() {
//        return HinhAnhGioHang;
//    }
//
//    public void setHinhAnhGioHang(String hinhAnhGioHang) {
//        HinhAnhGioHang = hinhAnhGioHang;
//    }

    public GioHang() {
    }

    public GioHang(String DonHangID, String monAnID, int soLuong) {
        this.DonHangID = DonHangID;
        MonAnID = monAnID;
        SoLuong = soLuong;
    }

    public GioHang(int gioHangId, String DonHangID, String monAnID, int soLuong) {
        this.gioHangId = gioHangId;
        this.DonHangID = DonHangID;
        MonAnID = monAnID;
        SoLuong = soLuong;
    }
}
