package com.example.tpo_training_and_placement_student.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tpo_training_and_placement_student.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    public String[] branchArray = {"AI/ML","CE","CH","CM","IT","ME","EJ"};
    public ImageView arrowBackImageView;
    public TextView loginTextView;
    public Button signUpButton;
    public ConstraintLayout constraintLayout;
    public ImageView profileImageView;
    public TextInputEditText studentNameTextInputEditText, emailTextInputEditText,
            phoneNumberTextInputEditText, passwordTextInputEditText,
            confirmPasswordTextInputEditText;
    public AutoCompleteTextView branchAutoCompleteTextView;
    public Uri filepath;
    public Bitmap bitmap;
    public int flagToCheckUploadingImage = 0;
    public int branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).hide();

        constraintLayout = findViewById(R.id.id_constraint_layout_in_activity_sign_up);
        arrowBackImageView = findViewById(R.id.id_arrow_back_image_view_in_activity_sign_up);
        profileImageView = findViewById(R.id.id_profile_image_view_in_activity_sign_up);
        studentNameTextInputEditText = findViewById(R.id.id_student_name_edit_text_in_activity_sign_up);
        emailTextInputEditText = findViewById(R.id.id_email_edit_text_in_activity_sign_up);
        phoneNumberTextInputEditText = findViewById(R.id.id_phone_no_edit_text_in_activity_sign_up);
        branchAutoCompleteTextView = findViewById(R.id.id_select_branch_auto_complete_textview_in_activity_sign_up);
        passwordTextInputEditText = findViewById(R.id.id_password_edit_text_in_activity_sign_up);
        confirmPasswordTextInputEditText = findViewById(R.id.id_confirm_password_edit_text_in_activity_sign_up);
        loginTextView = findViewById(R.id.id_login_text_view_in_activity_sign_up);
        signUpButton = findViewById(R.id.id_sign_up_button_in_activity_sign_up);

        arrowBackImageView.setOnClickListener(view -> finish());

        loginTextView.setOnClickListener(view -> finish());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SignUpActivity.this,R.layout.dropdownlist_item,branchArray);
        branchAutoCompleteTextView.setAdapter(arrayAdapter);

        branchAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        profileImageView.setOnClickListener(view -> Dexter.withActivity(SignUpActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Please select Image"),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                    }
                }).check());


        signUpButton.setOnClickListener(view -> {

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference().child("Student Request");
            StorageReference storageReference = firebaseStorage.getReference().child("Student Request");

            if(Objects.requireNonNull(studentNameTextInputEditText.getText()).toString().length() != 0 &&
                    Objects.requireNonNull(emailTextInputEditText.getText()).toString().length() != 0 &&
                    Objects.requireNonNull(phoneNumberTextInputEditText.getText()).toString().length() != 0 &&
                    branchAutoCompleteTextView.getText().toString().length() != 0 &&
                    Objects.requireNonNull(passwordTextInputEditText.getText()).toString().length() != 0 &&
                    Objects.requireNonNull(confirmPasswordTextInputEditText.getText()).toString().length() != 0
            ){

                if (flagToCheckUploadingImage == 5) {
                    storageReference.putFile(filepath)
                            .addOnSuccessListener(taskSnapshot -> {
                                Task<Uri> result =taskSnapshot.getStorage().getDownloadUrl();
                                result.addOnSuccessListener(uri -> {
                                    if (phoneNumberTextInputEditText.getText().toString().length() == 10){
                                        if (passwordTextInputEditText.getText().toString().equals(confirmPasswordTextInputEditText.getText().toString())){
                                            Map<String,String> map = new HashMap<>();
                                            map.put("StudentImage",uri.toString());
                                            map.put("StudentName", Objects.requireNonNull(studentNameTextInputEditText.getText()).toString());
                                            map.put("Email", Objects.requireNonNull(emailTextInputEditText.getText()).toString());
                                            map.put("PhoneNumber", Objects.requireNonNull(phoneNumberTextInputEditText.getText()).toString());
                                            map.put("Branch",branchAutoCompleteTextView.getText().toString());
                                            map.put("Password",passwordTextInputEditText.getText().toString());
                                            map.put("ConfirmPassword",confirmPasswordTextInputEditText.getText().toString());
                                            databaseReference.child(studentNameTextInputEditText.getText().toString()).setValue(map);
                                            Snackbar.make(constraintLayout,"Registered Successfully",Snackbar.LENGTH_SHORT).show();
                                            finish();
                                        }
                                        else
                                            Snackbar.make(constraintLayout,"Password Mismatch",Snackbar.LENGTH_SHORT).show();
                                    }
                                    else
                                        Snackbar.make(constraintLayout, "Please enter valid phone number", Snackbar.LENGTH_SHORT).show();
                                });
                            });
                }
                else{
                    Snackbar.make(constraintLayout, "Please Select Image", Snackbar.LENGTH_SHORT).show();
                }
            }
            else {
                Snackbar.make(constraintLayout, "Please fill all the details", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            assert data != null;
            filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                profileImageView.setImageBitmap(bitmap);
                flagToCheckUploadingImage =5;
            }catch (Exception ex)
            {
                //pass
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SignUpActivity.this,R.layout.dropdownlist_item,branchArray);
        branchAutoCompleteTextView.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}