/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.business;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author macbookpro
 */
public class WayBill {
    
    private final StringProperty wayBillNumber = new SimpleStringProperty();
    private final StringProperty driverName = new SimpleStringProperty();
    private final StringProperty carCompany = new SimpleStringProperty();
    private final StringProperty driverPhone = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final StringProperty carPlateNumber = new SimpleStringProperty();
    private LocalDate dateOfArrival;

    public WayBill() {
    }

    public String getWayBillNumber(){
        return wayBillNumber.get();
    }
    
    public void setWayBillNumber(String wayBillNumber){
        this.wayBillNumber.set(wayBillNumber);
    }
    
    public String getDriverName(){
        return driverName.get();
    }
    
    public void setdriverName(String driverName){
        this.driverName.set(driverName);
    }
    
    public String getCarCompany(){
        return carCompany.get();
    }
    
    public void setCarCompany(String carCompany){
        this.carCompany.set(carCompany);
    }
    
    public String getDriverPhone(){
        return driverPhone.get();
    }
    
    public void setDriverPhone(String phone){
        this.driverPhone.set(phone);
    }
    
    public String getFromLocation(){
        return fromLocation.get();
    }
    
    public void setFromLocation(String from){
        this.fromLocation.set(from);
    }
    
    public String getToLocation(){
        return toLocation.get();
    }
    
    public void setToLocation(String to){
        this.toLocation.set(to);
    }
    
    public String getCarPlateNumber(){
        return carPlateNumber.get();
    }
    
    public void setCarPlateNumber(String plate){
        this.carPlateNumber.set(plate);
    }
    
    
    
    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
    
    
}
