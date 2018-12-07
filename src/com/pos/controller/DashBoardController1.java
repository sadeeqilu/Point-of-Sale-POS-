package com.pos.controller;

import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class DashBoardController1 extends BaseController implements Initializable{

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
    private BorderPane dashboardPane;

    private void clearBgColor(){
        btnDashboard.setOpacity(1);
        logoutBtn.setOpacity(1);
        settingsBtn.setOpacity(1);
        transactionsPageBtn.setOpacity(1);
        customerPageBtn.setOpacity(1);
        productsPageBtn.setOpacity(1);
        employeesPageBtn.setOpacity(1);
        dashboardPane.setOpacity(1);
    }
    @FXML
    void handleCustomerBtn(MouseEvent event) throws IOException {
        clearBgColor();
        customerPageBtn.setOpacity(0.5);
        loadUI(Page.CUSTOMERS.getPage());
    }

    @FXML
    void handleDashboardBtn(MouseEvent event) throws IOException {
        clearBgColor();
        btnDashboard.setOpacity(0.5);
        loadUI(Page.DASHBOARD.getPage());
    }

    @FXML
    void handleEmployeesBtn(MouseEvent event) throws IOException {
        clearBgColor();
        employeesPageBtn.setOpacity(0.5);
        loadUI(Page.EMPLOYEES.getPage());
    }

    @FXML
    void handleLogoutBtn(MouseEvent event) throws IOException {
        navigate(event, Page.LOGIN.getPage());
    }

    @FXML
    void handleProductBtn(MouseEvent event) throws IOException {
        clearBgColor();
        productsPageBtn.setOpacity(0.5);
        loadUI(Page.PRODUCTS.getPage());
    }

    @FXML
    void handleSettingsBtn(MouseEvent event) throws IOException {
        clearBgColor();
        settingsBtn.setOpacity(0.5);
        loadUI(Page.SETTINGS.getPage());
    }

    @FXML
    void handleTransactionBtn(MouseEvent event) throws IOException {
        clearBgColor();
        transactionsPageBtn.setOpacity(0.5);
        loadUI(Page.TRANSACTION.getPage());
    }
    
    private void loadUI(URL page) throws IOException{
        Parent root = null;
        root = FXMLLoader.load(page);
        dashboardPane.setCenter(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            btnDashboard.setOpacity(0.5);
            loadUI(Page.DASHBOARD.getPage());
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}

