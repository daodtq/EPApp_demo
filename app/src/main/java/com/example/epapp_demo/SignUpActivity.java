package com.example.epapp_demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.epapp_demo.DAO.KhachHangDAO;
import com.example.epapp_demo.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp;
    private FirebaseAuth mAuth;
    TextInputEditText regUser, regPass, regRePass, redSdt;
    TextInputLayout inputRegUser, inputRegPass, inputRegPass1, inputRegPhone;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ProgressBar pb;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        btnSignUp = findViewById(R.id.btnSignUp);
        regUser = findViewById(R.id.edtEmail);
        regPass = findViewById(R.id.edtPassword);
        regRePass = findViewById(R.id.edtRePassword);
        inputRegUser = findViewById(R.id.inputEmail);
        inputRegPass = findViewById(R.id.inputPass);
        inputRegPass1 = findViewById(R.id.inputRePass);
        pb = findViewById(R.id.pbRegister);

        regPass.addTextChangedListener(new ValidationTextWatcher(regPass));
        regRePass.addTextChangedListener(new ValidationTextWatcher(regRePass));
        regUser.addTextChangedListener(new ValidationTextWatcher(regUser));
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), BottomNavigation.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEmail() & validatePassword() == true){
                    final String email= regUser.getText().toString();
                    final String password = regPass.getText().toString();
                    pb.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.v(email,password);
                                        Toast.makeText(SignUpActivity.this, "Đăng kí thành công",
                                                Toast.LENGTH_SHORT).show();
                                        userID = mAuth.getCurrentUser().getUid();
//                                        String userId = mData.push().getKey();
                                        KhachHang s = new KhachHang(userID,"","",password,"", email,"",0);
                                        mData.child("KhachHang").child(userID).push();
                                        mData.child("KhachHang").child(userID).setValue(s);
//                                        KhachHangDAO khachHangDAO = new KhachHangDAO(SignUpActivity.this);
//                                        khachHangDAO.insert(s);
                                        Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        Log.v(email,password);
                                        Toast.makeText(SignUpActivity.this, "Nhập đúng định dạng email, mật khẩu 6 kí tự",
                                                Toast.LENGTH_SHORT).show();
                                        pb.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validatePassword() {
        if (regPass.getText().toString().trim().isEmpty()) {
            inputRegPass.setError("Bắt buộc nhập mật khẩu");
            requestFocus(regPass);
            return false;
        }else if(regPass.getText().toString().length() < 6){
            inputRegPass.setError("Mật khẩu phải là 6 ký tự");
            requestFocus(regPass);
            return false;
        }else {
            inputRegPass.setErrorEnabled(false);
        }
        return true;
    }
    private  boolean validateRePass(){
        String pass = regPass.getText().toString();
        String rePass = regRePass.getText().toString();
        if (regRePass.getText().toString().trim().isEmpty()) {
            inputRegPass1.setError("Nhập lại mật khẩu");
            requestFocus(regRePass);
            return false;
        }
        else if (rePass.equals(pass)){
            inputRegPass1.setErrorEnabled(false);
            return true;
        }else {
            inputRegPass1.setError("Mật khẩu không khớp");
            requestFocus(regRePass);

        }
        return false;
    }

    private boolean validateEmail() {
        if (regUser.getText().toString().trim().isEmpty()) {
            inputRegUser.setError("Bắt buộc nhập mật Email");
            requestFocus(regUser);
            return false;
        } else {
            String emailId = regUser.getText().toString();
            Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                inputRegUser.setError("Sai định dạng Email, ex: abc@example.com");
                requestFocus(regUser);
                return false;
            } else {
                inputRegUser.setErrorEnabled(false);
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
                case R.id.edtRePassword:
                    validateRePass();
                    break;
            }
        }
    }
}