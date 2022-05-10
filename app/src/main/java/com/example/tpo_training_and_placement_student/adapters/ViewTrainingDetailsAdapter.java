package com.example.tpo_training_and_placement_student.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.activities.training.ViewTrainingActivity;
import com.example.tpo_training_and_placement_student.models.ViewTrainingDetailsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ViewTrainingDetailsAdapter extends FirebaseRecyclerAdapter<ViewTrainingDetailsModel, ViewTrainingDetailsAdapter.ViewHolder> {

    public ViewTrainingDetailsAdapter(@NonNull FirebaseRecyclerOptions<ViewTrainingDetailsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ViewTrainingDetailsModel model) {
        holder.companyNameTextView.setText(model.getCompanyName());
        holder.companyNameTextView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ViewTrainingActivity.class);
            intent.putExtra("CompanyName", holder.companyNameTextView.getText().toString());
            intent.putExtra("AboutCompany", model.getAboutCompany());
            intent.putExtra("ContentOfTraining", model.getContentOfTraining());
            intent.putExtra("EligibilityCriteria", model.getEligibilityCriteria());
            intent.putExtra("TrainingDuration", model.getTrainingDuration());
            intent.putExtra("TrainingCharges", model.getTrainingCharges());
            intent.putExtra("RegistrationDate", model.getRegistrationDate());
            intent.putExtra("lastDateOfRegistration", model.getLastDateOfRegistration());
            intent.putExtra("ContactDetails", model.getContactDetails());
            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_training_card, parent, false);
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
