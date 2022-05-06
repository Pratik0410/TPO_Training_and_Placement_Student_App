package com.example.tpo_training_and_placement_student.activities.companyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpo_training_and_placement_student.R;
import com.squareup.picasso.Picasso;

public class ViewCompanyActivity extends AppCompatActivity {

    TextView companyNameTextView, typeOfCompanyTextView, productOrServiceOfCompanyTextView, aboutCompanyTextView, contactDetailsTextView;
    String companyNameString, typeOfCompanyString, productOrServiceString, aboutString, contactDetailsString, companyLogoString;
    ImageView companyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_company);

        companyImageView = findViewById(R.id.id_profile_image_view_in_activity_view_company);
        companyNameTextView = findViewById(R.id.id_company_name_text_view_in_activity_view_company);
        typeOfCompanyTextView = findViewById(R.id.id_type_of_company_text_view_in_activity_view_company);
        productOrServiceOfCompanyTextView = findViewById(R.id.id_product_or_service_text_view_in_activity_view_company);
        aboutCompanyTextView = findViewById(R.id.id_about_text_view_in_activity_view_company);
        contactDetailsTextView = findViewById(R.id.id_contact_details_text_view_in_activity_view_company);

        companyNameString = getIntent().getExtras().get("Company Name").toString();
        typeOfCompanyString = getIntent().getExtras().get("Type of Company").toString();
        productOrServiceString = getIntent().getExtras().get("Product or Service of Company").toString();
        aboutString = getIntent().getExtras().get("About Company").toString();
        contactDetailsString = getIntent().getExtras().get("Contact Details").toString();
        companyLogoString = getIntent().getExtras().get("Company Logo").toString();

        companyNameTextView.setText(companyNameString);
        typeOfCompanyTextView.setText(typeOfCompanyString);
        productOrServiceOfCompanyTextView.setText(productOrServiceString);
        aboutCompanyTextView.setText(aboutString);
        contactDetailsTextView.setText(contactDetailsString);
        Picasso.get().load(companyLogoString).into(companyImageView);

    }
}