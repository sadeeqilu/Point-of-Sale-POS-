/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author macbookpro
 */
public class SettingsController extends BaseController implements Initializable{
    
    @FXML
    private Button btnDashboard;

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
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
