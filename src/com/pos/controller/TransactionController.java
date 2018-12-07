/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package com.pos.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.pos.fxml.Page;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASIYA
 */
public class TransactionController extends BaseController implements Initializable {




    @FXML
    private Button btnDashboard;
    
    @FXML
    private Text txt;

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
    private VBox salesVbox;
    
    @FXML
    private VBox dashboard;

    @FXML
    private JFXDatePicker date;

    @FXML
    void HandleCustomerPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.CUSTOMERS.getPage());
    }

    @FXML
    void HandleEmployeesPageBtn(ActionEvent event) throws IOException {

        navigate(event, Page.EMPLOYEES.getPage());
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
    void handleDate(ActionEvent event) {

        txt.setText("Date = "+date.getValue()+"");
        txt = new Text("is it workin again?");
        dashboard.getChildren().add(txt);
        txt = new Text("Shegen   ");
        salesVbox.getChildren().add(txt);
        
        
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

        Parent node = FXMLLoader.load(Page.newTR.getPage());
        
        Stage stage = new Stage();
        Scene scene = new Scene(node);
        stage.setScene(scene);
        
        stage.show();
        //navigate(event, Page.newTR.getPage());
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
