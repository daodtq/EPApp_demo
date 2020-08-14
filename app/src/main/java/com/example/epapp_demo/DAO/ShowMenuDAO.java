package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.Hoat_Dong_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.fragment.ShowMenuStoreFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.example.epapp_demo.model.MonAn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ShowMenuDAO {
    DatabaseReference mDatabase;
    Context context;
    String CuaHangID;
    public ShowMenuDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("MonAn");
        this.context = context;
    }

    public ArrayList<MonAn> getMonAnByCuaHangID(String idCuaHang) {
        final ArrayList<MonAn> list = new ArrayList<MonAn>();
        mDatabase.orderByChild("storeID").equalTo(idCuaHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ds.getKey();
                    MonAn monAn = ds.getValue(MonAn.class);
                    Log.d("ab1", monAn.getMonAnID());
                    list.add(monAn);

                }
                ShowMenuStoreFragment.showMenuStoreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}
