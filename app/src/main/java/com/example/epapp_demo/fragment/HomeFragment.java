package com.example.epapp_demo.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.MonAnDAO;
import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CategoriesAdapter;
import com.example.epapp_demo.adapter.CuaHangAdapter_temp;
import com.example.epapp_demo.adapter.PlaceAdapter;
import com.example.epapp_demo.adapter.SliderAdapter;
import com.example.epapp_demo.model.CuaHang_temp;
import com.example.epapp_demo.model.PhanLoai;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements LocationListener {
    SliderView sliderView;
    RecyclerView rcvCategories;
    ListView rcvQuanGoiY;
    public static CuaHangAdapter_temp cuaHangAdapter_temp;
    List<CuaHang_temp> temp = new ArrayList<>();
    PlaceAdapter placeAdapter;
    public static CategoriesAdapter categoriesAdapter;
    ArrayList<PhanLoai> list = new ArrayList<>();
    boolean GpsStatus;
    PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO(getActivity());
    ImageView btn_reload;
    LocationManager locationManager;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_fragment, container, false);

     return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Permission already Granted
            //Do your work here
            //Perform operations here only which requires permission
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) this);
        final Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        sliderView = view.findViewById(R.id.imgSlider);
        rcvCategories = (RecyclerView)view.findViewById(R.id.trending_recycler_view);
        rcvQuanGoiY = view.findViewById(R.id.place_recycler_view);
        btn_reload = view.findViewById(R.id.btn_reload);
        LinearLayoutManager llmTrending = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvCategories.setLayoutManager(llmTrending);
        list = phanLoaiDAO.getAllMenu();
        categoriesAdapter = new CategoriesAdapter(list,getActivity());
        rcvCategories.setAdapter(categoriesAdapter);

        //custom slider
        SliderAdapter adapter = new SliderAdapter(getActivity());
        sliderView.setSliderAdapter(adapter);
        //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        btn_reload.setVisibility(View.INVISIBLE);
        getTemp();
        if (location == null) {
            btn_reload.setVisibility(View.VISIBLE);
        }

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (GpsStatus == true) {
                    getTemp();
                    btn_reload.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa bật vị trí của thiết bị!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cuaHangAdapter_temp.setOnCuaHangGanItemClickListener(new CuaHangAdapter_temp.OnCuaHangGanClickListener() {
            @Override
            public void onCuaHangGanItemClick(int position) {
                CuaHang_temp cuaHangTemp = temp.get(position);
                String idStore = cuaHangTemp.getMacuahang();
                ShowMenuStoreFragment newFragment = new ShowMenuStoreFragment(idStore);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getTemp() {
        CuaHangDAO cuaHangDAO = new CuaHangDAO(getActivity());
        temp = cuaHangDAO.getTemp(getActivity());
        cuaHangAdapter_temp = new CuaHangAdapter_temp(getActivity(), R.layout.item_cuahang_gan, temp);
        rcvQuanGoiY.setAdapter(cuaHangAdapter_temp);
        Log.d("size","temp: "+temp.size());
    }
}