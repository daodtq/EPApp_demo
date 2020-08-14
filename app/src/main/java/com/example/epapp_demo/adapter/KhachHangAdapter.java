package com.example.epapp_demo.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.DAO.MonAnDAO;
import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.example.epapp_demo.model.MonAn;
import com.example.epapp_demo.model.PhanLoai;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {

    Context context;
    ArrayList<KhachHang> list;
    KhachHangDAO khachHangDAO;



    public KhachHangAdapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.list = list;
        khachHangDAO = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.khachhang_one_item,parent,false);
        khachHangDAO = new KhachHangDAO(context);
        return new KhachHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIDKH.setText(list.get(position).getUserID());
        holder.tvNGaySinhKH.setText(list.get(position).getUserNgaySinh());
        holder.tvTenKH.setText(list.get(position).getUserName());
        holder.tvEmailKH.setText(list.get(position).getUserMail());
        holder.tvSDTKH.setText(list.get(position).getUserSDT());
        holder.tvDiaChiKH.setText(list.get(position).getUserDiaChi());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvIDKH, tvNGaySinhKH, tvTenKH, tvEmailKH, tvSDTKH,tvDiaChiKH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDKH = itemView.findViewById(R.id.tvIDKH);
            tvNGaySinhKH = itemView.findViewById(R.id.tvNGaySinhKH);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvEmailKH = itemView.findViewById(R.id.tvEmailKH);
            tvSDTKH = itemView.findViewById(R.id.tvSDTKH);
            tvDiaChiKH = itemView.findViewById(R.id.tvDiaChiKH);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
