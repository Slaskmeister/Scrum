package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
/**
 *
 * @author jenniferhuang
 */
public class UserRepository {
    
    private Connection con;
    
    public UserRepository() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer getUserIdForUsernamn(String anvandarnamn) {
        try {
            String query = "SELECT id FROM USER\n" +
                            "WHERE ANAMN = '" + anvandarnamn + "'";
            
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<User> getUsers() {
        String query = "select * from user";
        List<User> users = new ArrayList();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                Integer id = rs.getInt("id");
                String anamn = rs.getString("anamn");
                String fnamn = rs.getString("fnamn");
                String enamn = rs.getString("enamn");
                String email = rs.getString("email");
                String admin = rs.getString("admin");
                
                User user = new User(id, anamn, fnamn, enamn, email, admin);
                users.add(user);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
