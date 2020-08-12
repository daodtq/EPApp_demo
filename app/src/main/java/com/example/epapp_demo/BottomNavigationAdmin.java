package com.example.epapp_demo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.epapp_demo.fragment.Hoat_Dong_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.Home_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.Mon_An_Cua_Hang_Fragment;
import com.example.epapp_demo.fragment.QlyCuaHangFragment;
import com.example.epapp_demo.fragment.QlyKhachHangFragment;
import com.example.epapp_demo.fragment.Tai_Khoan_Cua_Hang_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationAdmin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_admin);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_2);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // set fragment home đầu tiên
        if (savedInstanceState == null) {
            QlyCuaHangFragment gt  = new QlyCuaHangFragment();
            FragmentManager mn = getSupportFragmentManager();
            mn.beginTransaction()
                    .add(R.id.fragment_2, gt)
                    .commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.qlycuahang:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new QlyCuaHangFragment()).commit();
                    return true;
                case R.id.qlynguoidung:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new QlyKhachHangFragment()).commit();
                    return true;
                case R.id.theloaimonan:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new Mon_An_Cua_Hang_Fragment()).commit();
                    return true;

            }
            return false;
        }
    };
}