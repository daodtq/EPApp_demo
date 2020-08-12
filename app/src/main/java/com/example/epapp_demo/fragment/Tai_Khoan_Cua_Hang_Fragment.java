package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.LoginActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class Tai_Khoan_Cua_Hang_Fragment extends Fragment {
    ImageView ivAvt;
    ImageView ivEditViTri, ivLogoutCH, ivEditProfileCH;
    TextView tvNameCHa, tvMailCHa,tvDiaChiCHa, tvDanhgia, kinhdo, vido;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String userID = fAuth.getCurrentUser().getUid();
    CuaHangDAO cuaHangDAO = new CuaHangDAO(getActivity());

    public Tai_Khoan_Cua_Hang_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_tai__khoan__cua__hang_, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ivAvt = view.findViewById(R.id.ivCH);
        ivEditViTri = view.findViewById(R.id.ivEditViTri);
        ivLogoutCH = view.findViewById(R.id.ivLogoutCH);
        tvNameCHa = view.findViewById(R.id.tvNameCHa);
        tvMailCHa = view.findViewById(R.id.tvMailCHa);
        tvDiaChiCHa = view.findViewById(R.id.tvDiaChiCHa);
        tvDanhgia = view.findViewById(R.id.tvDanhgia);
        kinhdo = view.findViewById(R.id.kinhdo);
        vido = view.findViewById(R.id.vido);
        ivEditProfileCH = view.findViewById(R.id.ivEditProfileCH);

        ivAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.edit_avata_ch,null);
                final EditText url = view1.findViewById(R.id.url);
                builder.setView(view1);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                CuaHang user = dataSnapshot.getValue(CuaHang.class);
                                String url1 = url.getText().toString();
                                userID = fAuth.getCurrentUser().getUid();
//                                        String userId = mData.push().getKey();
                                CuaHang s = new CuaHang(userID,user.getStoreMail(),user.getStorePass(),user.getStoreMonAn(), user.getStoreName(),user.getStoreDiaChi(),user.getStoreDanhGia(),url1,user.getStoreViDo(),user.getStoreKinhDo(),1);
                                cuaHangDAO.update(s);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });

        ivEditViTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = layoutInflater.inflate(R.layout.vitri_cuahang,null);

                final EditText kinhdo = view1.findViewById(R.id.edtKinhDo);
                final EditText vido = view1.findViewById(R.id.edtViDo);

                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                CuaHang user = dataSnapshot.getValue(CuaHang.class);
                                double kinhdo1 = Double.parseDouble(kinhdo.getText().toString());
                                double vido1 = Double.parseDouble(vido.getText().toString());

                                CuaHang s = new CuaHang(userID,user.getStoreMail(),user.getStorePass(),user.getStoreMonAn(), user.getStoreName(),user.getStoreDiaChi(),user.getStoreDanhGia(),user.getStoreHinhAnh(),vido1,kinhdo1,1);
                                cuaHangDAO.update(s);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();

            }
        });

        ivEditProfileCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = layoutInflater.inflate(R.layout.edit_cuahang,null);

                final EditText name = view1.findViewById(R.id.edtNameCHa);
                final EditText diachi = view1.findViewById(R.id.edtDiaCHiCHa);

                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                CuaHang user = dataSnapshot.getValue(CuaHang.class);
                                String name1 = name.getText().toString();
                                String diachi1 = diachi.getText().toString();

                                CuaHang s = new CuaHang(userID,user.getStoreMail(),user.getStorePass(),user.getStoreMonAn(), name1,diachi1,user.getStoreDanhGia(),user.getStoreHinhAnh(),user.getStoreViDo(),user.getStoreKinhDo(),1);
                                cuaHangDAO.update(s);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });

        mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CuaHang user = dataSnapshot.getValue(CuaHang.class);

                try {
                    Picasso.with(getActivity()).load(user.getStoreHinhAnh()).into(ivAvt);
                } catch (Exception e) {

                }
                tvNameCHa.setText(user.getStoreName());
                tvMailCHa.setText(user.getStoreMail());
                tvDiaChiCHa.setText(user.getStoreDiaChi());
                tvDanhgia.setText(String.valueOf(user.getStoreDanhGia()));
                kinhdo.setText(String.valueOf(user.getStoreKinhDo()));
                vido.setText(" , "+String.valueOf(user.getStoreViDo()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ivLogoutCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = layoutInflater.inflate(R.layout.logout_alert_dialog,null);


                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
        });

    }
}