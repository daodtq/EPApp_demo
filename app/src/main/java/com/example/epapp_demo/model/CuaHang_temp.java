package com.example.epapp_demo.model;

import java.io.Serializable;

public class CuaHang_temp implements Serializable {
    private String macuahang;
    private String tencuahang;
    private String diachi;
    private Double rating;
    private String hinhanh;
    private Double latitude;
    private Double longitude;
    private Double khoangcach;

    public CuaHang_temp(String macuahang, String tencuahang, String diachi, Double rating, String hinhanh, Double latitude, Double longitude, Double khoangcach) {
        this.macuahang = macuahang;
        this.tencuahang = tencuahang;
        this.diachi = diachi;
        this.rating = rating;
        this.hinhanh = hinhanh;
        this.latitude = latitude;
        this.longitude = longitude;
        this.khoangcach = khoangcach;
    }

    public CuaHang_temp() {
    }

    public String getMacuahang() {
        return macuahang;
    }

    public void setMacuahang(String macuahang) {
        this.macuahang = macuahang;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getKhoangcach() {
        return khoangcach;
    }

    public void setKhoangcach(Double khoangcach) {
        this.khoangcach = khoangcach;
    }
}
