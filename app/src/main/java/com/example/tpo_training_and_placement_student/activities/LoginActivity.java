package com.example.tpo_training_and_placement_student.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpo_training_and_placement_student.MainActivity;
import com.example.tpo_training_and_placement_student.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    public TextInputEditText emailTextInputEditText, passwordTextInputEditText;
    public Button loginButton;
    public TextView signUpTextView;
    public TextView forgotPasswordTextView;
    public FirebaseAuth mAuth;
    public FirebaseUser adminUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.id_login_button_in_activity_login);
        emailTextInputEditText = findViewById(R.id.id_email_edit_text_in_activity_login);
        passwordTextInputEditText = findViewById(R.id.id_password_edit_text_in_activity_login);
        signUpTextView = findViewById(R.id.id_sign_up_text_in_activity_login);
        forgotPasswordTextView = findViewById(R.id.id_forgot_password_text_in_activity_login);

        mAuth = FirebaseAuth.getInstance();
        adminUser = mAuth.getCurrentUser();

        if(adminUser!=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        signUpTextView.setOnClickListener(View -> startActivity(new Intent(LoginActivity.this,SignUpActivity.class)));

        forgotPasswordTextView.setOnClickListener(View -> startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class)));

        loginButton.setOnClickListener(view -> {
            if(Objects.requireNonNull(emailTextInputEditText.getText()).length()==0){
                Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
            }else if(Objects.requireNonNull(passwordTextInputEditText.getText()).length()==0){
                Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
            }else{
//                FirebaseDatabase.getInstance().getReference("Students")
//                        .child().updateChildren(data)
//                        .addOnSuccessListener(unused -> {
//                            Toast.makeText(EditPlacementOpportunityActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();
//                            finish();
//                        }).addOnFailureListener(e -> Toast.makeText(EditPlacementOpportunityActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show());

                mAuth.signInWithEmailAndPassword(emailTextInputEditText.getText().toString(), passwordTextInputEditText.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    updateUI(user);
                                } else {
                                    Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                    updateUI(null);
                                }
                            }

                            private void updateUI(FirebaseUser user) {
                            }
                        });
            }
        });

    }
}