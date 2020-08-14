package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.PhanloaiFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.example.epapp_demo.model.PhanLoai;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class PhanLoaiDAO {
    DatabaseReference mDatabase;
    Context context;
    String PhanLoaiID;
    public PhanLoaiDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("PhanLoai");
        this.context = context;
    }

    public void insert(PhanLoai s) {
        PhanLoaiID = mDatabase.push().getKey();
        String MaSach = mDatabase.child(PhanLoaiID).push().getKey();
        s.getLoaiID(MaSach);
        mDatabase.child(PhanLoaiID).setValue(s)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d("insert", "insert Thanh cong");

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("insert", "insert That bai");
            }
        });
    }


    public ArrayList<PhanLoai> getAll() {
        final ArrayList<PhanLoai> list = new ArrayList<PhanLoai>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        PhanLoai pl = next.getValue(PhanLoai.class);
                        list.add(pl);
                        PhanloaiFragment.phanLoaiAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}