package com.example.epapp_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.PhanLoai;

import java.util.ArrayList;

public class PhanLoaiAdapter extends RecyclerView.Adapter<PhanLoaiAdapter.ViewHolder> {

    Context context;
    ArrayList<PhanLoai> phanloai;
    PhanLoaiDAO phanLoaiDAO;

    public PhanLoaiAdapter(ArrayList<PhanLoai> phanloai, Context context){
        this.phanloai =phanloai;
        this.context = context;
        phanLoaiDAO = new PhanLoaiDAO(context);
    }

    public PhanLoaiAdapter(Context context) {
    }

    @NonNull
    @Override
    public PhanLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.loai_one_item,parent,false);
        phanLoaiDAO = new PhanLoaiDAO (context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhanLoaiAdapter.ViewHolder holder, int position) {

        holder.tvidloai.setText(phanloai.get(position).getLoaiID());
        holder.tvnameloai.setText(phanloai.get(position).getNameLoai());
        holder.tvmota.setText(phanloai.get(position).getMota());

    }

    @Override
    public int getItemCount() {
        return phanloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvidloai, tvnameloai, tvmota;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidloai = itemView.findViewById(R.id.tvLoaiid);
            tvnameloai = itemView.findViewById(R.id.Nameloai);
            tvmota = itemView.findViewById(R.id.tvMota);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
