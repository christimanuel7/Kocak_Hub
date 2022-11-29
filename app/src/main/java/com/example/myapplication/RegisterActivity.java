package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextView btnLogin;
    private EditText editUsername,editEmail,editPassword,editRepeatPassword;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        editUsername=findViewById(R.id.edit_username);
        editEmail=findViewById(R.id.edit_email);
        editPassword=findViewById(R.id.edit_password);
        editRepeatPassword=findViewById(R.id.edit_repeatpassword);

        btnRegister=findViewById(R.id.btnsignup);
        btnLogin=findViewById(R.id.btnLogin);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Register Account");
        progressDialog.setTitle("Creating account in progress, please wait");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Intent register = new Intent(RegisterActivity.this, SplashActivity.class);
                startActivity(register);
            }
        });
    }

    private void registerUser(){
        String username=editUsername.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        String repeatpassword=editRepeatPassword.getText().toString().trim();

        if(username.isEmpty()){
            editUsername.setError("Username is required!");
            editUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }

        if(repeatpassword.isEmpty()){
            editRepeatPassword.setError("Repeat Password is required!");
            editRepeatPassword.requestFocus();
            return;
        }

        if(!password.equals(repeatpassword)){
            editRepeatPassword.setError("Password and repeat password does not match!");
            editPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserRegister user=new UserRegister(username,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this,"Account has been registered successfully!",Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(RegisterActivity.this,"Failed to regiter account! Try again",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                });

    }
}