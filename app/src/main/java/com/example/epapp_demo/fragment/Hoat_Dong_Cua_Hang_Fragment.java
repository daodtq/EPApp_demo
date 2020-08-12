package com.example.epapp_demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.epapp_demo.R;


public class Hoat_Dong_Cua_Hang_Fragment extends Fragment {



    public Hoat_Dong_Cua_Hang_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_hoat__dong__cua__hang_, container, false);
        return view;
    }
}