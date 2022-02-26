package com.example.tpo_training_and_placement_student.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpo_training_and_placement_student.R;

public class SignUpActivity extends AppCompatActivity {

    public ImageView arrowBackImageView;
    public TextView loginTextView;
    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        arrowBackImageView = findViewById(R.id.id_arrow_back_image_view_in_activity_sign_up);
        loginTextView = findViewById(R.id.id_login_text_view_in_activity_sign_up);
        signUpButton = findViewById(R.id.id_sign_up_button_in_activity_sign_up);

        arrowBackImageView.setOnClickListener(view -> finish());

        loginTextView.setOnClickListener(view -> finish());

        signUpButton.setOnClickListener(view -> finish());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}