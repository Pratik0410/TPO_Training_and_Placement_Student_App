package com.example.tpo_training_and_placement_student.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo_training_and_placement_student.R;
import com.example.tpo_training_and_placement_student.activities.companyactivity.ViewCompanyActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.tpo_training_and_placement_student.models.CompanyModel;
import com.squareup.picasso.Picasso;

public class CompanyAdapter extends FirebaseRecyclerAdapter<CompanyModel, CompanyAdapter.ViewHolder>{

    public CompanyAdapter(@NonNull FirebaseRecyclerOptions<CompanyModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull CompanyModel model) {

        holder.companyNameTextView.setText(model.getCompanyName());
        Picasso.get().load(model.getCompanyLogo()).into(holder.companyImageView);
        holder.companyNameTextView.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), ViewCompanyActivity.class);
            intent.putExtra("Company Name",holder.companyNameTextView.getText().toString());
            intent.putExtra("Type of Company", model.getTypeofCompany());
            intent.putExtra("Product or Service of Company", model.getProductorServiceofCompany());
            intent.putExtra("About Company", model.getAboutCompany());
            intent.putExtra("Contact Details", model.getContactDetails());
            intent.putExtra("Company Logo", model.getCompanyLogo());
            view.getContext().startActivity(intent);

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
        ImageView companyImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNameTextView = itemView.findViewById(R.id.id_company_name_text_view_in_model_company_card);
            companyImageView = itemView.findViewById(R.id.id_company_circular_image_in_model_company_cardview);
        }
    }
}
