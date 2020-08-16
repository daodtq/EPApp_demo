package com.example.epapp_demo.DAO;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.epapp_demo.fragment.ListRestaurantFragment;
import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.CuaHang_temp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static com.example.epapp_demo.fragment.HomeFragment.cuaHangAdapter_temp;

public class CuaHangDAO implements LocationListener {
    DatabaseReference mDatabase;
    Context context;
    String CuaHangID;
    List<CuaHang_temp> temp = new ArrayList<>();
    public CuaHangDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("CuaHang");
        this.context = context;
    }
    //đo khoảng cách giữa hai điểm
    public static double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
        double dLat = (la2 - la1) * (Math.PI / 180);
        double dLon = (lo2 - lo1) * (Math.PI / 180);
        double la1ToRad = la1 * (Math.PI / 180);
        double la2ToRad = la2 * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6400 * c;
        return d;
    }

    public ArrayList<CuaHang> getAll() {

        final ArrayList<CuaHang> list = new ArrayList<CuaHang>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<   DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        CuaHang sach = next.getValue(CuaHang.class);
                        list.add(sach);
                       QlyCuaHangFragment.cuaHangAdapte.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
    public ArrayList<CuaHang> getShowCuahang() {

        final ArrayList<CuaHang> list = new ArrayList<CuaHang>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        CuaHang sach = next.getValue(CuaHang.class);
                        list.add(sach);
                        ListRestaurantFragment.showcuaHangAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }

    public void update(final CuaHang s) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("storeID").getValue(String.class).equalsIgnoreCase(s.getStoreID())) {
                        CuaHangID = data.getKey();
                        Log.d("getKey", "onCreate: key :" + CuaHangID);
                        mDatabase.child(CuaHangID).setValue(s)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("update", "update Thanh cong");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("update", "update That bai");
                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //get CuaHang within 10km
    public List<CuaHang_temp> getTemp(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Permission already Granted
            //Do your work here
            //Perform operations here only which requires permission
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) this);
        final Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temp.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //convert ra đối tượng HoaDon
                    CuaHang cuaHang = data.getValue(CuaHang.class);
                    if (location != null) {
                        double khoangcach = distanceBetween2Points(location.getLatitude(), location.getLongitude(), cuaHang.getStoreViDo(), cuaHang.getStoreKinhDo());
                        if (khoangcach < 10) {
                            temp.add(new CuaHang_temp(
                                    cuaHang.getStoreID(),
                                    cuaHang.getStoreName(),
                                    cuaHang.getStoreDiaChi(),
                                    cuaHang.getStoreDanhGia(),
                                    cuaHang.getStoreHinhAnh(),
                                    cuaHang.getStoreViDo(),
                                    cuaHang.getStoreKinhDo(),
                                    khoangcach
                            ));
                        }
                    }
                }

                //sort khoảng cách đến quán ăn theo thứ tự tăng dần
                Collections.sort(temp, new Comparator<CuaHang_temp>() {
                    @Override
                    public int compare(CuaHang_temp o1, CuaHang_temp o2) {
                        return Double.valueOf(o1.getKhoangcach()).compareTo(o2.getKhoangcach());
                    }
                });

                cuaHangAdapter_temp.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
