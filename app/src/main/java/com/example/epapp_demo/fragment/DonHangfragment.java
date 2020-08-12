package com.example.epapp_demo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.DonHangDAO;
import com.example.epapp_demo.QlyCuaHangActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CuaHangAdapter;
import com.example.epapp_demo.adapter.DonHangApdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.DonHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class DonHangfragment extends Fragment {

    RecyclerView rcv;
    DonHangDAO donHangDAO = new DonHangDAO(getActivity());
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String userID;
    public static DonHangApdapter donHangApdapter;
    ArrayList<DonHang> list = new ArrayList<>();

    public DonHangfragment() {
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
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);

        String i = mAuth.getCurrentUser().getUid();
        rcv = view.findViewById(R.id.rcv_DonHang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        list = donHangDAO.getDonByKhachID("MY6QSZ4hhbbXaoRIGoeWmUZYXRy1");
        donHangApdapter = new DonHangApdapter(list,getActivity());
        rcv.setAdapter(donHangApdapter);

        return view;
    }
}