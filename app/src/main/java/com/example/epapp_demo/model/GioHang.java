package com.example.epapp_demo.model;

import java.io.Serializable;

class GioHang implements Serializable {
    private String StoreID;
    private String MonAnID;
    private String NameMonAn;
    private int SoLuong;
    private int Gia;
    private String HinhAnhGioHang;

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getHinhAnhGioHang() {
        return HinhAnhGioHang;
    }

    public void setHinhAnhGioHang(String hinhAnhGioHang) {
        HinhAnhGioHang = hinhAnhGioHang;
    }

    public GioHang() {
    }

    public GioHang(String storeID, String monAnID, String nameMonAn, int soLuong, int gia, String hinhAnhGioHang) {
        StoreID = storeID;
        MonAnID = monAnID;
        NameMonAn = nameMonAn;
        SoLuong = soLuong;
        Gia = gia;
        HinhAnhGioHang = hinhAnhGioHang;
    }
}
