package com.example.tpo_training_and_placement_student.adapters;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.models.PrePlacementModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PrePlacementAdapter extends FirebaseRecyclerAdapter<PrePlacementModel, PrePlacementAdapter.ViewHolder> {

    public PrePlacementAdapter(@NonNull FirebaseRecyclerOptions<PrePlacementModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PrePlacementModel model) {
        holder.companyNameTextView.setText(model.getCompanyName());
        holder.detailsTextView.setText(model.getDetails());
        holder.linkTextView.setText(model.getLink());
        holder.expandMoreImageButton.setOnClickListener(view -> {
            if (holder.hiddenViewLinearLayout.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.modelPrePlacementCardView,
                        new AutoTransition());
                holder.hiddenViewLinearLayout.setVisibility(View.GONE);
                holder.expandMoreImageButton.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
            else {
                TransitionManager.beginDelayedTransition(holder.modelPrePlacementCardView,
                        new AutoTransition());
                holder.hiddenViewLinearLayout.setVisibility(View.VISIBLE);
                holder.expandMoreImageButton.setImageResource(R.drawable.ic_baseline_expand_less_24);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_pre_placement_card,parent,false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyNameTextView, detailsTextView, linkTextView;
        ImageButton expandMoreImageButton;
        LinearLayout hiddenViewLinearLayout;
        CardView modelPrePlacementCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.id_company_name_text_view_in_model_pre_placement_card);
            detailsTextView = itemView.findViewById(R.id.id_details_text_view_in_model_pre_placement_card);
            linkTextView = itemView.findViewById(R.id.id_link_text_view_model_pre_placement_card);
            expandMoreImageButton = itemView.findViewById(R.id.id_expand_more_image_button_in_model_pre_placement_card);
            hiddenViewLinearLayout = itemView.findViewById(R.id.id_hidden_view_linear_layout_in_model_pre_placement_card);
            modelPrePlacementCardView = itemView.findViewById(R.id.id_model_pre_placement_card);
        }
    }
}
