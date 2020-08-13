package com.example.epapp_demo.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.DonHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CuaHangAdapter extends RecyclerView.Adapter<CuaHangAdapter.ViewHolder> {

    Context context;
    ArrayList<CuaHang> cuahang;
    CuaHangDAO cuaHangDAO;

    public CuaHangAdapter(ArrayList<CuaHang> cuahang, Context context){
        this.cuahang = cuahang;
        this.context = context;
        cuaHangDAO = new CuaHangDAO(context);
    }

    @NonNull
    @Override
    public CuaHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cuahang_one_item,parent,false);
        cuaHangDAO = new CuaHangDAO(context);
        return new CuaHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvidCH.setText(cuahang.get(position).getStoreID());
        holder.tvmailCH.setText(cuahang.get(position).getStoreMail());
        holder.tvNameCH.setText(cuahang.get(position).getStoreName());
        holder.tvpassCH.setText(cuahang.get(position).getStorePass());
        holder.tvDiaChiCH.setText(cuahang.get(position).getStoreDiaChi());

    }

    @Override
    public int getItemCount() {
        return cuahang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvidCH, tvmailCH, tvNameCH, tvpassCH, tvDiaChiCH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidCH = itemView.findViewById(R.id.tvidCH);
            tvmailCH = itemView.findViewById(R.id.tvmailCH);
            tvNameCH = itemView.findViewById(R.id.tvNameCH);
            tvpassCH = itemView.findViewById(R.id.tvpassCH);
            tvDiaChiCH = itemView.findViewById(R.id.tvDiaChiCH);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
