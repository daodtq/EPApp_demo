package com.example.epapp_demo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.epapp_demo.model.KhachHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InforUserActivity extends AppCompatActivity {
    String pass, uid, email;
    EditText ed_name, ed_address, ed_phone;
    Button btn_xacnhan;
    TextView ed_ngaysinh;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);
        addcontrols();
        addevents();
    }
    private void addcontrols() {
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        pass = getIntent().getStringExtra("pass");
        email = user.getEmail();
        ed_name = findViewById(R.id.ed_name);
        ed_address = findViewById(R.id.ed_address);
        ed_phone = findViewById(R.id.ed_phone);
        ed_ngaysinh = findViewById(R.id.ed_NgaySinh);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
    }

    private void addevents() {
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String name = ed_name.getText().toString();
                    final String address = ed_address.getText().toString();
                    final String phone = ed_phone.getText().toString();
                    final String ngaysinh = ed_ngaysinh.getText().toString();
                    ed_ngaysinh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar calendar = Calendar.getInstance();
                            int d = calendar.get(Calendar.DAY_OF_MONTH);
                            int m = calendar.get(Calendar.MONTH);
                            int y = calendar.get(Calendar.YEAR);
                            datePickerDialog = new DatePickerDialog(InforUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    final String startDay = dayOfMonth + "/" + (month + 1) + "/" + year;
                                    ed_ngaysinh.setText(startDay);
                                }
                            }, y, m, d);
                            datePickerDialog.show();
                        }
                    });
                    if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                        Toast.makeText(InforUserActivity.this, "Thông tin không được để trống!", Toast.LENGTH_SHORT).show();
                    } else {
                        mData.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                KhachHang user = new KhachHang(uid, name, address,pass,phone,email,ngaysinh,0);
                                mData.child("KhachHang").child(uid).setValue(user);
                                Intent intent = new Intent(InforUserActivity.this, BottomNavigation.class);
                                intent.putExtra("uid", uid);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }catch (Exception ex){

                }


            }
        });
    }
}