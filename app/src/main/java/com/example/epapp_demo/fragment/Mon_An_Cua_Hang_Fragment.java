package com.example.epapp_demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


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

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}