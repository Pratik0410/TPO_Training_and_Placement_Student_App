package com.example.tpo_training_and_placement_student.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tpo_training_and_placement_student.adapters.CompanyAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.models.CompanyModel;

public class ViewCompanyUi extends AppCompatActivity {

    RecyclerView companyRecyclerView;
    CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_view_company);

        companyRecyclerView = findViewById(R.id.id_companies_recycler_view_in_ui_view_company);

        companyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CompanyModel> options =
                new FirebaseRecyclerOptions.Builder<CompanyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Admin").child("List of Companies"), CompanyModel.class)
                        .build();

        companyAdapter = new CompanyAdapter(options);
        companyRecyclerView.setAdapter(companyAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        companyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        companyAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();

        companyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CompanyModel> options =
                new FirebaseRecyclerOptions.Builder<CompanyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Admin").child("List of Companies"), CompanyModel.class)
                        .build();

        companyAdapter = new CompanyAdapter(options);
        companyRecyclerView.setAdapter(companyAdapter);

        companyAdapter.startListening();
    }
}