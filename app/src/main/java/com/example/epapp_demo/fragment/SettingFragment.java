package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epapp_demo.LoginActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.KhachHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class SettingFragment extends Fragment {
    TextView  txtDoiThongTin, txtChangePassWord;
    ImageView logout;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1 = firebaseDatabase.getReference("KhachHang");
    CircularImageView profile_image;
    TextView tvNameProfile, tvMailProfile, tvPhoneProfile, tvAddressProfile,tvNgaySinh;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public static String userID;
    public static String email;
    public static String pass;
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
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        tvMailProfile = view.findViewById(R.id.tvMailProfile);
        tvPhoneProfile = view.findViewById(R.id.tvPhoneProfile);
        tvAddressProfile = view.findViewById(R.id.tvDiaChiProfile);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinhProfile);
        txtDoiThongTin = view.findViewById(R.id.txtDoiThongTin);
        txtChangePassWord = view.findViewById(R.id.txtDoiMatKhau);
        profile_image = view.findViewById(R.id.profile_image);
        userID = fAuth.getCurrentUser().getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            email = user.getEmail();
            tvMailProfile.setText(email);
        databaseReference1.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                KhachHang nguoiDung = dataSnapshot.getValue(KhachHang.class);
                tvNameProfile.setText(nguoiDung.getUserName());
                tvPhoneProfile.setText(nguoiDung.getUserSDT());
                tvAddressProfile.setText(nguoiDung.getUserDiaChi());
                tvNgaySinh.setText(nguoiDung.getUserNgaySinh());
                pass = nguoiDung.getUserPass();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

        txtDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new DoiThongTinFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        txtChangePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ChangePassFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}