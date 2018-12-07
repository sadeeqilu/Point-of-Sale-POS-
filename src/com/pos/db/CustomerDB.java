/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.db;

import com.pos.business.Customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author macbookpro
 */
public class CustomerDB implements DAO<Customer>{

    
   
    public Customer getCustomer(int id) {
        String selectQuery = "Select * from customer where id = ?";
        
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
             Customer e = new Customer();
            while(rs.next()){
               
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLast_name(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPhonenumber(rs.getString(6));
                e.setGender(rs.getString(5));
                e.setCompany(rs.getString(7));
                
                
            }
            return e;
        }catch(SQLException ex){
            System.out.println("Error "+ex.getMessage());
        }
        return null;
    }
    
    @Override
    public Customer getT(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public boolean add(Customer t) {
        String sql = "insert into customer values(null,?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql)){
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getGender());
            ps.setString(5, t.getPhonenumber());
            ps.setString(6, t.getCompany());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Customer t) {
             String updateQuery = "update customer set first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phonenumber = ?, "
                + "gender = ?, "
                + "company = ? "
                + "where id = ?";
        try (PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(updateQuery)){
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPhonenumber());
            ps.setString(5, t.getGender());
            ps.setString(6, t.getCompany());
            ps.setInt(7, t.getId());
       
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERRRRROOORRR "+e.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<Customer> getAll() {
         String selectQuery = "Select * from customer";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery()){
            ObservableList customers = FXCollections.observableArrayList();
            while(rs.next()){
                Customer e = new Customer();
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLast_name(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPhonenumber(rs.getString(6));
                e.setGender(rs.getString(5));
                e.setCompany(rs.getString(7));
                
                
                customers.add(e);
            }
            return customers;
        }catch(SQLException ex){
            System.out.println("Error "+ex.getMessage());
        }
        return null;
    }
    
    
}
