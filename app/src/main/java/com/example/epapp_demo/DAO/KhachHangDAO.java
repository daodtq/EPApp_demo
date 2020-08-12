package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.fragment.QlyKhachHangFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class KhachHangDAO {
    DatabaseReference mDatabase;
    Context context;
    String KhachHangID;
    public KhachHangDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("KhachHang");
        this.context = context;
    }


    public ArrayList<KhachHang> getAll() {
        final ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        KhachHang sach = next.getValue(KhachHang.class);
                        list.add(sach);
                        QlyKhachHangFragment.khachHangAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }

    public void update(final KhachHang s) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userMail").getValue(String.class).equalsIgnoreCase(s.getUserMail())) {
                        KhachHangID = data.getKey();
                        Log.d("getKey", "onCreate: key :" + KhachHangID);
                        mDatabase.child(KhachHangID).setValue(s)
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

}
