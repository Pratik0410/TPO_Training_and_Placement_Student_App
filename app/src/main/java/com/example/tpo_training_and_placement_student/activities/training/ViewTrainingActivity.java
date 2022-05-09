package com.example.tpo_training_and_placement_student.activities.training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tpo_training_and_placement_student.R;

public class ViewTrainingActivity extends AppCompatActivity {
    public TextView companyNameTextView, registrationDateTextView, lastDateOfRegistrationTextView, aboutCompanyTextView,
            contentsOfTrainingTextView, eligibilityCriteriaTextView, trainingDurationTextView,
            trainingChargesTextView, contactDetailsTextView;
    public String companyName, aboutCompany, contentsOfTraining, eligibilityCriteria, trainingDuration, trainingCharges, contactDetails,
            registrationDate, lastDateOfRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_training);
        companyNameTextView = findViewById(R.id.id_company_name_text_view_in_activity_view_training);
        aboutCompanyTextView = findViewById(R.id.id_about_the_company_or_organization_text_view_in_activity_view_training);
        contentsOfTrainingTextView = findViewById(R.id.id_content_of_training_text_view_in_activity_view_training);
        eligibilityCriteriaTextView = findViewById(R.id.id_eligibility_criteria_text_view_in_activity_view_training);
        trainingDurationTextView = findViewById(R.id.id_training_duration_text_view_in_activity_view_training);
        trainingChargesTextView = findViewById(R.id.id_training_charges_text_view_in_activity_view_training);
        registrationDateTextView = findViewById(R.id.id_training_registration_date_text_view_in_activity_view_training);
        lastDateOfRegistrationTextView = findViewById(R.id.id_training_last_date_of_registration_text_view_in_activity_view_training);
        contactDetailsTextView = findViewById(R.id.id_training_contact_details_text_view_in_activity_view_training);

        companyName = getIntent().getExtras().get("CompanyName").toString();
        aboutCompany = getIntent().getExtras().get("AboutCompany").toString();
        contentsOfTraining = getIntent().getExtras().get("ContentOfTraining").toString();
        eligibilityCriteria = getIntent().getExtras().get("EligibilityCriteria").toString();
        trainingDuration = getIntent().getExtras().get("TrainingDuration").toString();
        trainingCharges = getIntent().getExtras().get("TrainingCharges").toString();
        contactDetails = getIntent().getExtras().get("ContactDetails").toString();
        registrationDate = getIntent().getExtras().get("RegistrationDate").toString();
        lastDateOfRegistration = getIntent().getExtras().get("lastDateOfRegistration").toString();

        companyNameTextView.setText(companyName);
        aboutCompanyTextView.setText(aboutCompany);
        contentsOfTrainingTextView.setText(contentsOfTraining);
        eligibilityCriteriaTextView.setText(eligibilityCriteria);
        trainingDurationTextView.setText(trainingDuration);
        trainingChargesTextView.setText(trainingCharges);
        registrationDateTextView.setText(registrationDate);
        lastDateOfRegistrationTextView.setText(lastDateOfRegistration);
        contactDetailsTextView.setText(contactDetails);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}