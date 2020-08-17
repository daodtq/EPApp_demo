package com.example.epapp_demo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.MonAnDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.PhanLoaiAdapter;
import com.example.epapp_demo.adapter.ShowCuaHangAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.MonAn;

import java.util.ArrayList;

public class PhanLoaiKHFragment extends Fragment {
    public static String id;
    RecyclerView rcv;
    public PhanLoaiKHFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }
    ImageView ivBack;
    PhanLoaiAdapter phanLoaiAdapter = new PhanLoaiAdapter(getActivity());
    ArrayList<MonAn> list = new ArrayList<>();
    public static ShowCuaHangAdapter showcuaHangAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phan_loai_k_h, container, false);

        ivBack = view.findViewById(R.id.back1);
        rcv = view.findViewById(R.id.rcv_phanloai);
        return view;
    }
}