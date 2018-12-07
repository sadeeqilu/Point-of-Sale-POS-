/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.business.User;
import com.pos.db.UserDB;
import com.pos.fxml.Page;
import com.pos.validation.FXMLValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author ASIYA
 */
public class LoginController extends BaseController implements Initializable {
    @FXML
    private PasswordField passwordText;

    @FXML
    private Button closeButton;

    @FXML
    private TextField usernameText;

    @FXML
    private Button loginButton;
    
    String username;
    
    public void setUsername(String user){
        username =user;
    }
    
    public String getUsername(){
        return this.username;
    }

    @FXML
    void handleLoginButton(ActionEvent event) throws IOException {

        if(isValidEntry()){
            try{
                UserDB user = new UserDB();
                User u = user.getUser(usernameText.getText().trim(), passwordText.getText());
             
                String role = u.getRole();
                if(role.equalsIgnoreCase("manager")){
                    navigate(event , Page.SIDENAV.getPage());
                    
                }
                else if(role.equalsIgnoreCase("employee")){
                    this.setUsername(user.getusername());
                    nav(event, Page.newTR.getPage(),this.getUsername());
                }
            }catch(NullPointerException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username or password");
                alert.setHeaderText("Invalid user");
                alert.showAndWait();
                }
    }
    }

    @FXML
    void handleCloseButton(ActionEvent event) {

        Platform.exit();
    }


    public boolean isValidEntry(){
        FXMLValidator fx = new FXMLValidator();
        return fx.isPresent(usernameText, "Username ") &&
                fx.isPresent(passwordText, "Password ");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
