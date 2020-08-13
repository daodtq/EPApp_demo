package com.example.epapp_demo.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.SpinnerPLAdapter;
import com.example.epapp_demo.model.PhanLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class Mon_An_Cua_Hang_Fragment extends Fragment {

    RecyclerView rcv;
    FloatingActionButton add;

    public Mon_An_Cua_Hang_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mon__an__cua__hang_, container, false);
        rcv = view.findViewById(R.id.recycler_mon_an_cua_hang);
        add = view.findViewById(R.id.btn_add_mon_an);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.add_monan,null);
                final EditText tenmon = view1.findViewById(R.id.edtTenMon);
                final Spinner spn = view1.findViewById(R.id.spnTheLoai);
                final EditText mota = view1.findViewById(R.id.edtMotaMon);
                final EditText gia = view1.findViewById(R.id.edtGiaMon);
                final EditText url = view1.findViewById(R.id.edtUrlMon);
                List<PhanLoai> listPL = new PhanLoaiDAO(getActivity()).getAll();
                SpinnerPLAdapter spnPL = new SpinnerPLAdapter(getActivity(),listPL);
                spn.setAdapter(spnPL);
                builder.setView(view1);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}