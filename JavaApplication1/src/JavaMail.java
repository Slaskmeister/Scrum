import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.Driver;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author William
 */
public class JavaMail {
    
    public static void Skicka()
    {
     
     try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
      
      String query = "SELECT * FROM user";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      
      while (rs.next()) {
        
        String email = rs.getString("email");
        SkickaMail.sendMail(email);
       
         
        
      }
      
     
    } catch (Exception e) { 
        
        System.err.println("fel");
        System.err.println(e.getMessage());
        
    }
  }
}