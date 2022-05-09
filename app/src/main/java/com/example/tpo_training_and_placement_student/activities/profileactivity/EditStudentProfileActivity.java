package com.example.tpo_training_and_placement_student.activities.profileactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tpo_training_and_placement_student.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditStudentProfileActivity extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference dr;
    public String[] branchArray = {"AI/ML", "CE", "CH", "CM", "IT", "ME", "EJ"};
    String name, email, phoneNo, branch, profilePic;
    ImageView studentPicImageView;
    TextInputEditText studentNameTextInputEditText, emailTextInputEditText, phoneNoTextInputEditText;
    AutoCompleteTextView branchAutoCompleteTextView;
    Button saveDetailsButton;
    public Uri filepath;
    public Bitmap bitmap;
    public int flagToCheckUploadingImage =0;

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditStudentProfileActivity.this,R.layout.dropdownlist_item,branchArray);
       branchAutoCompleteTextView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditStudentProfileActivity.this,R.layout.dropdownlist_item,branchArray);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_profile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        fd=FirebaseDatabase.getInstance();
        dr=fd.getReference();
        studentPicImageView = findViewById(R.id.id_profile_image_view_in_activity_edit_student_profile);
        studentNameTextInputEditText = findViewById(R.id.id_student_name_edit_text_in_activity_edit_student_profile);
        emailTextInputEditText = findViewById(R.id.id_email_edit_text_in_activity_edit_student_profile);
        phoneNoTextInputEditText = findViewById(R.id.id_phone_no_edit_text_in_activity_edit_student_profile);
        branchAutoCompleteTextView = findViewById(R.id.id_select_branch_auto_complete_textview_in_activity_edit_student_profile);
        saveDetailsButton = findViewById(R.id.id_sign_up_button_in_activity_edit_student_profile);

        name = (String) getIntent().getExtras().get("StudentName");
        email =(String) getIntent().getExtras().get("Email");;
        phoneNo = (String) getIntent().getExtras().get("PhoneNo");
        branch = (String) getIntent().getExtras().get("Branch");
        profilePic = (String) getIntent().getExtras().get("StudentImage");

        studentNameTextInputEditText.setText(name);
        emailTextInputEditText.setText(email);
        phoneNoTextInputEditText.setText(phoneNo);
        branchAutoCompleteTextView.setText(branch);
        Picasso.get().load(profilePic).into(studentPicImageView);

        branchAutoCompleteTextView.setAdapter(arrayAdapter);

        studentPicImageView.setOnClickListener(view -> Dexter.withActivity(EditStudentProfileActivity.this)
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

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (studentNameTextInputEditText.getText().length() != 0 && emailTextInputEditText.getText().length() !=0
                && phoneNoTextInputEditText.getText().length() == 10 && branchAutoCompleteTextView.getText().length() != 0){
                    if (flagToCheckUploadingImage==5){
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference uploader = storage.getReference().child("Student Profile").child(studentNameTextInputEditText.getText().toString());

                        uploader.putFile(filepath)
                                .addOnSuccessListener(taskSnapshot -> {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(uri -> {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("StudentName", studentNameTextInputEditText.getText().toString());
                                        map.put("Email", emailTextInputEditText.getText().toString());
                                        map.put("Branch", branchAutoCompleteTextView.getText().toString());
                                        map.put("PhoneNumber", phoneNoTextInputEditText.getText().toString());
                                        map.put("StudentImage", uri.toString());

                                        FirebaseDatabase.getInstance().getReference("Students")
                                                .child(user.getUid()).updateChildren(map);
                                        finish();
                                    }).addOnFailureListener(e -> Toast.makeText(EditStudentProfileActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show());
                                });
                    }else {
                        
                        Map<String, Object> map = new HashMap<>();
                        map.put("StudentName", studentNameTextInputEditText.getText().toString());
                        map.put("Email", emailTextInputEditText.getText().toString());
                        map.put("Branch", branchAutoCompleteTextView.getText().toString());
                        map.put("PhoneNumber", phoneNoTextInputEditText.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Students")
                                .child(user.getUid()).updateChildren(map);
                        finish();
                    }

                }else {
                    Toast.makeText(EditStudentProfileActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            assert data != null;
            filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                studentPicImageView.setImageBitmap(bitmap);
                flagToCheckUploadingImage =5;
            }catch (Exception ex)
            {
                //pass
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}