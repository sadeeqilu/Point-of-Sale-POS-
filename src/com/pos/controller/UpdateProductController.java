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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UpdateProductController extends BaseController implements Initializable{

    @FXML
    private ComboBox<Integer> idCombo;

    @FXML
    private TextField NameText;

    @FXML
    private TextField descText;

    @FXML
    private TextField categoryText;

    @FXML
    private TextField priceText;

    @FXML
    private Button updateBtn;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField quantityText;

    @FXML
    void handleBackBtn(ActionEvent event) {

    }

    @FXML
    void handleCancelButton(ActionEvent event) {

    }

    @FXML
    void handleIdCombo(ActionEvent event) {

    }

    @FXML
    void handleUpdateButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
