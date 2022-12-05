package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private MaterialButton btnReset;
    private TextView btnLogin;
    private TextInputEditText editEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        editEmail=findViewById(R.id.edit_email);
        btnLogin=findViewById(R.id.btn_backsignin);
        btnReset=findViewById(R.id.btnreset);

        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
//            METHOD ONCLICK btnLogin
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            //            METHOD ONCLICK btnResetPassword
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
//        METHOD UNTUK RESET PASSWORD
        String email=editEmail.getText().toString().trim();

        if(email.isEmpty()){
//            MENGECEK EMAIL APAKAH INPUT NYA KOSONG ATAU TIDAK?
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            MENGECEK EMAIL APAKAH INPUT NYA SESUAI DENGAN FORMAT EMAIL ATAU TIDAK?
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            //            METHOD UNTUK MEMPROSES PENGIRIMAN PASSWORD RESET VERIFIKASI
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Try again! something wrong happened", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}