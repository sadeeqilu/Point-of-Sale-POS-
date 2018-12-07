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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
/**
 *
 * @author macbookpro
 */
public class updateCustomerController extends BaseController implements Initializable{


    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private ComboBox<Integer> idCombo;

    @FXML
    private TextField fNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField companyText;

    CustomerDB cdb = new CustomerDB();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<Integer> ids = FXCollections.observableArrayList();
    
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
    void handleIdCombo(ActionEvent event) {
        
        Customer customer = cdb.getCustomer(idCombo.getSelectionModel().getSelectedItem());

        fNameText.setText(customer.getFirstName());
        lastNameText.setText(customer.getLastName());
        emailText.setText(customer.getEmail());
        phoneText.setText(customer.getPhonenumber());

        if(customer.getGender().equalsIgnoreCase("male")){
            maleRadio.setSelected(true);
        }
        else femaleRadio.setSelected(true);
      
        idCombo.setValue(idCombo.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleUpdateButton(ActionEvent event) throws IOException {

        Customer staff = new Customer();
        staff.setId(idCombo.getSelectionModel().getSelectedItem());
        staff.setFirstName(fNameText.getText());
        staff.setLast_name(lastNameText.getText());
        String gender;
        if(maleRadio.isSelected())
            gender = "male";
        else gender = "female";
        staff.setGender(gender);
        staff.setPhonenumber(phoneText.getText());
        
        staff.setCompany(companyText.getText());
        staff.setEmail(emailText.getText());

        if(cdb.update(staff)){
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Updated");
                alert.showAndWait();
                navigate(event, Page.CUSTOMERS.getPage());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Couldn't update Customer");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup g1 = new ToggleGroup();
        maleRadio.setToggleGroup(g1);
        femaleRadio.setToggleGroup(g1);
        
        customers = cdb.getAll();
        
        for (int i = 0; i < customers.size(); i++) {
              ids.add(customers.get(i).getId());
        }
        
        idCombo.setItems(ids);
    }



}
