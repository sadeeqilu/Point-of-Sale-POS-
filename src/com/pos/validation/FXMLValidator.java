/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 *
 * @author ASIYA
 */
public class FXMLValidator {

    Alert alert;
    public FXMLValidator() {
    }
    
    public boolean isPresent(TextField t , String title){
        if(t.getText().length() > 0){
            return true;
        }
        else{
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(title+ " field is empty");
            alert.showAndWait();
            t.requestFocus();
            return false;
        }
    }
    
     public boolean isInteger(TextField t, String title){
        try{
            Integer.parseInt(t.getText());
            return true;
        }catch(NumberFormatException ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(title +" field is not a number");
            alert.showAndWait();
            t.requestFocus();
            return false;
        }
    }
    
    public boolean isDouble(TextField t, String title){
        try{
            Double.parseDouble(t.getText());
            return true;
        }catch(NumberFormatException ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(title +" field is not a number");
            alert.showAndWait();
            t.requestFocus();
            return false;
        }
    }
}
