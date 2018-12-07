/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author ASIYA
 */
public class ConnectionDB {
    private static Connection conn;
    
    public static synchronized Connection getConnection(){
        if(conn != null)
            return ConnectionDB.conn;
        else{
            try{
                String username = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:8889/pos_db";
                
                conn = DriverManager.getConnection(url, username, password);
                return conn;
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Check database server connection");
                alert.showAndWait();
            }
        }
        return conn;
    }
    
    public static synchronized void closeConnection(){
        if(conn != null)
            conn = null;
        try{
            conn.close();
        }catch(SQLException ex){
            
        }
    }
}
