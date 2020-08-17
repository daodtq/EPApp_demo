package com.example.epapp_demo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.GioHangAdapter;
import com.example.epapp_demo.localdb.DbHelper;
import com.example.epapp_demo.model.ChiTietGioHang;

import java.util.ArrayList;

public class GioHangFragment extends Fragment {
    RecyclerView rcv;
    Button Add;
    GioHangAdapter adapter;
    ArrayList<ChiTietGioHang> list = new ArrayList<>();
    DbHelper db;
    public GioHangFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        rcv = view.findViewById(R.id.rcvGioHang);
        Add = view.findViewById(R.id.btnAddGioHang);
        db = new DbHelper(getContext());

        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(place);

        list = db.listGioHang();
        adapter = new GioHangAdapter(list, getContext());
        rcv.setAdapter(adapter);
        return view;
    }
}