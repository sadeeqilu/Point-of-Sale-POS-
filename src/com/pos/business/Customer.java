/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.business;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ASIYA
 */
public class Customer {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phonenumber = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty company = new SimpleStringProperty();
   
    public Customer() {
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String first_name) {
        firstName.set(first_name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLast_name(String last_name) {
       lastName.set(last_name); 
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhonenumber() {
        return phonenumber.get();
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.set(phonenumber);
    }

    public String getCompany() {
        return company.get();
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }
    
    
}
