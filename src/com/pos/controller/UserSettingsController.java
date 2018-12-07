/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserSettingsController implements Initializable{

    @FXML
    private BorderPane dashboardPane;

    @FXML
    private Text usernameText;

    @FXML
    private VBox dashboard;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    void handleCancelButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleUpdateButton(ActionEvent event) {

        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}


