/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

/**
 *
 * @author macbookpro
 */


import com.pos.business.Employee;
import com.pos.business.User;
import com.pos.db.EmployeeDB;
import com.pos.db.UserDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NewEmployeeController extends BaseController implements Initializable{

    @FXML
    private RadioButton managerRadio;

    @FXML
    private RadioButton employeeRadio;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private TextField fNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private DatePicker dob;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField highestQualText;
    
    

    EmployeeDB sdb = new EmployeeDB();

    @FXML
    void handleBackBtn(ActionEvent event) throws IOException {

        navigate(event, Page.EMPLOYEES.getPage());
    }

    @FXML
    void handleCancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel operation");
        alert.setContentText("Are you sure you want to cancel this operation?");
        alert.setHeaderText("Confirm Cancel");
        Optional<ButtonType> button = alert.showAndWait();
        if(button.get().equals(ButtonType.OK)) {
            navigate(event, Page.EMPLOYEES.getPage());
        }
    
    }

    @FXML
    void handleRegisterButton(ActionEvent event) throws IOException {

        Employee staff = new Employee();
        staff.setFirstName(fNameText.getText());
        staff.setLastName(lastNameText.getText());
        String gender;
        if(maleRadio.isSelected())
            gender = "male";
        else gender = "female";
        staff.setGender(gender);
        staff.setPhonenumber(phoneText.getText());
        String role;
        if(employeeRadio.isSelected())
            role = "employee";
        else role = "manager";
        staff.setRole(role);
        staff.setEmail(emailText.getText());
        staff.setDob(dob.getValue());
        staff.setQualification(highestQualText.getText());
        
        User u = new User();
        
        u.setPassword("pass");
        u.setRole(staff.getRole());
        u.setUsername(staff.getFirstName().trim().toLowerCase()+"."+staff.getLastName().trim().toLowerCase());
        UserDB udb = new UserDB();
        if(sdb.add(staff)){
            
            u.setId(sdb.getId(emailText.getText()));
            if(udb.add(u)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully added");
                alert.showAndWait();
                navigate(event, Page.EMPLOYEES.getPage());
            }
            
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Couldn't add staff");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         ToggleGroup toggle = new ToggleGroup();
         maleRadio.setToggleGroup(toggle);
         maleRadio.setSelected(true);
         femaleRadio.setToggleGroup(toggle);
         
         ToggleGroup g = new ToggleGroup();
         managerRadio.setToggleGroup(g);
         employeeRadio.setToggleGroup(g);
    }

}
