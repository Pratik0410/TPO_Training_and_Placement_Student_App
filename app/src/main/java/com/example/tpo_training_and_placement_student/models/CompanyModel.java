package com.example.tpo_training_and_placement_student.models;

public class CompanyModel {

    String CompanyName, CompanyLogo, ContactDetails, ProductorServiceofCompany, TypeofCompany, AboutCompany;

    public CompanyModel() {
    }

    public CompanyModel(String companyName, String companyLogo, String contactDetails, String productorServiceofCompany, String typeofCompany, String about) {
        CompanyName = companyName;
        CompanyLogo = companyLogo;
        ContactDetails = contactDetails;
        ProductorServiceofCompany = productorServiceofCompany;
        TypeofCompany = typeofCompany;
        AboutCompany = about;
    }

    public String getAboutCompany() {
        return AboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        AboutCompany = aboutCompany;
    }

    public String getContactDetails() {
        return ContactDetails;
    }

    public void setContactDetails(String contactDetails) {
        ContactDetails = contactDetails;
    }

    public String getProductorServiceofCompany() {
        return ProductorServiceofCompany;
    }

    public void setProductorServiceofCompany(String productorServiceofCompany) {
        ProductorServiceofCompany = productorServiceofCompany;
    }

    public String getTypeofCompany() {
        return TypeofCompany;
    }

    public void setTypeofCompany(String typeofCompany) {
        TypeofCompany = typeofCompany;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }
}
