package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epapp_demo.BottomNavigation;
import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.LoginActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class SettingFragment extends Fragment {

    ImageView logout;
    CircularImageView profile_image;
    TextView tvNameProfile, tvMailProfile, tvPhoneProfile, tvNgaySinhProfile, tvDiaChiProfile;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    String userID= fAuth.getCurrentUser().getUid();
    ImageView edit;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        tvMailProfile = view.findViewById(R.id.tvMailProfile);
        tvPhoneProfile = view.findViewById(R.id.tvPhoneProfile);
        profile_image = view.findViewById(R.id.profile_image);
        tvNgaySinhProfile = view.findViewById(R.id.tvNgaySinhProfile);
        tvDiaChiProfile = view.findViewById(R.id.tvDiaChiProfile);

        edit = view.findViewById(R.id.ivEditProfile);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mData.child("KhachHang").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                KhachHang user = dataSnapshot.getValue(KhachHang.class);

                tvNameProfile.setText(user.getUserName());
                tvMailProfile.setText(user.getUserMail());
                tvPhoneProfile.setText(user.getUserSDT());
                tvNgaySinhProfile.setText(user.getUserNgaySinh());
                tvDiaChiProfile.setText(user.getUserDiaChi());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = layoutInflater.inflate(R.layout.edit_profile,null);

                final EditText name = view1.findViewById(R.id.edtNameKH);
                final EditText ngaysinh = view1.findViewById(R.id.edtNgaySinhKH);
                final EditText sdt = view1.findViewById(R.id.edtSDTKH);
                final EditText diachi = view1.findViewById(R.id.edtDiaChiKH);

                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            mData.child("KhachHang").child(userID).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    try {
                                    KhachHang user = dataSnapshot.getValue(KhachHang.class);
                                    String pass11 = user.getUserPass();
                                    String id11 = user.getUserID();
                                    String mail11 = user.getUserMail();
                                    String name1 = name.getText().toString();
                                    Date ngaysinh1 = sdf.parse(ngaysinh.getText().toString());
                                    String sdt1 = sdt.getText().toString();
                                    String diachi1 = diachi.getText().toString();

                                    KhachHang s = new KhachHang(id11,name1,diachi1,pass11,sdt1,mail11, sdf.format(ngaysinh1),0)   ;
                                    KhachHangDAO khachHangDAO = new KhachHangDAO(getActivity());
                                    khachHangDAO.update(s);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
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
        logout = view.findViewById(R.id.ivLogout);

        logout.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }

}