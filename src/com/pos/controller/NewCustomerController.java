/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.business.Customer;
import com.pos.db.CustomerDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
/**
 *
 * @author macbookpro
 */
public class NewCustomerController extends BaseController implements Initializable {



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
    private Button registerBtn;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField companyText;

    
    @FXML
    void handleBackBtn(ActionEvent event) throws IOException {

        navigate(event, Page.CUSTOMERS.getPage());
    }

    @FXML
    void handleCancelButton(ActionEvent event) throws IOException {

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel operation");
        alert.setContentText("Are you sure you want to cancel this operation?");
        alert.setHeaderText("Confirm Cancel");
        Optional<ButtonType> button = alert.showAndWait();
        if(button.get().equals(ButtonType.OK)) {
            navigate(event, Page.CUSTOMERS.getPage());
        }
    }

    @FXML
    void handleRegisterButton(ActionEvent event) throws IOException {
        Customer cus = new Customer();
        cus.setFirstName(fNameText.getText());
        cus.setLast_name(lastNameText.getText());
        String gender;
        if(maleRadio.isSelected())
            gender = "male";
        else gender = "female";
        cus.setGender(gender);
        cus.setPhonenumber(phoneText.getText());
        
        cus.setEmail(emailText.getText());
        cus.setCompany(companyText.getText());
     
        CustomerDB cdb = new CustomerDB();
       
        if(cdb.add(cus)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added");
            alert.showAndWait();
            navigate(event, Page.CUSTOMERS.getPage());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Couldn't add Customer to database");
            alert.showAndWait();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggle = new ToggleGroup();
        maleRadio.setToggleGroup(toggle);
        maleRadio.setSelected(true);
        femaleRadio.setToggleGroup(toggle);
        
    }
    
}
