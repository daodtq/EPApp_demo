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

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CuaHangAdapter extends BaseAdapter {

    Context context;
    ArrayList<CuaHang> cuahang;
    CuaHangDAO cuaHangDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("CuaHang");


    public CuaHangAdapter(Context context, ArrayList<CuaHang> cuahang) {
        this.context = context;
        this.cuahang = cuahang;
        cuaHangDAO = new CuaHangDAO(context);
    }


    @Override
    public int getCount() {
        return cuahang.size();
    }

    @Override
    public Object getItem(int position) {
        return cuahang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if (convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.cuahang_one_item, null);
            holder = new Viewholder();
            holder.tvidCH = convertView.findViewById(R.id.tvidCH);
            holder.tvmailCH = convertView.findViewById(R.id.tvmailCH);
            holder.tvNameCH = convertView.findViewById(R.id.tvNameCH);
            holder.tvpassCH = convertView.findViewById(R.id.tvpassCH);
            holder.tvDiaChiCH = convertView.findViewById(R.id.tvDiaChiCH);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }

        final CuaHang s = cuahang.get(position);



        holder.tvidCH.setText(s.getStoreID());
        holder.tvmailCH.setText(s.getStoreMail());
        holder.tvNameCH.setText(s.getStoreName());
        holder.tvpassCH.setText(s.getStorePass());
        holder.tvDiaChiCH.setText(s.getStoreDiaChi());

        return convertView;
    }
    class Viewholder {
        TextView tvidCH, tvmailCH, tvNameCH, tvpassCH, tvDiaChiCH;
    }
}
