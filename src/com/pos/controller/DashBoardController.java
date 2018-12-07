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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 *
 * @author macbookpro
 */
public class DashBoardController extends BaseController implements Initializable{

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
    private BarChart<?, ?> barchart;

    @FXML
    private CategoryAxis nameside;

    @FXML
    private NumberAxis salaryside;
    
    @FXML
    private PieChart pie;
        
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
        XYChart.Series xy = new XYChart.Series<>();
        xy.getData().add(new XYChart.Data<>("Ahmad", 2000));
        xy.getData().add(new XYChart.Data<>("Sani", 10000));
        xy.getData().add(new XYChart.Data<>("Deejah", 5000));
        xy.getData().add(new XYChart.Data<>("Sadiq", 3000));
        xy.getData().add(new XYChart.Data<>("Tanko", 14000));
        
        barchart.getData().addAll(xy);
        
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
        
                new PieChart.Data("Cars", 200),
                new PieChart.Data("Bikes", 150),
                new PieChart.Data("Buses", 50),
                new PieChart.Data("Cycles", 90),
                new PieChart.Data("Okada", 120)
        
        );
        
        pie.setData(pieData);
    }
    
}
