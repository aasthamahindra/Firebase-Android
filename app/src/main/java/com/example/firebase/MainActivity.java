package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        });

        AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            finish();
        });
    }


}