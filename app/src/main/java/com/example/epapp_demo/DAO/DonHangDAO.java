package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.DonHangfragment;
import com.example.epapp_demo.fragment.Hoat_Dong_Cua_Hang_Fragment;
import com.example.epapp_demo.model.DonHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangDAO {
    DatabaseReference mDatabase;
    Context context;
    public DonHangDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("DonHang");
        this.context = context;
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
    public ArrayList<DonHang> getDonByCuaHangID(String idCuaHang) {
        final ArrayList<DonHang> list = new ArrayList<DonHang>();
        mDatabase.orderByChild("storeID").equalTo(idCuaHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ds.getKey();
                    DonHang hd = ds.getValue(DonHang.class);
                    Log.d("ab1", hd.getStoreID());
                    list.add(hd);

                }
                Hoat_Dong_Cua_Hang_Fragment.donHangApdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }


}
