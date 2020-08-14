package com.example.epapp_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.MonAn;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowMenuStoreAdapter extends RecyclerView.Adapter<ShowMenuStoreAdapter.ViewHolder> {
    Context context;
    private List<MonAn> monAns = new ArrayList<>();
    ArrayList<MonAn> monAn;
    CuaHangDAO cuaHangDAO;
    RecyclerView rcvMenu;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
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
        View view= LayoutInflater.from(context).inflate(R.layout.monan_one_item,parent,false);
        cuaHangDAO = new CuaHangDAO(context);
        return new ShowMenuStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(monAn.get(position).getHinhAnhMonAn()).into(holder.ivHinhMonAn);
        holder.tenMonAn.setText(monAn.get(position).getNameMonAn());
        holder.moTaMonAn.setText(monAn.get(position).getMoTa());
        holder.giaMonAn.setText(formatter.format(monAn.get(position).getGiaMonAn())+" VND");
    }

    @Override
    public int getItemCount() {
        return monAn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tenMonAn, moTaMonAn , giaMonAn;
        ImageView ivHinhMonAn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvMenu = itemView.findViewById(R.id.recyclerStoreMenu);
            tenMonAn = itemView.findViewById(R.id.item_ten_MA);
            moTaMonAn = itemView.findViewById(R.id.item_moTa_MA);
            giaMonAn = itemView.findViewById(R.id.item_gia_MA);
            ivHinhMonAn = itemView.findViewById(R.id.anh_MA);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onMenuItemClick(getPosition());
                }
            });
        }
        @Override
        public void onClick(View v) {

        }
    }
    public interface OnMenuClickListener {
        void onMenuItemClick(int position);
    }
}
