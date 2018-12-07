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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author macbookpro
 */


public class UpdateEmployeeController extends BaseController implements Initializable{

    
    @FXML
    private RadioButton activeRadio;

    @FXML
    private RadioButton onLeaveRadio;

    @FXML
    private RadioButton dismissedRadio;

    @FXML
    private TextField highestQual;

    @FXML
    private RadioButton managerRadio;

    @FXML
    private RadioButton employeeRadio;

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
    private DatePicker dob;

    @FXML
    private Button updateBtn;

    @FXML
    private Button cancelButton;
    
    
    ObservableList<Integer> ids = FXCollections.observableArrayList();
    ObservableList<Employee> employees = FXCollections.observableArrayList();
    
    EmployeeDB sdb = new EmployeeDB();
    UserDB udb = new UserDB();

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
    void handleIdCombo(ActionEvent event) {
        
        Employee employee = sdb.getTt(idCombo.getSelectionModel().getSelectedItem());
        User u = udb.getT(idCombo.getSelectionModel().getSelectedItem());

        fNameText.setText(employee.getFirstName());
        lastNameText.setText(employee.getLastName());
        emailText.setText(employee.getEmail());
        phoneText.setText(employee.getPhonenumber());
        dob.setValue(employee.getDob());
        highestQual.setText(employee.getQualification());
        
        if(employee.getGender().equalsIgnoreCase("male")){
            maleRadio.setSelected(true);
        }
        else femaleRadio.setSelected(true);
        
        if(u.getStatus().equalsIgnoreCase("active")){
            activeRadio.setSelected(true);
        }
        else if(u.getStatus().equalsIgnoreCase("on leave")){
            onLeaveRadio.setSelected(true);
        }
        else dismissedRadio.setSelected(true);
        
        if(u.getRole().equalsIgnoreCase("manager")){
            managerRadio.setSelected(true);
        }
        else employeeRadio.setSelected(true);
        
        idCombo.setValue(idCombo.getSelectionModel().getSelectedItem());
        
        
    }

    @FXML
    void handleUpdateButton(ActionEvent event) throws IOException {

          Employee staff = new Employee();
          staff.setId(idCombo.getSelectionModel().getSelectedItem());
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
        
        staff.setEmail(emailText.getText());
        staff.setDob(dob.getValue());
        staff.setQualification(highestQual.getText());
        
        User u = new User();
        
        u.setRole(role);
        String status;
        if(activeRadio.isSelected())
            status = "active";
        else if(onLeaveRadio.isSelected())
            status = "on leave";
        else status = "dismissed";
        u.setStatus(status);
       // u.setUsername(staff.getFirstName().trim().toLowerCase()+"."+staff.getLastName().trim().toLowerCase());
        UserDB udb1 = new UserDB();
        if(sdb.update(staff)){
            u.setId(sdb.getId(emailText.getText()));
            if(udb1.update(u)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Updated");
                alert.showAndWait();
                navigate(event, Page.EMPLOYEES.getPage());
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Couldn't update employee");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup g1 = new ToggleGroup();
        maleRadio.setToggleGroup(g1);
        femaleRadio.setToggleGroup(g1);
        
        ToggleGroup g2 = new ToggleGroup();
        activeRadio.setToggleGroup(g2);
        dismissedRadio.setToggleGroup(g2);
        onLeaveRadio.setToggleGroup(g2);
        
        ToggleGroup g3 = new ToggleGroup();
        employeeRadio.setToggleGroup(g3);
        managerRadio.setToggleGroup(g3);
        
        employees = sdb.getAll();
        
        for (int i = 0; i < employees.size(); i++) {
              ids.add(employees.get(i).getId());
        }
        
        idCombo.setItems(ids);
        
    //    FilteredList<Integer> filteredId = new FilteredList(ids, i -> true);
        
//        idCombo.setEditable(true);
//        idCombo.setOnKeyReleased(e -> { 
//            filteredId.setPredicate(new Predicate() {
//                @Override
//                public boolean test(Object t) {
//                    if(t.toString().isEmpty() || t.toString() == null)
//                        return true;
//                    
//                    return t.toString().contains(idCombo.getSelectionModel().getSelectedItem().toString());
//                }
//            });
//        });
//        idCombo.setItems(filteredId);
    }

}


