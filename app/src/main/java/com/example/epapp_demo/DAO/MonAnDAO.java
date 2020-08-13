package com.example.epapp_demo.DAO;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.MonAn;
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
                    Iterable<   DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        MonAn sach = next.getValue(MonAn.class);
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
}
