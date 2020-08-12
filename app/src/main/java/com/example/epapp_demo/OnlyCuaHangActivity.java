package com.example.epapp_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.ParseException;
import java.util.Date;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class OnlyCuaHangActivity extends AppCompatActivity {

    ImageView ivAvt;
    ImageView ivEditViTri, ivLogoutCH, ivEditProfileCH;
    TextView tvNameCHa, tvMailCHa,tvDiaChiCHa, tvDanhgia, kinhdo, vido;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String userID = fAuth.getCurrentUser().getUid();
    CuaHangDAO cuaHangDAO = new CuaHangDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_cua_hang);
        ivAvt = findViewById(R.id.ivCH);
        ivEditViTri = findViewById(R.id.ivEditViTri);
        ivLogoutCH = findViewById(R.id.ivLogoutCH);
        tvNameCHa = findViewById(R.id.tvNameCHa);
        tvMailCHa = findViewById(R.id.tvMailCHa);
        tvDiaChiCHa = findViewById(R.id.tvDiaChiCHa);
        tvDanhgia = findViewById(R.id.tvDanhgia);
        kinhdo = findViewById(R.id.kinhdo);
        vido = findViewById(R.id.vido);
        ivEditProfileCH = findViewById(R.id.ivEditProfileCH);

        ivAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(OnlyCuaHangActivity.this);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(OnlyCuaHangActivity.this);
                LayoutInflater layoutInflater = LayoutInflater.from(OnlyCuaHangActivity.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OnlyCuaHangActivity.this);
                LayoutInflater layoutInflater = LayoutInflater.from(OnlyCuaHangActivity.this);
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
                    Picasso.with(OnlyCuaHangActivity.this).load(user.getStoreHinhAnh()).into(ivAvt);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(OnlyCuaHangActivity.this);
                LayoutInflater layoutInflater = LayoutInflater.from(OnlyCuaHangActivity.this);
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