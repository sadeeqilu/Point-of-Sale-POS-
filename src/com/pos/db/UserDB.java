package com.pos.db;

import com.pos.business.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;

public class UserDB implements DAO<User>{

    private String username;
    
    public User getT(int id) {
            String selectQuery = "Select * from user where id = ?";
            try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery))
                     {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                User u = new User();
                while(rs.next()){
                    u.setId(id);
                    u.setUsername(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setRole(rs.getString(4));
                    u.setStatus(rs.getString(5));
                }
                return u;
                
        } catch (SQLException e) {
                System.out.println("Error !!! "+e.getMessage());
            return null;
        }
        
           }

    public User getUser(String username,String password){
        User user =  new User();
        user.setUsername(username);
        user.setPassword(password);
        //SELECT employee.id,user.id,employee.first_name,employee.last_name FROM employee,user WHERE employee.id = user.id AND user.username = 'a.a' AND user.password = 'pass'
        String selectQuery = "select employee.first_name,employee.last_name,user.role from employee,user "
                + "where employee.id = user.id and user.username = ? and user.password = ?";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(selectQuery)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            
            ResultSet rs = ps.executeQuery();
                if(!rs.next()) {
             } else {
                    this.setUsername(rs.getString(1)+" "+rs.getString(2));
                    user.setRole(rs.getString(3));
                    return user;
             }
              
            
        }catch(SQLException ex){
            System.out.println("Error"+ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Username or password is incorrect");
                alert.showAndWait();
                //usernameText.selectAll();
               // passwordText.setText("");        }
        
    }
        return null;
    }
    
    public String getusername(){
        return this.username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    @Override
    public boolean add(User t) {
      String insertQuery = "insert into user values(?,?,?,?,'active')";
      try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(insertQuery)){
          ps.setInt(1, t.getId());
          ps.setString(2, t.getUsername());
          ps.setString(3, t.getPassword());
          ps.setString(4, t.getRole());
          
          return ps.executeUpdate() > 0;
      }catch(SQLException ex){
         
          System.out.println("Error add user!!!!!!! "+ex.getMessage());
           return false;
      }
    }

    @Override
    public boolean update(User t) {
        String updateQuery = "update user set role = ?, status = ? where id = ?";
        try(PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(updateQuery)){
            ps.setString(1, t.getRole());
            ps.setString(2, t.getStatus());
            ps.setInt(3, t.getId());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException ex){
            System.out.println("Errrror update user "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getT(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
