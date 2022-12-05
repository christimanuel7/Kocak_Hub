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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView btnRegister,btnForgetPassword;
    private TextInputEditText editEmail,editPassword;
    private MaterialButton btnLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        btnLogin=findViewById(R.id.btnsignin);
        btnRegister=findViewById(R.id.btnRegister);
        btnForgetPassword=findViewById(R.id.btnForgotPassword);

        editEmail=findViewById(R.id.edit_email);
        editPassword=findViewById(R.id.edit_password);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetpassword = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(forgetpassword);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin(){
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(LoginActivity.this,"Email is required!",Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(LoginActivity.this,"Please provide valid email!",Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Password is required!",Toast.LENGTH_SHORT).show();
            editPassword.requestFocus();
            return;
        }

        if(password.length()<8){
            Toast.makeText(LoginActivity.this,"Min. password length is 8 character!",Toast.LENGTH_SHORT).show();
            editPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult()!=null){

                    FirebaseUser user=mAuth.getCurrentUser();

                    if(user.isEmailVerified()){
                        Toast.makeText(LoginActivity.this,"Account has been logged in successfully!",Toast.LENGTH_LONG).show();
                        reload();
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(),"Check your email to verify your account!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Login Failed! Please check your credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){
        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(home);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}