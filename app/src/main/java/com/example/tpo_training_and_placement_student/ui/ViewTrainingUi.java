package com.example.tpo_training_and_placement_student.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.adapters.ViewTrainingDetailsAdapter;
import com.example.tpo_training_and_placement_student.errorhandling.WrapContentLinearLayoutManager;
import com.example.tpo_training_and_placement_student.models.ViewTrainingDetailsModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewTrainingUi extends AppCompatActivity {
    public RecyclerView viewTrainingRecyclerView;
    public ViewTrainingDetailsAdapter viewTrainingDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_view_training);
        viewTrainingRecyclerView = findViewById(R.id.id_companies_recycler_view_in_ui_view_training);

        viewTrainingRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));

        FirebaseRecyclerOptions<ViewTrainingDetailsModel> options =
                new FirebaseRecyclerOptions.Builder<ViewTrainingDetailsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Training Activity"), ViewTrainingDetailsModel.class)
                        .build();

        viewTrainingDetailsAdapter = new ViewTrainingDetailsAdapter(options);
        viewTrainingRecyclerView.setAdapter(viewTrainingDetailsAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        viewTrainingDetailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewTrainingDetailsAdapter.stopListening();
    }
}