package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.PhanLoaiAdapter;
import com.example.epapp_demo.model.PhanLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PhanloaiFragment extends Fragment {

    PhanLoaiDAO phanLoaiDAO= new PhanLoaiDAO(getActivity());
    private FirebaseAuth mAuth;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("PhanLoai");
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
        lv = view.findViewById(R.id.rcvPhanLoai);
        add = view.findViewById(R.id.btnAddPhanLoai);
        mAuth = FirebaseAuth.getInstance();

        list = phanLoaiDAO.getAll();
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
                final EditText tenloai = view1.findViewById(R.id.edttenlaoi);
                final EditText motalaoi = view1.findViewById(R.id.edtMota);
                final EditText anh = view1.findViewById(R.id.edtUrlAnh);
                builder.setView(view1);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String anh1 = anh.getText().toString();
                        String tenl1 = tenloai.getText().toString();
                        String motal1 = motalaoi.getText().toString();
                        PhanLoai s = new PhanLoai(null,tenl1,motal1,anh1);
                        phanLoaiDAO.insert(s);

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