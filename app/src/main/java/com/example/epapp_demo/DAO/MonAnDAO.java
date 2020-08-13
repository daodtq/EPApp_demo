package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.Mon_An_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.MonAn;
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

public class MonAnDAO {
    DatabaseReference mDatabase;
    Context context;
    String monAnID;
    public MonAnDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("MonAn");
        this.context = context;
    }

    public ArrayList<MonAn> getAll() {

        final ArrayList<MonAn> list = new ArrayList<MonAn>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        MonAn monAn = next.getValue(MonAn.class);
                        list.add(monAn);
                        Mon_An_Cua_Hang_Fragment.monAnAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
    public void update(final MonAn s, String id) {
        mDatabase.child(id).setValue(s)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("update", "update Thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("update", "update That bai");
            }
        });
    }
    public  void delete( String id){
        mDatabase.child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("delete", "delete Thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("delete", "delete That bai");
            }
        });

    }
    public void insert(MonAn s) {
        monAnID = mDatabase.push().getKey();
        String MaSach = mDatabase.child(monAnID).push().getKey();
        s.setMonAnID(MaSach);
        mDatabase.child(monAnID).setValue(s)
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
}
