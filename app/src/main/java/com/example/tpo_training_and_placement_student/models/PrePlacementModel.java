package com.example.tpo_training_and_placement_student.models;

public class PrePlacementModel {

    String CompanyName, Details, Link;

    public PrePlacementModel() {
    }

    public PrePlacementModel(String companyName, String details, String link) {
        CompanyName = companyName;
        Details = details;
        Link = link;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
