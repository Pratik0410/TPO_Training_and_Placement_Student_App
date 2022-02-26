package com.example.tpo_training_and_placement_student.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tpo_training_and_placement_student.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    public Button loginButton;
    public TextView signUpTextView;
    public TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide();


        signUpTextView = findViewById(R.id.id_sign_up_text_in_activity_login);
        forgotPasswordTextView = findViewById(R.id.id_forgot_password_text_in_activity_login);

        signUpTextView.setOnClickListener(View -> startActivity(new Intent(LoginActivity.this,SignUpActivity.class)));

        forgotPasswordTextView.setOnClickListener(View -> startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class)));

    }
}