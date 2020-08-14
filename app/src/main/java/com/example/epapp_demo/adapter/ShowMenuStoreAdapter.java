package com.example.epapp_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class ShowMenuStoreAdapter extends RecyclerView.Adapter<ShowMenuStoreAdapter.ViewHolder> {
    Context context;
    private List<MonAn> monAns = new ArrayList<>();
    ArrayList<MonAn> monAn;
    CuaHangDAO cuaHangDAO;
    RecyclerView rcvMenu;
    private ShowMenuStoreAdapter.OnMenuClickListener mListener;
    public void setOnMenuItemClickListener (ShowMenuStoreAdapter.OnMenuClickListener onMenuItemClickListener){
        mListener = onMenuItemClickListener;
    }
    public ShowMenuStoreAdapter(ArrayList<MonAn> monAn, Context context){
        this.monAn = monAn;
        this.context = context;
        cuaHangDAO = new CuaHangDAO(context);
    }

    @NonNull
    @Override
    public ShowMenuStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.quanan_one_item,parent,false);
        cuaHangDAO = new CuaHangDAO(context);
        return new ShowMenuStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.storeName.setText(String.valueOf(monAn.get(position).getGiaMonAn()));
        holder.storeLocation.setText(monAn.get(position).getHinhAnhMonAn());
        holder.storeRating.setText(String.valueOf(monAn.get(position).getNameMonAn()));
        holder.storeDelivery.setText(monAn.get(position).getMonAnID());
    }

    @Override
    public int getItemCount() {
        return monAn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView storeName, storeLocation, storeRating, storeDelivery;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvMenu = itemView.findViewById(R.id.recyclerStoreMenu);
            storeName = itemView.findViewById(R.id.place_name);
            storeLocation = itemView.findViewById(R.id.place_location);
            storeRating = itemView.findViewById(R.id.place_rating);
            storeDelivery = itemView.findViewById(R.id.place_delivery);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onMenuItemClick(getPosition());
//                }
//            });
        }
        @Override
        public void onClick(View v) {

        }
    }
    public interface OnMenuClickListener {
        void onMenuItemClick(int position);
    }
}
