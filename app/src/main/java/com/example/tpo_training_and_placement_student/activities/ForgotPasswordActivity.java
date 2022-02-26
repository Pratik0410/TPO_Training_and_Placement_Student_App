package com.example.tpo_training_and_placement_student.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tpo_training_and_placement_student.R;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    public ImageView arrowBackImageView;
    public Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Objects.requireNonNull(getSupportActionBar()).hide();

        arrowBackImageView = findViewById(R.id.id_arrow_back_image_view_in_activity_forgot_password);
        submitButton = findViewById(R.id.id_submit_button_in_activity_forgot_password);

        arrowBackImageView.setOnClickListener(view -> finish());

        submitButton.setOnClickListener(view -> finish());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}