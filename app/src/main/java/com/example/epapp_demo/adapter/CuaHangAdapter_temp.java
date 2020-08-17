package com.example.epapp_demo.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang_temp;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


public class CuaHangAdapter_temp extends ArrayAdapter<CuaHang_temp> {
    Activity context;
    int resource;
    List<CuaHang_temp> objects;
    private CuaHangAdapter_temp.OnCuaHangGanClickListener mListener;
    public void setOnCuaHangGanItemClickListener (CuaHangAdapter_temp.OnCuaHangGanClickListener onCuaHangGanItemClickListener){
        mListener = onCuaHangGanItemClickListener;
    }

    public CuaHangAdapter_temp(Activity context, int resource, List<CuaHang_temp> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_cuahang_gan, null);
            viewHolder.img_cuahang = convertView.findViewById(R.id.img_cuahang);
            viewHolder.tv_tenquan = convertView.findViewById(R.id.tv_tenquan);
            viewHolder.tv_diachi = convertView.findViewById(R.id.tv_address);
            viewHolder.tv_rating = convertView.findViewById(R.id.tv_rating);
            viewHolder.tv_khoangcach = convertView.findViewById(R.id.tv_khoangcach);
            viewHolder.item_cuahang = convertView.findViewById(R.id.item_cuahnag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CuaHang_temp cuaHang = this.objects.get(position);
        if (cuaHang.getTencuahang().length() > 20) {
            viewHolder.tv_tenquan.setText(cuaHang.getTencuahang().substring(0, 20) + " ...");
        } else {
            viewHolder.tv_tenquan.setText(cuaHang.getTencuahang());
        }
        if (cuaHang.getDiachi().length() > 30) {
            viewHolder.tv_diachi.setText(cuaHang.getDiachi().substring(0, 30) + " ...");
        } else {
            viewHolder.tv_diachi.setText(cuaHang.getDiachi());
        }
        viewHolder.tv_rating.setText(String.valueOf(cuaHang.getRating()));
        try {
            Picasso.with(context).load(cuaHang.getHinhanh()).into(viewHolder.img_cuahang);
        } catch (Exception e) {

        }
        viewHolder.item_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCuaHangGanItemClick(position);
            }
        });

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            viewHolder.tv_khoangcach.setText(distanceBetween2Points(location.getLatitude(), location.getLongitude(), cuaHang.getLatitude(), cuaHang.getLongitude()) + " km");
        }
        return convertView;
    }


    static class ViewHolder implements OnCuaHangGanClickListener {
        ImageView img_cuahang;
        TextView tv_tenquan, tv_diachi, tv_rating, tv_khoangcach;
        LinearLayout item_cuahang;

        @Override
        public void onCuaHangGanItemClick(int position) {

        }
    }

    public static String distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
        double dLat = (la2 - la1) * (Math.PI / 180);
        double dLon = (lo2 - lo1) * (Math.PI / 180);
        double la1ToRad = la1 * (Math.PI / 180);
        double la2ToRad = la2 * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6400 * c;

        //format number
        NumberFormat formatter = new DecimalFormat("#0.0");
        return formatter.format(d);
    }
    public interface OnCuaHangGanClickListener {
        void onCuaHangGanItemClick(int position);
//        void onPlaceFavoriteClick(Place place);
    }
}
