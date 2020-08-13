package com.example.epapp_demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.MonAnDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.MonAn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {


    List<MonAn> list;
    Context context;
    MonAnDAO monAnDAO;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public MonAnAdapter(List<MonAn> list, Context context){
        this.list = list;
        this.context = context;
        monAnDAO = new MonAnDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.monan_one_item,parent,false);
        monAnDAO= new MonAnDAO(context);
        return new MonAnAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        String i = list.get(position).getStoreID();

        //  Thêm ảnh

        Picasso.with(context).load(list.get(position).getHinhAnhMonAn()).into(holder.anh_MA);


        holder.ten_Ma.setText(list.get(position).getNameMonAn());
        holder.gia_MA.setText(String.valueOf(list.get(position).getGiaMonAn())+" VNĐ");
        holder.moTa_MA.setText(list.get(position).getMoTa());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView ten_Ma, moTa_MA, gia_MA;
        public ImageView anh_MA;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_Ma = itemView.findViewById(R.id.item_ten_MA);
            moTa_MA = itemView.findViewById(R.id.item_moTa_MA);
            gia_MA = itemView.findViewById(R.id.item_gia_MA);
            anh_MA = itemView.findViewById(R.id.anh_MA);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
