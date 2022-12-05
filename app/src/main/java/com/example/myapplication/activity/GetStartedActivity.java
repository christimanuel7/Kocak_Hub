package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        MaterialButton btnGetStarted=findViewById(R.id.btn_getstarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            //            METHOD ONCLICK btnGetStarted untuk INTENT KE ACTIVITY LOGIN
            @Override
            public void onClick(View view) {
                Intent login = new Intent(GetStartedActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
    }
}