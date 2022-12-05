package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth=FirebaseAuth.getInstance();

        //      MEMUNCULKAN SPLASH PAGE SELAMA 1500 ms, LANJUT LOGIN INTENT
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(SplashActivity.this, GetStartedActivity.class);
                startActivity(login);
            }
        },1500);
    }

    private void reload(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(home);
            }
        },1500);
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