package com.example.epapp_demo.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.fragment.GioHangFragment;
import com.example.epapp_demo.fragment.ShowMenuStoreFragment;
import com.example.epapp_demo.localdb.DbHelper;

import com.example.epapp_demo.model.ChiTietGioHang;
import com.example.epapp_demo.model.GioHang;
import com.example.epapp_demo.model.PhanLoai;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    ArrayList<ChiTietGioHang> list;
    Context context;

    public GioHangAdapter(ArrayList<ChiTietGioHang> list, Context context){
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.giohang_one_item,parent,false);

        return new GioHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        final DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.name.setText(list.get(position).getTenMonAn());
        holder.soluong.setText("Số lượng: "+list.get(position).getSoluong()+"");
        holder.dongia.setText("Đơn giá: "+formatter.format(list.get(position).getGia()) + "VNĐ");
        holder.tongtien.setText("Tổng: "+formatter.format(list.get(position).getSoluong() * list.get(position).getGia())+ "VNĐ");
        Picasso.with(context).load(list.get(position).getHinh()).into(holder.hinh);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, soluong, tongtien, dongia;
        public ImageView hinh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tenItemGH);
            soluong = itemView.findViewById(R.id.soLuongItemGH);
            tongtien = itemView.findViewById(R.id.tongtienItemGH);
            hinh = itemView.findViewById(R.id.anhGH);
            dongia = itemView.findViewById(R.id.tienItemMon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            final int position = getLayoutPosition();
            if (getAdapterPosition() == RecyclerView.NO_POSITION);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view1 = layoutInflater.inflate(R.layout.delete_alert_dialog,null);


            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String s = list.get(position).getMonAnId();
                    DbHelper dbHelper = new DbHelper(context);
                    Log.d("1122",s);
                    dbHelper.delete(s);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    GioHangFragment myFragment = new GioHangFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, myFragment).addToBackStack(null).commit();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setView(view1);
            builder.show();

        }
    }

}
