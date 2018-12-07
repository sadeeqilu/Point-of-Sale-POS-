/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.db;

import com.pos.business.Employee;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASIYA
 */
public class EmployeeDB implements DAO<Employee> {

    
    public int getId(String email) {
        String selectQuery = "Select id from employee where email = ?";
        
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ){
           ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            int x = 0;
            while(rs.next()){
                x = rs.getInt(1);
            }
            return x;
    }   catch (SQLException ex) {
             System.out.println("ERROR !!!!!!!!" +ex.getMessage());
        }
        return 0;
    }


    @Override
    public boolean add(Employee t) {
        String insertQuery = "insert into employee values(null,?,?,?,?,?,?,?)";
        
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(insertQuery)){
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPhonenumber());
            ps.setString(5, t.getGender());
            ps.setDate(6, Date.valueOf(t.getDob()));
            ps.setString(7, t.getQualification());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException ex){
            System.out.println("Error!!! "+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Employee t) {
        String updateQuery = "update employee set first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phonenumber = ?, "
                + "gender = ?, "
                + "dob = ?, "
                + "highest_qualification = ? where id = ?";
        try (PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(updateQuery)){
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPhonenumber());
            ps.setString(5, t.getGender());
            ps.setDate(6, Date.valueOf(t.getDob()));
            ps.setString(7, t.getQualification());
            ps.setInt(8, t.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERRRRROOORRR "+e.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<Employee> getAll() {
        
        String selectQuery = "Select employee.id, first_name, last_name"
                + ", email, phonenumber, gender,"
                + "dob, highest_qualification,"
                + "role, status from employee, user where employee.id = user.id";
       // System.out.println("select * from employee right join user on id.employee = id.user");
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery()){
            ObservableList staffs = FXCollections.observableArrayList();
            while(rs.next()){
                
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLastName(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPhonenumber(rs.getString(5));
                e.setGender(rs.getString(6));
                e.setDob(rs.getDate(7).toLocalDate());
                e.setRole(rs.getString(9));
                e.setQualification(rs.getString(8));
                e.setStatus(rs.getString(10));
                
                staffs.add(e);
            }
            return staffs;
        }catch(SQLException ex){
            System.out.println("Error "+ex.getMessage());
        }
        return null;
    }

    @Override
    public Employee getT(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Employee getTt(int id) {
        
        String selectQuery = "Select * from employee where id = ?";
        
        try (PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery);
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Employee e = new Employee();
            while(rs.next()){
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLastName(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPhonenumber(rs.getString(5));
                e.setGender(rs.getString(6));
                e.setDob(rs.getDate(7).toLocalDate());
             
                e.setQualification(rs.getString(8));
            }
            return e;
        } catch (SQLException e) {
            System.out.println("a nan nake  " +e.getMessage());
            return null;
        }
    
    }
}
