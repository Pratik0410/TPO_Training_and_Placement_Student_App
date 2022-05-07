package com.example.tpo_training_and_placement_student.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.activities.training.ViewTrainingActivity;
import com.example.tpo_training_and_placement_student.models.TrainingModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TrainingAdapter extends FirebaseRecyclerAdapter<TrainingModel, TrainingAdapter.ViewHolder> {

    public TrainingAdapter(@NonNull FirebaseRecyclerOptions<TrainingModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TrainingModel model) {
        holder.companyNameTextView.setText(model.getCompanyName());
        holder.companyNameTextView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ViewTrainingActivity.class);
            intent.putExtra("Company Name", model.getCompanyName());
            intent.putExtra("About Company", model.getAboutCompany());
            intent.putExtra("Content of Training",model.getContentOfTraining());
            intent.putExtra("Eligibility Criteria",model.getEligibilityCriteria());
            intent.putExtra("Training Duration",model.getTrainingDuration());
            intent.putExtra("Training Chargers",model.getTrainingCharges());
            intent.putExtra("Registration Date",model.getRegistrationDate());
            intent.putExtra("Last Date of Registration",model.getRegistrationDate());
            intent.putExtra("Contact Details",model.getContactDetails());
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_company_card,parent,false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyNameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.id_company_name_text_view_in_model_training_card);
        }
    }
}
