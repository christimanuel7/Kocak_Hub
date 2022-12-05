package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextView btnLogin;
    private TextInputEditText editUsername,editEmail,editPassword,editRepeatPassword;
    private MaterialButton btnRegister;
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
            }
        });
    }

    private void registerUser(){
        String username=editUsername.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        String repeatpassword=editRepeatPassword.getText().toString().trim();

        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(),"Email is required!",Toast.LENGTH_SHORT).show();
            editUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(),"Email is required!",Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Please provide valid email!",Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Password is required!",Toast.LENGTH_SHORT).show();
            editPassword.requestFocus();
            return;
        }

        if(password.length()<8){
            Toast.makeText(getApplicationContext(),"Min. password length is 8 character!",Toast.LENGTH_SHORT).show();
            editPassword.requestFocus();
            return;
        }

        if(repeatpassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"Repeat Password is required!",Toast.LENGTH_SHORT).show();
            editRepeatPassword.requestFocus();
            return;
        }

        if(!password.equals(repeatpassword)){
            Toast.makeText(getApplicationContext(),"Password and repeat password does not match!",Toast.LENGTH_SHORT).show();
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
                                                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                                                startActivity(register);
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),"Failed to regiter account! Try again",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

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