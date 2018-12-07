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
 * @author macbookpro
 */
public class Supplier {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty fname = new SimpleStringProperty();
    private final StringProperty lname = new SimpleStringProperty();
    private final StringProperty company = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    
    public Supplier(){
        
    }
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public int getId(){
        return id.get();
    }
    
    public String getFname(){
        return fname.get();
    }
    
    public void setFname(String fname){
        this.fname.set(fname);
    }
    
    public void setLname(String fname){
        this.lname.set(fname);
    }
    
    public String getLname(){
        return lname.get();
    }
    
    public void setPhone(String phone){
        this.phone.set(phone);
    }
    
    public String getPhone(){
        return phone.get();
    }
    
    public String getCompany(){
        return company.get();
    }
    
    public void setCompany(String company){
        this.company.set(company);
    }
    
    
}
