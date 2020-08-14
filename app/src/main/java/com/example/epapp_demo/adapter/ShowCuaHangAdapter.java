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

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowCuaHangAdapter extends RecyclerView.Adapter<ShowCuaHangAdapter.ViewHolder> {

    Context context;
    private List<CuaHang> cuaHangs = new ArrayList<>();
    ArrayList<CuaHang> cuahang;
    CuaHangDAO cuaHangDAO;
    private ShowCuaHangAdapter.OnStoreClickListener mListener;
    public void setOnStoreItemClickListener (ShowCuaHangAdapter.OnStoreClickListener onStoreItemClickListener){
        mListener = onStoreItemClickListener;
    }
    public ShowCuaHangAdapter(ArrayList<CuaHang> cuahang, Context context){
        this.cuahang = cuahang;
        this.context = context;
        cuaHangDAO = new CuaHangDAO(context);
    }


    @NonNull
    @Override
    public ShowCuaHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.quanan_one_item,parent,false);
        cuaHangDAO = new CuaHangDAO(context);
        return new ShowCuaHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(cuahang.get(position).getStoreHinhAnh()).into(holder.ivStorePicture);
        holder.storeName.setText(cuahang.get(position).getStoreName());
        holder.storeLocation.setText(cuahang.get(position).getStoreDiaChi());
        holder.storeRating.setText(String.valueOf(cuahang.get(position).getStoreDanhGia()));
    }
    @Override
    public int getItemCount() {
        return cuahang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView storeName, storeLocation, storeRating ;
        public ImageView ivStorePicture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.place_name);
            storeLocation = itemView.findViewById(R.id.place_location);
            storeRating = itemView.findViewById(R.id.place_rating);
            ivStorePicture = itemView.findViewById(R.id.ivStorePicture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onStoreItemClick(getPosition());
                }
            });
        }
        @Override
        public void onClick(View v) {

        }
    }
    public interface OnStoreClickListener {
        void onStoreItemClick(int position);
//        void onPlaceFavoriteClick(Place place);
    }

}
