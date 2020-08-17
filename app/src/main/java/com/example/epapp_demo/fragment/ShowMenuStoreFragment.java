package com.example.epapp_demo.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.ShowMenuDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.ShowCuaHangAdapter;
import com.example.epapp_demo.adapter.ShowMenuStoreAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.example.epapp_demo.model.MonAn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ShowMenuStoreFragment extends Fragment {
    public static String idStore;
    public static ShowMenuStoreAdapter showMenuStoreAdapter;
    RecyclerView recyclerMenu;
    String idCuahangGan;
    CuaHangDAO cuaHangDAO;
    Integer soluong, tonggia;
    ShowMenuDAO showMenuDAO;
    ArrayList <MonAn> list = new ArrayList<>();
    ArrayList <CuaHang> listCuaHang = new ArrayList<>();
    public ShowMenuStoreFragment(String idStore) {
        this.idStore = idStore;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_one_store_fragment, container, false);
        final TextView storeName, storeLocation;
        ImageView ivBack = view.findViewById(R.id.ivBack);
        storeName = view.findViewById(R.id.txtStoreName);
        storeLocation = view.findViewById(R.id.txtLocationStore);
        recyclerMenu = view.findViewById(R.id.recyclerStoreMenu);
        showMenuDAO = new ShowMenuDAO(getActivity());
//        idCuahangGan = getIn
        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerMenu.setLayoutManager(place);
        list = showMenuDAO.getMonAnByCuaHangID(idStore);
        showMenuStoreAdapter = new ShowMenuStoreAdapter(list,getActivity());
        recyclerMenu.setAdapter(showMenuStoreAdapter);
        showMenuStoreAdapter.setOnMenuItemClickListener(new ShowMenuStoreAdapter.OnMenuClickListener() {
            @Override
            public void onMenuItemClick(final int position) {
                final Dialog dialog = new Dialog(getActivity(), R.style.theme_dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_to_cart);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                //lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                lp.windowAnimations = R.style.DialogAnimation;

                dialog.getWindow().setAttributes(lp);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.show();
                //ánh xạ
                final DecimalFormat formatter = new DecimalFormat("###,###,###");
                ImageView img_monan = dialog.findViewById(R.id.img_monan);
                TextView tv_tenmonan = dialog.findViewById(R.id.tv_tenmonan);
                TextView tv_gia = dialog.findViewById(R.id.tv_gia);
                final Button btn_add_to_cart = dialog.findViewById(R.id.btn_add_to_cart);
                LinearLayoutCompat btn_cancel = dialog.findViewById(R.id.btn_cancel);
                final ElegantNumberButton btn_soluong = dialog.findViewById(R.id.btn_soluong);
                //set default so luong
                btn_soluong.setRange(1, 10);
                btn_soluong.setNumber("1");
                soluong = Integer.parseInt(btn_soluong.getNumber());
                tonggia = soluong * list.get(position).getGiaMonAn();
                btn_add_to_cart.setText("Thêm vào giỏ hàng - " + formatter.format(tonggia) + " VND");

                //sự kiện
                try {
                    Picasso.with(getActivity()).load(list.get(position).getHinhAnhMonAn()).into(img_monan);
                } catch (Exception e) {
                    Picasso.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/polyfood-7fcd7.appspot.com/o/no_image.jpg?alt=media&token=fa11b05a-5e3e-4f0b-a172-f10dad5208f6").into(img_monan);
                }

                tv_tenmonan.setText(list.get(position).getNameMonAn());
                tv_gia.setText(formatter.format(list.get(position).getGiaMonAn()) + " VND");
                btn_soluong.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                    @Override
                    public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                        soluong = Integer.parseInt(btn_soluong.getNumber());
                        tonggia = soluong * list.get(position).getGiaMonAn();
                        btn_add_to_cart.setText("Thêm vào giỏ hàng - " + formatter.format(tonggia) + " VND");
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ListRestaurantFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("CuaHang");
        mData.child(idStore).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CuaHang cuaHang = dataSnapshot.getValue(CuaHang.class);
                storeName.setText(cuaHang.getStoreName());
                storeLocation.setText(cuaHang.getStoreDiaChi());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}