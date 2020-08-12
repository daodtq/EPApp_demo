package com.example.epapp_demo.model;

import java.io.Serializable;
import java.util.List;

public class CuaHang implements Serializable {

    private String StoreID;
    private String StoreMail;
    private String StorePass;
    private List StoreMonAn;
    private String StoreName;
    private String StoreDiaChi;
    private Double StoreDanhGia;
    private String StoreHinhAnh;
    private Double StoreViDo;
    private Double StoreKinhDo;
    private int PhanQuyen;

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getStoreMail() {
        return StoreMail;
    }

    public void setStoreMail(String storeMail) {
        StoreMail = storeMail;
    }

    public String getStorePass() {
        return StorePass;
    }

    public void setStorePass(String storePass) {
        StorePass = storePass;
    }

    public List getStoreMonAn() {
        return StoreMonAn;
    }

    public void setStoreMonAn(List storeMonAn) {
        StoreMonAn = storeMonAn;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreDiaChi() {
        return StoreDiaChi;
    }

    public void setStoreDiaChi(String storeDiaChi) {
        StoreDiaChi = storeDiaChi;
    }

    public Double getStoreDanhGia() {
        return StoreDanhGia;
    }

    public void setStoreDanhGia(Double storeDanhGia) {
        StoreDanhGia = storeDanhGia;
    }

    public String getStoreHinhAnh() {
        return StoreHinhAnh;
    }

    public void setStoreHinhAnh(String storeHinhAnh) {
        StoreHinhAnh = storeHinhAnh;
    }

    public Double getStoreViDo() {
        return StoreViDo;
    }

    public void setStoreViDo(Double storeViDo) {
        StoreViDo = storeViDo;
    }

    public Double getStoreKinhDo() {
        return StoreKinhDo;
    }

    public void setStoreKinhDo(Double storeKinhDo) {
        StoreKinhDo = storeKinhDo;
    }

    public int getPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        PhanQuyen = phanQuyen;
    }

    public CuaHang() {
    }

    public CuaHang(String storeID, String storeMail, String storePass, List storeMonAn, String storeName, String storeDiaChi, Double storeDanhGia, String storeHinhAnh, Double storeViDo, Double storeKinhDo, int phanQuyen) {
        StoreID = storeID;
        StoreMail = storeMail;
        StorePass = storePass;
        StoreMonAn = storeMonAn;
        StoreName = storeName;
        StoreDiaChi = storeDiaChi;
        StoreDanhGia = storeDanhGia;
        StoreHinhAnh = storeHinhAnh;
        StoreViDo = storeViDo;
        StoreKinhDo = storeKinhDo;
        PhanQuyen = phanQuyen;
    }

}
