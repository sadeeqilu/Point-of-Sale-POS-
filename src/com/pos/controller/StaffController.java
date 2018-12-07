/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.business.Employee;
import com.pos.business.User;
import com.pos.db.EmployeeDB;
import com.pos.db.UserDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASIYA
 */
public class StaffController extends BaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private TextField fnameText;

    @FXML
    private HBox back;
            
    @FXML
    private RadioButton femaleRadio;
    
    
    @FXML
    private RadioButton maleRadio;

    @FXML
    private TableColumn<Employee, String> genderColumn;

    @FXML
    private TextField emailText;

    @FXML
    private DatePicker dobField;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TextField phoneText;

    @FXML
    private TableColumn<Employee, String> fnameColumn;


    @FXML
    private TableColumn<Employee, String> roleColumn;

    @FXML
    private TextField lnameText;

    @FXML
    private TableView<Employee> selectedItemsTable;

    @FXML
    private TableColumn<Employee, String> lnameColumn;

    @FXML
    private TableColumn<Employee, String> dobCol;

    @FXML
    private TextField roleText;

    @FXML
    private TableColumn<Employee, String> emailColumn;
    
    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    EmployeeDB sdb = new EmployeeDB();

    @FXML
    void handleBackButton(ActionEvent event) throws IOException {

        navigate(event , Page.MANAGER.getPage());
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {

        navigate(event , Page.LOGIN.getPage());
    }

    @FXML
    void handleSelectedItemsTable(ActionEvent event) {

        
    }

    @FXML
    void handleAddButton(ActionEvent event) {

        Employee staff = new Employee();
        staff.setFirstName(fnameText.getText());
        staff.setLastName(lnameText.getText());
        String gender;
        if(maleRadio.isSelected())
            gender = "male";
        else gender = "female";
        staff.setGender(gender);
        staff.setPhonenumber(phoneText.getText());
        staff.setRole(roleText.getText());
        staff.setEmail(emailText.getText());
        staff.setDob(dobField.getValue());
        
        User u = new User();
        
        u.setPassword("pass");
        u.setRole(staff.getRole());
        u.setUsername(staff.getFirstName().trim().toLowerCase()+"."+staff.getLastName().trim().toLowerCase());
        UserDB udb = new UserDB();
        if(sdb.add(staff) && udb.add(u)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added");
            alert.showAndWait();
            employees.add(staff);
            selectedItemsTable.setItems(employees);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Couldn't add staff");
            alert.showAndWait();
        }
            
    }

    @FXML
    void handleUpdateButton(ActionEvent event) {

    }

    @FXML
    void handleClearButton(ActionEvent event) {

    }

    @FXML
    void handleRemoveButton(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Are you sure you want delete " + getEmployee().getFirstName()
                +" "+getEmployee().getLastName()+ " from table and database?");
        alert.setHeaderText("Confirm Delete");
        Optional<ButtonType> button = alert.showAndWait();
        if(button.get().equals(ButtonType.OK)) {
            
            this.employees.remove(selectedItemsTable.getSelectionModel().getSelectedItem());
        }

    }

    private Employee getEmployee(){
        return
                Employee.class.cast(selectedItemsTable.getSelectionModel().getSelectedItem());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup toggle = new ToggleGroup();
        maleRadio.setToggleGroup(toggle);
        maleRadio.setSelected(true);
        femaleRadio.setToggleGroup(toggle);
        
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        dobCol.setCellValueFactory(cellData -> {
              Employee e = (Employee) cellData.getValue();
              LocalDate date = e.getDob();
              String dateFormatted = date.format(DateTimeFormatter.ISO_DATE);
            return new ReadOnlyStringWrapper(dateFormatted);
              
        });
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        employees = sdb.getAll();
        selectedItemsTable.setItems(employees);
        
    }    
    
}
