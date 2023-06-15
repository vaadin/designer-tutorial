package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contact {

    @Id
    @NotBlank
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String status;

    public Contact(String email, String firstName, String lastName, String company, String status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.status = status;
    }

    public Contact() {}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
