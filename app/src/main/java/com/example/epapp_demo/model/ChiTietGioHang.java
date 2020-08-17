package com.example.epapp_demo.model;

public class ChiTietGioHang {
    private int giohangId;
    private String MonAnId;
    private String tenMonAn;
    private int gia;
    private int soluong;
    private String hinh;

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(int giohangId, String monAnId, String tenMonAn, int gia, int soluong, String hinh) {
        this.giohangId = giohangId;
        MonAnId = monAnId;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.soluong = soluong;
        this.hinh = hinh;
    }

    public int getGiohangId() {
        return giohangId;
    }

    public String getMonAnId() {
        return MonAnId;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getGia() {
        return gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getHinh() {
        return hinh;
    }
}
