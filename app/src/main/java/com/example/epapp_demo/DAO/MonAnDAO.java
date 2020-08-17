package com.example.epapp_demo.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.Hoat_Dong_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.Mon_An_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.fragment.ShowMenuStoreFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
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

    public ArrayList<MonAn> getAll(String idCuaHang) {
        final ArrayList<MonAn> list = new ArrayList<MonAn>();
        mDatabase.orderByChild("storeID").equalTo(idCuaHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ds.getKey();
                    MonAn hd = ds.getValue(MonAn.class);
                    list.add(hd);

                }
                Mon_An_Cua_Hang_Fragment.monAnAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }


    public ArrayList<MonAn> getToanBoMonAn() {

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
        String MaSach = mDatabase.child(monAnID).getKey();
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

    public ArrayList<MonAn> getAllByIDMonAn(String idMonAn) {
        final ArrayList<MonAn> list = new ArrayList<MonAn>();
        mDatabase.orderByChild("monAnID").equalTo(idMonAn).addValueEventListener(new ValueEventListener() {
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
