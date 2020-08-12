package com.example.epapp_demo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.BottomNavigation;
import com.example.epapp_demo.DAO.DonHangDAO;
import com.example.epapp_demo.LoginActivity;
import com.example.epapp_demo.OnlyCuaHangActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.example.epapp_demo.model.KhachHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.List;

public class DonHangApdapter extends RecyclerView.Adapter<DonHangApdapter.ViewHolder> {

    List<DonHang> list;
    Context context;
    DonHangDAO donHangDAO;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public DonHangApdapter(List<DonHang> list, Context context){
        this.list = list;
        this.context = context;
        donHangDAO = new DonHangDAO(context);
    }

    @NonNull
    @Override
    public DonHangApdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.donhang_one_item,parent,false);
        donHangDAO= new DonHangDAO(context);
        return new DonHangApdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DonHangApdapter.ViewHolder holder, final int position) {

        String i = list.get(position).getStoreID();
        holder.ivID.setText(list.get(position).getDHID());
        holder.ivThoiGian.setText(list.get(position).getDHThoiGian());
        holder.ivTrangThai.setText(list.get(position).getDHTrangThai());
        mData.child("CuaHang").child(i).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                CuaHang user = dataSnapshot.getValue(CuaHang.class);

                holder.ivCuaHang.setText(user.getStoreName());

                Log.d("abc1","" + list.get(position).getDHID());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView ivID, ivCuaHang, ivThoiGian, ivTrangThai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivID = itemView.findViewById(R.id.tvmaDonHang);
            ivCuaHang = itemView.findViewById(R.id.tvTenCuaHang);
            ivThoiGian = itemView.findViewById(R.id.tvNgaythuchien);
            ivTrangThai = itemView.findViewById(R.id.tvTrangthai);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
