package com.example.tpo_training_and_placement_student.models;

public class TrainingModel {

    String AboutCompany,CompanyName, ContactDetails, ContentOfTraining, EligibilityCriteria, RegistrationDate, TrainingCharges, TrainingDuration, lastDateOfRegistration;

    public TrainingModel() {
    }

    public TrainingModel(String aboutCompany, String companyName, String contactDetails, String contentOfTraining, String eligibilityCriteria, String registrationDate, String trainingCharges, String trainingDuration, String lastDateOfRegistration) {
        AboutCompany = aboutCompany;
        CompanyName = companyName;
        ContactDetails = contactDetails;
        ContentOfTraining = contentOfTraining;
        EligibilityCriteria = eligibilityCriteria;
        RegistrationDate = registrationDate;
        TrainingCharges = trainingCharges;
        TrainingDuration = trainingDuration;
        this.lastDateOfRegistration = lastDateOfRegistration;
    }

    public String getAboutCompany() {
        return AboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        AboutCompany = aboutCompany;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getContactDetails() {
        return ContactDetails;
    }

    public void setContactDetails(String contactDetails) {
        ContactDetails = contactDetails;
    }

    public String getContentOfTraining() {
        return ContentOfTraining;
    }

    public void setContentOfTraining(String contentOfTraining) {
        ContentOfTraining = contentOfTraining;
    }

    public String getEligibilityCriteria() {
        return EligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        EligibilityCriteria = eligibilityCriteria;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getTrainingCharges() {
        return TrainingCharges;
    }

    public void setTrainingCharges(String trainingCharges) {
        TrainingCharges = trainingCharges;
    }

    public String getTrainingDuration() {
        return TrainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        TrainingDuration = trainingDuration;
    }

    public String getLastDateOfRegistration() {
        return lastDateOfRegistration;
    }

    public void setLastDateOfRegistration(String lastDateOfRegistration) {
        this.lastDateOfRegistration = lastDateOfRegistration;
    }
}
