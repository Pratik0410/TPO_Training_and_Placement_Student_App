package com.example.tpo_training_and_placement_student.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.adapters.CompanyAdapter;
import com.example.tpo_training_and_placement_student.adapters.PrePlacementAdapter;
import com.example.tpo_training_and_placement_student.models.CompanyModel;
import com.example.tpo_training_and_placement_student.models.PrePlacementModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewPrePlacementUi extends AppCompatActivity {

    RecyclerView recyclerView;
    PrePlacementAdapter prePlacementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_view_pre_placement);

        recyclerView = findViewById(R.id.id_pre_placement_recycler_view_in_ui_view_pre_placement);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PrePlacementModel> options =
                new FirebaseRecyclerOptions.Builder<PrePlacementModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Admin").child("Pre Placement Talks"), PrePlacementModel.class)
                        .build();

        prePlacementAdapter = new PrePlacementAdapter(options);
        recyclerView.setAdapter(prePlacementAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        prePlacementAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        prePlacementAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PrePlacementModel> options =
                new FirebaseRecyclerOptions.Builder<PrePlacementModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Admin").child("Pre Placement Talks"), PrePlacementModel.class)
                        .build();

        prePlacementAdapter = new PrePlacementAdapter(options);
        recyclerView.setAdapter(prePlacementAdapter);

        prePlacementAdapter.startListening();
    }
}