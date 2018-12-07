/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.business;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ASIYA
 */
public class Employee {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phonenumber = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final StringProperty qualification = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private LocalDate dob;
    

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String value) {
        role.set(value);
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }
    
    public String getQualification() {
        return qualification.get();
    }

    public void setQualification(String value) {
        qualification.set(value);
    }
    
    public String getGender() {
        return gender.get();
    }

    
    public void setGender(String value) {
        gender.set(value);
    }

    public String getPhonenumber() {
        return phonenumber.get();
    }

    public void setPhonenumber(String value) {
        phonenumber.set(value);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

     public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

}