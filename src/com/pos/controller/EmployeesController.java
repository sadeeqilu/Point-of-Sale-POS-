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
import com.pos.db.EmployeeDB;
import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeesController extends BaseController implements Initializable{

    @FXML
    private Button btnDashboard;

    
    @FXML
    private TextField searchEmployeeText;
    
    @FXML
    private Button customerPageBtn;

    @FXML
    private Button employeesPageBtn;

    @FXML
    private Button productsPageBtn;

    @FXML
    private Button transactionsPageBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private BorderPane dashboardPane;

    @FXML
    private VBox dashboard;

    @FXML
    private Button newEmployeeBtn;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, Integer> employeeIDCol;

    @FXML
    private TableColumn<Employee, String> employeefnameCol;

    @FXML
    private TableColumn<Employee, String> employeelnameCol;

    @FXML
    private TableColumn<Employee, String> employeeEmailCol;

    @FXML
    private TableColumn<Employee, String> employeephoneCol;

    @FXML
    private TableColumn<Employee, String> employeeGenderCol;

    @FXML
    private TableColumn<Employee, String> employeeDobCol;

    @FXML
    private TableColumn<Employee, String> employeeRoleCol;

    @FXML
    private TableColumn<Employee, String> employeeStatusCol;
    
    
    @FXML
    private TableColumn<Employee, String> employeequaliCol;


    @FXML
    private Button updateEmployeeBtn;

    @FXML
    private Button delEmployeeBtn;
    
    
    
    EmployeeDB sdb = new EmployeeDB();
    public ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    void HandleCustomerPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.CUSTOMERS.getPage());
    }


    @FXML
    void HandleProductsPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.PRODUCTS.getPage());
    }

    @FXML
    void handleBtnDashboard(ActionEvent event) throws IOException {

        navigate(event, Page.DASHBOARD.getPage());
    }

    @FXML
    void handleLogoutBtn(ActionEvent event) throws IOException {

        navigate(event, Page.LOGIN.getPage());
    }

    @FXML
    void handleSettingsBtn(ActionEvent event) throws IOException {

        navigate(event, Page.SETTINGS.getPage());
    }

    @FXML
    void handleTransactionsPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.TRANSACTION.getPage());
    }

    
    @FXML
    void handleDelEmployeeBtn(ActionEvent event) {

    }
    
    @FXML
    void handleNewEmployeeBtn(ActionEvent event) throws IOException {

//        Stage stage = new Stage();
//        stage.setTitle("New Employee");
//        stage.setResizable(false);
//        Parent node = FXMLLoader.load(Page.NEW_EMPLOYEE.getPage());
//        Scene scene = new Scene(node);
//        
//        stage.setScene(scene);
//        stage.showAndWait();
        navigate(event, Page.NEW_EMPLOYEE.getPage());
    }
    
    @FXML
    void handleUpdateEmployeeBtn(ActionEvent event) throws IOException {

        navigate(event, Page.UPDATE_EMPLOYEE.getPage());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeefnameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        employeelnameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        employeeGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employeeRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        employeequaliCol.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        employeeDobCol.setCellValueFactory(cellData -> {
              Employee e = (Employee) cellData.getValue();
              LocalDate date = e.getDob();
              String dateFormatted = date.format(DateTimeFormatter.ISO_DATE);
            return new ReadOnlyStringWrapper(dateFormatted);
              
        });
        employeephoneCol.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
        employeeEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        employeeStatusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        employees = sdb.getAll();
        employeesTable.setItems(employees);
        
        FilteredList<Employee> filteredEmployees = new FilteredList<>(employees, i -> true);
        searchEmployeeText.setOnKeyReleased(e -> {
            filteredEmployees.setPredicate(new Predicate<Employee>(){
                @Override
                public boolean test(Employee t) {
                    if(employeefnameCol.getText() == null || employeefnameCol.getText().isEmpty() ||
                            employeelnameCol.getText() == null || employeelnameCol.getText().isEmpty() ||
                            employeeRoleCol.getText() == null || employeeRoleCol.getText().isEmpty() ||
                            employeequaliCol.getText() == null || employeequaliCol.getText().isEmpty()){
                        return true;
                    }
                
                    return t.getFirstName().toLowerCase().contains(searchEmployeeText.getText().toLowerCase()) ||
                            t.getLastName().toLowerCase().contains(searchEmployeeText.getText().toLowerCase()) ||
                            t.getRole().toLowerCase().contains(searchEmployeeText.getText().toLowerCase()) ||
                            t.getQualification().toLowerCase().contains(searchEmployeeText.getText().toLowerCase());
                }
                
            });
        });
        
        
        employeesTable.setItems(filteredEmployees);
        
    }    

}
