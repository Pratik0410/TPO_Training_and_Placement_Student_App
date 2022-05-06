package com.example.tpo_training_and_placement_student.models;

public class BannerModel {

    String StudentName, Branch, Company, Designation, Salary, Year;

    public BannerModel() {
    }

    public BannerModel(String studentName, String branch, String company, String designation, String salary, String year) {
        StudentName = studentName;
        Branch = branch;
        Company = company;
        Designation = designation;
        Salary = salary;
        Year = year;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
