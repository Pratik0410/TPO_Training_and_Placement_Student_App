package com.example.tpo_training_and_placement_student.activities.profileactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpo_training_and_placement_student.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class ViewStudentProfileActivity extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference dr;
    TextView studentNameTextView, phoneNoTextView, branchTextView, emailTextView;
    ImageView studentProfileImageView, editImageView;
    String imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_profile);
        fd=FirebaseDatabase.getInstance();
        dr=fd.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        studentNameTextView=findViewById(R.id.id_student_name_text__view_in_activity_view_student_profile);
        phoneNoTextView=findViewById(R.id.id_student_phone_no_text_view_in_activity_view_student_profile);
        branchTextView=findViewById(R.id.id_student_branch_text_view_in_activity_view_student_profile);
        studentProfileImageView=findViewById(R.id.id_profile_image_view_in_activity_view_student_profile);
        emailTextView=findViewById(R.id.id_student_email_text_view_in_activity_view_student_profile);
        editImageView=findViewById(R.id.id_edit_profile_image_view_in_activity_view_student_profile);


        dr.child("Students").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map data= (Map) snapshot.getValue();
                String name=data.get("StudentName").toString();
                String email=data.get("Email").toString();
                String phoneNo=data.get("PhoneNumber").toString();
                String branch=data.get("Branch").toString();
                imageString=data.get("StudentImage").toString();
                Picasso.get()
                        .load(data.get("StudentImage").toString())
                        .placeholder(R.drawable.ic_profile)
                        .into(studentProfileImageView);
                studentNameTextView.setText(name);
                phoneNoTextView.setText(phoneNo);
                emailTextView.setText(email);
                branchTextView.setText(branch);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewStudentProfileActivity.this, EditStudentProfileActivity.class);
                i.putExtra("StudentName",studentNameTextView.getText().toString());
                i.putExtra("Email",emailTextView.getText().toString());
                i.putExtra("PhoneNo",phoneNoTextView.getText().toString());
                i.putExtra("Branch",branchTextView.getText().toString());
                i.putExtra("StudentImage",imageString);
                startActivity(i);
            }
        });
    }
}