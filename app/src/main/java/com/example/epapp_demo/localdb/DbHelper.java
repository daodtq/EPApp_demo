package com.example.epapp_demo.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.epapp_demo.model.ChiTietGioHang;
import com.example.epapp_demo.model.GioHang;
import com.example.epapp_demo.model.MonAn;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static ArrayList<GioHang> giohang = new ArrayList<>();
    SQLiteDatabase db;
    public DbHelper(Context context){
        super(context,"giohang",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "create table giohang(GioHangId integer primary key autoincrement, " +
                "DonHangId text, MonAnId text, SoLuong integer)";
        db.execSQL(sql);
        sql= "create table MonAn(MonAnId text primary key, " +
                "NameMonAn text, GiaMonAn integer, HinhAnhMonAn text)";
        db.execSQL(sql);
    }

    public void insertGH(GioHang gh){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DonHangId", gh.getStoreID());
        values.put("MonAnId", gh.getMonAnID());
        values.put("SoLuong", gh.getSoLuong());
        db.insert("giohang", null, values);
    }

    public void insertMonAn(MonAn monAn){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MonAnId", monAn.getMonAnID());
        values.put("NameMonAn", monAn.getNameMonAn());
        values.put("GiaMonAn", monAn.getGiaMonAn());
        values.put("HinhAnhMonAn", monAn.getHinhAnhMonAn());
        db.insert("MonAn", null, values);
    }

    public void delete(String id){
        db = this.getWritableDatabase();
        db.delete("giohang", "MonAnId=?",new String[]{id});
        db.delete("MonAn", "MonAnId=?",new String[]{id});
    }

    public ArrayList<ChiTietGioHang> listGioHang(){
        ArrayList<ChiTietGioHang> list = new ArrayList<>();
        db = this.getReadableDatabase();
        String sql = "Select g.GioHangId, g.MonAnId, m.NameMonAn, m.GiaMonAn, g.SoLuong, m.HinhAnhMonAn " +
                " from giohang g inner join MonAn m on g.MonAnId=m.MonAnId";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            ChiTietGioHang ctgh = new ChiTietGioHang(cs.getInt(0), cs.getString(1), cs.getString(2)
                    , cs.getInt(3), cs.getInt(4), cs.getString(5));
            list.add(ctgh);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
