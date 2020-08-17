package com.example.epapp_demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.ShowCuaHangAdapter;
import com.example.epapp_demo.model.CuaHang;

import java.util.ArrayList;


public class ListRestaurantFragment extends Fragment {
    RecyclerView recyclerCuaHang;
    CuaHangDAO cuaHangDAO;
    ArrayList <CuaHang> list = new ArrayList<>();
    public static ShowCuaHangAdapter showcuaHangAdapter;

    public ListRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cuaHangDAO = new CuaHangDAO(getActivity());
        recyclerCuaHang = view.findViewById(R.id.recycler_CuaHang);
        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerCuaHang.setLayoutManager(place);
        list = cuaHangDAO.getShowCuahang();
        showcuaHangAdapter = new ShowCuaHangAdapter(list,getActivity());
        recyclerCuaHang.setAdapter(showcuaHangAdapter);
        showcuaHangAdapter.setOnStoreItemClickListener(new ShowCuaHangAdapter.OnStoreClickListener() {
            @Override
            public void onStoreItemClick(int position) {
                CuaHang cuaHang = list.get(position);
                String idStore = cuaHang.getStoreID();
                ShowMenuStoreFragment newFragment = new ShowMenuStoreFragment(idStore);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}