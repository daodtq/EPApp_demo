package com.example.epapp_demo.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.MonAnDAO;
import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.MonAn;
import com.example.epapp_demo.model.PhanLoai;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {

    List<MonAn> list;
    Context context;
    MonAnDAO monAnDAO;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("MonAn");
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
        holder.gia_MA.setText(list.get(position).getGiaMonAn()+" VNĐ");
        holder.moTa_MA.setText(list.get(position).getMoTa());

        final MonAnDAO monAnDAO = new MonAnDAO(context);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final ArrayList<PhanLoai> listPL = new PhanLoaiDAO(context).getAllspn();
        //delete
        holder.item_mon_an.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("THÔNG BÁO!");
                builder.setMessage("Bạn có muốn xóa không?");

                //btn Yes
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String id = list.get(position).getMonAnID();
                        monAnDAO.delete(id);

                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                //btn No
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog myAlert = builder.create();
                myAlert.show();
                return false;
            }
        });

        // edit
        holder.item_mon_an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.add_monan,null);

                final EditText tenmon = view1.findViewById(R.id.edtTenMon);
                final Spinner spn = view1.findViewById(R.id.spnTheLoai);
                final EditText mota = view1.findViewById(R.id.edtMotaMon);
                final EditText gia = view1.findViewById(R.id.edtGiaMon);
                final EditText url = view1.findViewById(R.id.edtUrlMon);

                MonAn ma = list.get(position);
                tenmon.setText(ma.getNameMonAn());
                mota.setText(ma.getMoTa());
                gia.setText(String.valueOf(ma.getGiaMonAn()));
                url.setText(ma.getHinhAnhMonAn());

                //Test
                ArrayAdapter adapter = new ArrayAdapter (context, android.R.layout.simple_spinner_item, listPL);
                spn.setAdapter(adapter);

                int idxLS = -1;
                for (int i = 0; i < listPL.size(); i++){
                    if(listPL.get(i).getLoaiID().toString().equalsIgnoreCase(ma.getPhanLoaiID())){
                        idxLS = i;
                        break;
                    }
                }
                spn.setSelection(idxLS);

                builder.setView(view1);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String tenmon1 = tenmon.getText().toString();
                        String mota1 = mota.getText().toString();
                        int gia1 = Integer.parseInt(gia.getText().toString());
                        String url1 = url.getText().toString();
                        PhanLoai loai = (PhanLoai) spn.getSelectedItem();
                        String matheloai = loai.getLoaiID();
                        String idMonAn = list.get(position).getMonAnID();
                        String a = mAuth.getCurrentUser().getUid();
                        MonAn s = new MonAn(idMonAn,tenmon1,gia1,url1,a,matheloai,mota1);
                        monAnDAO.update(s, idMonAn);
                    }
                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView ten_Ma, moTa_MA, gia_MA;
        public ImageView anh_MA;
        LinearLayout item_mon_an;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_Ma = itemView.findViewById(R.id.item_ten_MA);
            moTa_MA = itemView.findViewById(R.id.item_moTa_MA);
            gia_MA = itemView.findViewById(R.id.item_gia_MA);
            anh_MA = itemView.findViewById(R.id.anh_MA);
            item_mon_an = itemView.findViewById(R.id.item_mon_an);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
