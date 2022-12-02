package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailET, passwordET, confirmPasswordET;
    private AppCompatButton signUpBtn;
    private TextView loginLink;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        // Method to initialize all the UI components
        initializeUI();
        
        signUpBtn.setOnClickListener(view -> {
            registerNewUser();
        });

        loginLink.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });
    }

    private void registerNewUser() {

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();

        // Error: if email field is empty
        if(TextUtils.isEmpty(email)){
            emailET.setError("Email is required!");
        }
        // Error: if password field is empty
        if(TextUtils.isEmpty(password)){
            passwordET.setError("Password is required!");
        }
        // Error: if confirm password doesn't match password
        if(!password.equals(confirmPassword)){
            confirmPasswordET.setError("Doesn't match with password!");
        }

        // Register user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                        finish();
                    }  else{
                        Toast.makeText(SignUpActivity.this, "Registration failed! Please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initializeUI() {
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        confirmPasswordET = findViewById(R.id.confirmPassword);
        signUpBtn = findViewById(R.id.signup);
        loginLink = findViewById(R.id.loginLink);
    }
}