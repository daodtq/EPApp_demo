package com.example.epapp_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.adapter.CuaHangAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class QlyCuaHangActivity extends AppCompatActivity {

    CuaHangDAO cuaHangDAO = new CuaHangDAO(this);
    private FirebaseAuth mAuth;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String userID;
    public static CuaHangAdapter cuaHangAdapte;
    ListView lv;
    ArrayList<CuaHang> list = new ArrayList<>();
    Button add, addDH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_cua_hang);

        lv = findViewById(R.id.rcvQlyCH);
        add = findViewById(R.id.btnAddCH);
        addDH = findViewById(R.id.btnAdDOnHang);
        mAuth = FirebaseAuth.getInstance();

        list = cuaHangDAO.getAll();
        cuaHangAdapte = new CuaHangAdapter(QlyCuaHangActivity.this,list);
        lv.setAdapter(cuaHangAdapte);
        Log.d("test1", String.valueOf(list));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(QlyCuaHangActivity.this);
                View view1 = getLayoutInflater().inflate(R.layout.add_cuahang,null);
                final EditText tenCH = view1.findViewById(R.id.edtNameStore);
                final EditText mailCH = view1.findViewById(R.id.edtMailStore);
                final EditText passCH = view1.findViewById(R.id.edtPassStore);
                builder.setView(view1);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        final String tenCH1 = tenCH.getText().toString();
                        final String mailCH1 = mailCH.getText().toString();
                        final String passCH1 = passCH.getText().toString();

                        mAuth.createUserWithEmailAndPassword(mailCH1, passCH1)
                                .addOnCompleteListener(QlyCuaHangActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.v(mailCH1,passCH1);
                                            Toast.makeText(QlyCuaHangActivity.this, "Đăng kí thành công",
                                                    Toast.LENGTH_SHORT).show();
                                            userID = mAuth.getCurrentUser().getUid();
//                                        String userId = mData.push().getKey();
                                            CuaHang s = new CuaHang(userID,mailCH1,passCH1,null, tenCH1,"",null,"",null,null,1);
                                            mData.child("CuaHang").child(userID).push();
                                            mData.child("CuaHang").child(userID).setValue(s);
//                                        KhachHangDAO khachHangDAO = new KhachHangDAO(SignUpActivity.this);
//                                        khachHangDAO.insert(s);


                                        } else {
                                            Log.v(mailCH1,passCH1);
                                            Toast.makeText(QlyCuaHangActivity.this, "Nhập đúng định dạng email, mật khẩu 6 kí tự",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });
        addDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mGioHang = FirebaseDatabase.getInstance().getReference("KhachHang/"+"MY6QSZ4hhbbXaoRIGoeWmUZYXRy1");
                mGioHang.child("donHangID").push().setValue("abc");
            }
        });

    }
}