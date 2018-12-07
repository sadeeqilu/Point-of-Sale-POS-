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
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 *
 * @author macbookpro
 */
public class CustomersController extends BaseController implements Initializable{



    @FXML
    private Button btnDashboard;

    @FXML
    private Button customerPageBtn;

    @FXML
    private Button customersPageBtn;

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
    private Button newCustomerBtn;

    @FXML
    private TextField searchCustomerText;

    @FXML
    private TableView<Customer> customersTable;
    
    
    @FXML
    private Text userText;
    
    @FXML
    private TableColumn<Customer, Integer> customerIDCol;

    @FXML
    private TableColumn<Customer, String> customerfnameCol;

    @FXML
    private TableColumn<Customer, String> customerlnameCol;

    @FXML
    private TableColumn<Customer, String> customeremailCol;

    @FXML
    private TableColumn<Customer, String> customerphoneCol;

    @FXML
    private TableColumn<Customer, String> customergenderCol;

    @FXML
    private TableColumn<Customer, String> customercompanyCol;

    @FXML
    private Button updateCustomerBtn;

    @FXML
    private Button delCustomerBtn;

  



    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    
    CustomerDB cdb = new CustomerDB();


   


     @FXML
    void handleDelCustomerBtn(ActionEvent event) {
         try {
             Alert alert = new Alert(AlertType.CONFIRMATION);
             alert.setContentText("Are you sure you want to delete "+customersTable.getSelectionModel().getSelectedItem()
                .getFirstName()+"?");
             
         } catch (NullPointerException e) {
             Alert alert = new Alert(AlertType.ERROR);
             alert.setContentText("Select a row from the table to delete ");
             alert.showAndWait();
         }
        
        

    }
    
     @FXML
    void handleUpdateCustomerBtn(ActionEvent event) throws IOException {

         navigate(event, Page.UPDATE_CUSTOMER.getPage());
    }
    
    @FXML
    void handleNewCustomerBtn(ActionEvent event) throws IOException {

        navigate(event, Page.NEW_CUSTOMER.getPage());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerfnameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        customerlnameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        customergenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
       
        customerphoneCol.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
        customeremailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        customercompanyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
       
        customers = cdb.getAll();
        customersTable.setItems(customers);  
        
        FilteredList<Customer> filteredCustomers = new FilteredList<>(customers, i->true);
        
        searchCustomerText.setOnKeyReleased(e -> {
            filteredCustomers.setPredicate(new Predicate<Customer>() {
                @Override
                public boolean test(Customer t) {
                    if(customerfnameCol.getText() == null || customerfnameCol.getText().isEmpty() ||
                            customerlnameCol.getText() == null || customerlnameCol.getText().isEmpty()){
                        return true;
                        }
                    
                    return t.getFirstName().toLowerCase().contains(searchCustomerText.getText().toLowerCase()) 
                            || t.getLastName().toLowerCase().contains(searchCustomerText.getText().toLowerCase());
                    
                }
            } );
        });
        
        customersTable.setItems(filteredCustomers);
    
    }
    
}
