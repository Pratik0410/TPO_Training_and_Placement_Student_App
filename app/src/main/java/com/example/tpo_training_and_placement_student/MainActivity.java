package com.example.tpo_training_and_placement_student;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo_training_and_placement_student.activities.LoginActivity;
import com.example.tpo_training_and_placement_student.activities.profileactivity.EditStudentProfileActivity;
import com.example.tpo_training_and_placement_student.activities.profileactivity.ViewStudentProfileActivity;
import com.example.tpo_training_and_placement_student.ui.ViewCompanyUi;
import com.example.tpo_training_and_placement_student.ui.ViewPlacementOpportunityUi;
import com.example.tpo_training_and_placement_student.ui.ViewPrePlacementUi;
import com.example.tpo_training_and_placement_student.ui.ViewTrainingUi;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView companiesCardView, placementHistoryCardView, placementOpportunityCardView, trainingCardView;
    TextView userNameInNavigationDrawerTextView;
    ImageView profileImageInNavigationDrawerImageView;
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    RecyclerView bannerRecyclerView;
    NavigationView navigationView;
    View headerView;
    ConstraintLayout constraintLayout;
    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fd=FirebaseDatabase.getInstance();
        dr=fd.getReference();
        companiesCardView = findViewById(R.id.id_companies_card_view_in_activity_main);
        placementHistoryCardView = findViewById(R.id.id_placement_history_card_view_in_activtiy_main);
        placementOpportunityCardView = findViewById(R.id.id_placement_opportunity_card_view_in_activity_main);
        trainingCardView = findViewById(R.id.id_training_card_view_in_activity_main);
        bannerRecyclerView = findViewById(R.id.id_banner_recycler_view_in_activity_main);
        drawerLayout = findViewById(R.id.id_drawer_layout_in_activity_main);
        materialToolbar = findViewById(R.id.id_material_toolbar_in_activity_main);
        navigationView = findViewById(R.id.id_navigation_view_in_activity_main);
        headerView = navigationView.getHeaderView(0);
        userNameInNavigationDrawerTextView = headerView.findViewById(R.id.id_user_name_text_view_in_navigation_header);
        profileImageInNavigationDrawerImageView = headerView.findViewById(R.id.id_profile_image_circular_image_in_navigation_header);
        constraintLayout = headerView.findViewById(R.id.id_constraint_layout_in_navigation_header);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        dr.child("Students").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map data= (Map) snapshot.getValue();
                String name=data.get("StudentName").toString();
                Picasso.get()
                        .load(data.get("StudentImage").toString())
                        .placeholder(R.drawable.ic_profile)
                        .into(profileImageInNavigationDrawerImageView);
                userNameInNavigationDrawerTextView.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        firebaseUser.getPhotoUrl();
        Picasso.get().load(firebaseUser.getPhotoUrl()).into(profileImageInNavigationDrawerImageView);
        userNameInNavigationDrawerTextView.setText(firebaseUser.getDisplayName());

        materialToolbar.setNavigationOnClickListener(view -> drawerLayout.open());

        navigationView.setNavigationItemSelectedListener(this);

        companiesCardView.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,ViewCompanyUi.class)));

        profileImageInNavigationDrawerImageView.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ViewStudentProfileActivity.class));
            drawerLayout.close();
        });
        constraintLayout.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,ViewStudentProfileActivity.class));
            drawerLayout.close();
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_nav_list_of_company_menu_item_in_navigation_menu:
                startActivity(new Intent(MainActivity.this, ViewCompanyUi.class));
                break;
            case R.id.id_nav_training_menu_item_in_navigation_menu:
                startActivity(new Intent(MainActivity.this, ViewTrainingUi.class));
                break;
            case R.id.id_nav_placement_opportunity_menu_item_in_navigation_menu:
                startActivity(new Intent(MainActivity.this, ViewPlacementOpportunityUi.class));
                break;
            case R.id.id_nav_placement_history_menu_item_in_navigation_menu:
                break;
            case R.id.id_nav_pre_placement_menu_item_in_navigation_menu:
                startActivity(new Intent(MainActivity.this, ViewPrePlacementUi.class));
                break;
            case R.id.id_nav_logout_menu_item_in_navigation_menu:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
