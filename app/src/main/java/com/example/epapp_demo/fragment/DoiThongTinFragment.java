package com.example.epapp_demo.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epapp_demo.InforUserActivity;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.KhachHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DoiThongTinFragment extends Fragment {
    EditText edtHoTen, edtSdt, edtDiaChi;
    TextView txtNgaySinh;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1 = firebaseDatabase.getReference("KhachHang");
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String userID, pass, email;
    Button btnXacNhan;
    DatePickerDialog datePickerDialog;
    public DoiThongTinFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.doi_thong_tin_fragment, container, false);
       edtHoTen = view.findViewById(R.id.ed_ChangeName);
       edtSdt = view.findViewById(R.id.ed_ChangePhone);
       edtDiaChi = view.findViewById(R.id.ed_ChangeAddress);
       txtNgaySinh = view.findViewById(R.id.ed_ChangeNgaySinh);
       btnXacNhan = view.findViewById(R.id.btn_Change);
       userID = fAuth.getCurrentUser().getUid();
       databaseReference1.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                KhachHang nguoiDung = dataSnapshot.getValue(KhachHang.class);
                edtHoTen.setText(nguoiDung.getUserName());
                edtDiaChi.setText(nguoiDung.getUserDiaChi());
                pass = nguoiDung.getUserPass();
                edtSdt.setText(nguoiDung.getUserSDT());
                txtNgaySinh.setText(nguoiDung.getUserNgaySinh());
                email = nguoiDung.getUserMail();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       txtNgaySinh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Calendar calendar = Calendar.getInstance();
               int d = calendar.get(Calendar.DAY_OF_MONTH);
               int m = calendar.get(Calendar.MONTH);
               int y = calendar.get(Calendar.YEAR);
               datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       final String startDay = dayOfMonth + "/" + (month + 1) + "/" + year;
                       txtNgaySinh.setText(startDay);
                   }
               }, y, m, d);
               datePickerDialog.show();
           }
       });
       btnXacNhan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String hoten = edtHoTen.getText().toString();
               String sodienthoai = edtSdt.getText().toString();
               String diachi = edtDiaChi.getText().toString();
               String ngaysinh = txtNgaySinh.getText().toString();
               if (hoten.length() > 0 && sodienthoai.length() >0 && diachi.length() >0 && ngaysinh.length()>0) {
                       databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("userName").setValue(hoten);
                       databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("userSDT").setValue(sodienthoai);
                       databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("userDiaChi").setValue(diachi);
                       databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("userNgaySinh").setValue(ngaysinh);
                       Toast.makeText(getActivity(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                   Fragment newFragment = new SettingFragment();
                   FragmentTransaction transaction = getFragmentManager().beginTransaction();
                   transaction.replace(R.id.frame_layout, newFragment);
                   transaction.addToBackStack(null);
                   transaction.commit();
               } else {
                   Toast.makeText(getActivity(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
               }
           }
       });
        return view;
    }
}