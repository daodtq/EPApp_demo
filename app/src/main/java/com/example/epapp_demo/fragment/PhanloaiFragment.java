package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CuaHangAdapter;
import com.example.epapp_demo.adapter.KhachHangAdapter;
import com.example.epapp_demo.adapter.PhanLoaiAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.example.epapp_demo.model.PhanLoai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class PhanloaiFragment extends Fragment {

    PhanLoaiDAO phanLoaiDAO= new PhanLoaiDAO(getActivity());
    private FirebaseAuth mAuth;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String LoaiID;
    public static PhanLoaiAdapter phanLoaiAdapter;
    RecyclerView lv;
    ArrayList<PhanLoai> list = new ArrayList<>();
    FloatingActionButton add;

    public PhanloaiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_phanloai, container, false);
        lv = view.findViewById(R.id.rcvphanloai);
        add = view.findViewById(R.id.btnAddPhanLoai);
        mAuth = FirebaseAuth.getInstance();

        list = PhanLoaiDAO.getAll();
        phanLoaiAdapter= new PhanLoaiAdapter(list,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(phanLoaiAdapter);
        Log.d("test2", String.valueOf(list));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.add_loai,null);
                final EditText tenloai = view1.findViewById(R.id.edtNameloai);
                final EditText motalaoi = view1.findViewById(R.id.edtMota);
                builder.setView(view1);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        final String tenl1 = tenloai.getText().toString();
                        final String motal1 = motalaoi.getText().toString();
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.v(tenl1, motal1);
                                    Toast.makeText(getActivity(), "thêm loại thành công",
                                            Toast.LENGTH_SHORT).show();
                                    LoaiID = mAuth.getCurrentUser().getUid();
                                    PhanLoai l = new PhanLoai(LoaiID, tenl1, motal1, null,"qeqrqr", "hàn","");
                                    mData.child("phanloai").child(LoaiID).push();
                                    mData.child("phanloai").child(LoaiID).setValue(l);


                                } else {
                                    Log.v(tenl1, motal1);
                                    Toast.makeText(getActivity(), "Nhập đúng định dạng email, mật khẩu 6 kí tự",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
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
        return view;
    }
}