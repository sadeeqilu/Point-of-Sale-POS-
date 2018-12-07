/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.db;

import com.pos.business.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASIYA
 */
public class ProductDB implements DAO<Product> {

    @Override
    public Product getT(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Product t) {
        String insertQuery = "insert into item values(null,?,?,?,?,?)";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(insertQuery)){
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getCategory());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getQuantity());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException ex){
            System.out.println("ERROR "+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product t) {
         String insertQuery = "update table product set "
                 + "description = ?,"
                 + "category = ?,"
                 + "price = ?,"
                 + "quantity = ?"
                 + "where name = ?)";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(insertQuery)){
            ps.setString(1, t.getDescription());
            ps.setString(2, t.getCategory());
            ps.setDouble(3, t.getPrice());
            ps.setInt(4, t.getQuantity());
            ps.setString(5, t.getName());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException ex){
            System.out.println("ERROR "+ex.getMessage());
            return false;
        }
    }

    public boolean buy(Product p){
        String sqlQuery = "update table product set quantity = (quantity - ?) where name = ?";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sqlQuery)){
            ps.setInt(1, p.getQuantity());
            ps.setString(2, p.getName());
            
            int i = ps.executeUpdate();
            
            return i > 0;
        }catch(SQLException ex){
            
        }
        return false;
    }
    @Override
    public ObservableList<Product> getAll() {
        String selectQuery = "select * from product";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery();){
            ObservableList<Product> items = FXCollections.observableArrayList();
            while(rs.next()){
                Product item = new Product();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setDescription(rs.getString(3));
                item.setCategory(rs.getString(4));
                item.setPrice(rs.getDouble(5));
                item.setQuantity(rs.getInt(6));
                
                items.add(item);
            }
            return items;
        }catch(SQLException ex){
            
        }
        return null;
    }
    
}
