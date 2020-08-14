package com.example.epapp_demo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.epapp_demo.R;

public class PhanLoaiKHFragment extends Fragment {
    public static String id;
    public PhanLoaiKHFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phan_loai_k_h, container, false);
        return view;
    }
}