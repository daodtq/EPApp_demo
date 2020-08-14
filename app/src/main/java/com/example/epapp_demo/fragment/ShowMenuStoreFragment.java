package com.example.epapp_demo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.ShowMenuDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.ShowCuaHangAdapter;
import com.example.epapp_demo.adapter.ShowMenuStoreAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.example.epapp_demo.model.MonAn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowMenuStoreFragment extends Fragment {
    public static String idStore;
    public static ShowMenuStoreAdapter showMenuStoreAdapter;
    RecyclerView recyclerMenu;
    CuaHangDAO cuaHangDAO;
    ShowMenuDAO showMenuDAO;
    ArrayList <MonAn> list = new ArrayList<>();
    ArrayList <CuaHang> listCuaHang = new ArrayList<>();
    public ShowMenuStoreFragment(String idStore) {
        this.idStore = idStore;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_one_store_fragment, container, false);
        final TextView storeName, storeLocation;
        ImageView ivBack = view.findViewById(R.id.ivBack);
        storeName = view.findViewById(R.id.txtStoreName);
        storeLocation = view.findViewById(R.id.txtLocationStore);
        recyclerMenu = view.findViewById(R.id.recyclerStoreMenu);
        showMenuDAO = new ShowMenuDAO(getActivity());
        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerMenu.setLayoutManager(place);
        list = showMenuDAO.getMonAnByCuaHangID(idStore);
        showMenuStoreAdapter = new ShowMenuStoreAdapter(list,getActivity());
        recyclerMenu.setAdapter(showMenuStoreAdapter);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ListRestaurantFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("CuaHang");
        mData.child(idStore).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CuaHang cuaHang = dataSnapshot.getValue(CuaHang.class);
                storeName.setText(cuaHang.getStoreName());
                storeLocation.setText(cuaHang.getStoreDiaChi());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}