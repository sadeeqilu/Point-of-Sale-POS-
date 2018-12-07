/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.business;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ASIYA
 */
public class User {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final ObjectProperty employee = new SimpleObjectProperty();
    private final StringProperty status = new SimpleStringProperty();
    

    public User() {
        
    }

  
  
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

  
    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }
    
     public String getStatus() {
        return status.get();
    }

    public void setStatus(String role) {
        this.status.set(role);
    }

    public Employee getEmployee() {
        return (Employee) employee.get();
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }
    
    
}
