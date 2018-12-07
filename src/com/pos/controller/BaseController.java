/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.controller;

import com.pos.fxml.Page;
import java.io.IOException;
import java.net.URL;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASIYA
 */
public class BaseController {
    
    public void navigate(Event event , URL page) throws IOException{
        Parent node = FXMLLoader.load(page);
            URL login = Page.LOGIN.getPage();
        if(login.equals(page)){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(node);
            
//            stage.setX(350);
//            stage.setY(240);
            stage.setScene(scene);
            stage.centerOnScreen();
           // this.fadeOut(node);
            stage.show();
        }else{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();
                
       // this.fadeOut(node);
                
        Scene scene = new Scene(node,width,height);
        
        stage.setScene(scene);
      //  stage.setFullScreen(true);
       // stage.getScene().setCursor(Cursor.WAIT);
        stage.requestFocus();
        //stage.setTitle(title);
        stage.requestFocus();
        stage.setMaximized(true);
        stage.show();
        stage.getScene().setCursor(Cursor.DEFAULT);
        }
    }
    
    public void nav(Event event,URL page,String title) throws IOException{
        Parent node = FXMLLoader.load(page);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();
                
        
                
        Scene scene = new Scene(node,width,height);
        
        stage.setScene(scene);
        stage.setTitle(title);
        
      //  stage.setFullScreen(true);
       // stage.getScene().setCursor(Cursor.WAIT);
        stage.requestFocus();
        //stage.setTitle(title);
        stage.requestFocus();
        stage.setMaximized(true);
        stage.show();
        
    }
    
    
//    public Stage getStage(User user){
//        
//    }
    
    
}
