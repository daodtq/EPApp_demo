package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.epapp_demo.QlyCuaHangActivity;
import com.example.epapp_demo.adapter.DonHangApdapter;
import com.example.epapp_demo.fragment.DonHangfragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DonHangDAO {
    DatabaseReference mDatabase;
    Context context;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    public DonHangDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("DonHang");
        this.context = context;
    }
    public ArrayList<DonHang> getAll(final String id) {
        final ArrayList<DonHang> list = new ArrayList<DonHang>();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //convert ra đối tượng HoaDon
                    DonHang cuaHang = data.getValue(DonHang.class);
                    if (cuaHang.getUserID().equalsIgnoreCase(id)) {
                        // taikhoanAdapter.add(hoaDon);
                        Log.d("a1", String.valueOf(list));
                        list.add(cuaHang);

                    }
                }
                DonHangfragment.donHangApdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return list;
    }

    public ArrayList<DonHang> getDonByKhachID(String idKhachHang) {
        final ArrayList<DonHang> list = new ArrayList<DonHang>();
        mDatabase.orderByChild("userID").equalTo(idKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ds.getKey();
                    DonHang hd = ds.getValue(DonHang.class);
                    Log.d("ab1", hd.getStoreID());
                    list.add(hd);

                }
                DonHangfragment.donHangApdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }


}
