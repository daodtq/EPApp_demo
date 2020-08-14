package com.example.epapp_demo;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.epapp_demo.fragment.HomeFragment;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnLogin;
    TextView txtSignUp;
    TextInputLayout inputEmail,inputPass;
    TextInputEditText edtemail, edtpassword;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    ProgressBar pb;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnSignIn);
        txtSignUp = findViewById(R.id.txtSignUp);
        edtemail = findViewById(R.id.edtEmail);
        edtpassword = findViewById(R.id.edtPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        edtemail.addTextChangedListener(new LoginActivity.ValidationTextWatcher(edtemail));
        edtpassword.addTextChangedListener(new LoginActivity.ValidationTextWatcher(edtpassword));

        pb = findViewById(R.id.pbLogin);


        if (mAuth.getCurrentUser() != null){
            final String userID= fAuth.getCurrentUser().getUid();
            try {
                mData.child("KhachHang").child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        try {
                            KhachHang user = dataSnapshot.getValue(KhachHang.class);
                            Log.d("abcxyz", String.valueOf(user));
                            int phanquyen = user.getPhanQuyen();
                            if (phanquyen == 0) {
                                Intent i = new Intent(LoginActivity.this, BottomNavigation.class);
                                startActivity(i);
                                finish();
                            }
                        } catch (Exception e) {
                            Intent i = new Intent(LoginActivity.this, Bottom_Navigation_CuaHang_Activity.class);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }catch (Exception e){
                mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        CuaHang user = dataSnapshot.getValue(CuaHang.class);
                        Log.d("abcxyz", String.valueOf(user));
                        Intent i = new Intent(LoginActivity.this, Bottom_Navigation_CuaHang_Activity.class);
                        startActivity(i);
                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEmail() & validatePassword() == true){
                    final String email = edtemail.getText().toString();
                    final String pass = edtpassword.getText().toString();
                    pb.setVisibility(View.VISIBLE);
                    if (email.equals("admin@gmail.com")&&pass.equals("admin1")){
                        Intent i = new Intent(LoginActivity.this, BottomNavigationAdmin.class);
                        startActivity(i);
                        finish();
                    }else {


                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                                    Toast.LENGTH_SHORT).show();
                                            final String userID = fAuth.getCurrentUser().getUid();
                                            mData.child("KhachHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("userPass").setValue(pass);
                                            try {
                                                mData.child("KhachHang").child(userID).addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                        try {
                                                            KhachHang user = dataSnapshot.getValue(KhachHang.class);
                                                            Log.d("abcxyz", String.valueOf(user));
                                                            int phanquyen = user.getPhanQuyen();
                                                            if (phanquyen == 0) {
                                                                Intent i = new Intent(LoginActivity.this, BottomNavigation.class);
                                                                startActivity(i);
                                                                finish();
                                                            }
                                                        } catch (Exception e) {
                                                            Intent i = new Intent(LoginActivity.this, Bottom_Navigation_CuaHang_Activity.class);
                                                            startActivity(i);
                                                            finish();
                                                        }


                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            } catch (Exception e) {
                                                mData.child("CuaHang").child(userID).addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        CuaHang user = dataSnapshot.getValue(CuaHang.class);
                                                        Log.d("abcxyz", String.valueOf(user));
                                                        Intent i = new Intent(LoginActivity.this, Bottom_Navigation_CuaHang_Activity.class);
                                                        startActivity(i);
                                                        finish();

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            }


                                        } else {
                                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",
                                                    Toast.LENGTH_SHORT).show();
                                            pb.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }

                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
        private boolean validatePassword() {
            if (edtpassword.getText().toString().trim().isEmpty()) {
                inputPass.setError("Bắt buộc nhập mật khẩu");
                requestFocus(edtpassword);
                return false;
            }else if(edtpassword.getText().toString().length() < 6){
                inputPass.setError("Mật khẩu phải là 6 ký tự");
                requestFocus(edtpassword);
                return false;
            }else {
                inputPass.setErrorEnabled(false);
            }
            return true;
        }
        private boolean validateEmail() {
            if (edtemail.getText().toString().trim().isEmpty()) {
                inputEmail.setError("Bắt buộc nhập mật Email");
                requestFocus(edtemail);
                return false;
            } else {
                String emailId = edtemail.getText().toString();
                Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
                if (!isValid) {
                    inputEmail.setError("Sai định dạng Email, ex: abc@example.com");
                    requestFocus(edtemail);
                    return false;
                } else {
                    inputEmail.setErrorEnabled(false);
                }
            }
            return true;
        }
    private class ValidationTextWatcher implements TextWatcher {

        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edtEmail:
                    validateEmail();
                    break;
                case R.id.edtPassword:
                    validatePassword();
                    break;
            }
        }
    }
}