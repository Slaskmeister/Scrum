/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Meeting;
import models.MötesFörslag;
import models.User;

/**
 *
 * @author jenniferhuang
 */
public class MeetingRepository {
    
    private Connection con;
    private PreparedStatement pst;
    private ResultSet res ;
    
    public MeetingRepository() {
            try {
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer createMeeting(Meeting meeting) {
        try {
            //Timestamp meetingTimestamp = Timestamp.valueOf(meetingDate);
            String query = "INSERT INTO `möte`(`titel`) VALUES (?)";
            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, meeting.getMeetingName());
            pst.executeUpdate();
            
            ResultSet keys = pst.getGeneratedKeys();
            
           if(keys.next()) {
               Integer meetingId = keys.getInt(1);
               return meetingId;
           }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void bjudInAnvändareTillMöte(List<User> invitedUsers, Integer mötesFk) {
        String query = "INSERT INTO `user_möte`(`user_fk`, `möte_fk`) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(query);
            
            for(User user : invitedUsers) {
                Integer userId = user.getId();
                pst.setInt(1, userId);
                pst.setInt(2, mötesFk);
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void skapaMötesFörslag(MötesFörslag mötesFörslag) {
        try {
            Timestamp timestamp1 = Timestamp.valueOf(mötesFörslag.getFörslag1());
            Timestamp timestamp2 = Timestamp.valueOf(mötesFörslag.getFörslag2());
            Timestamp timestamp3 = Timestamp.valueOf(mötesFörslag.getFörslag3());
  
            String query = "INSERT INTO `möte_tid_förslag`(`möte_fk`, `tid_förslag_1`, `tid_förslag_2`,`tid_förslag_3`) "
                    + "VALUES (?,?,?,?)";
            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, mötesFörslag.getMötesFk());
            pst.setTimestamp(2, timestamp1);
            pst.setTimestamp(3, timestamp2);
            pst.setTimestamp(4, timestamp3);
            pst.executeUpdate();
            
            ResultSet keys = pst.getGeneratedKeys();
            
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean bekräftaMöteFörAnvändare(Integer möteId, Integer userId, LocalDateTime bekräftatTid) {
        try {
            String query = "UPDATE USER_MÖTE\n" +
                    "SET bäst_tid = ? \n" +
                    "WHERE MÖTE_FK = ? \n" +
                    "AND USER_FK = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setTimestamp(1, Timestamp.valueOf(bekräftatTid));
            pst.setInt(2, möteId);
            pst.setInt(3, userId);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<MötesFörslag> getMeetingsForUser(Integer userId) {
        
        List<MötesFörslag> mötesFörslagFörAnvändare = new ArrayList();
        try {
           
            String query = "select möte.id, möte.titel, möte_tid_förslag.tid_förslag_1, möte_tid_förslag.tid_förslag_2, möte_tid_förslag.tid_förslag_3\n" +
                            "from möte\n" +
                            "join möte_tid_förslag\n" +
                            "on möte_tid_förslag.möte_fk = möte.id\n" +
                            "join user_möte\n" +
                            "on user_möte.möte_fk = möte.id\n" +
                            "where user_möte.user_fk = " + userId;
            
           
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
            
            while(rs.next()) {
                Integer mötesId = rs.getInt("id");
                String mötesTitel = rs.getString("titel");
                LocalDateTime förslag1 = rs.getTimestamp("tid_förslag_1").toLocalDateTime();
                LocalDateTime förslag2 = rs.getTimestamp("tid_förslag_2").toLocalDateTime();
                LocalDateTime förslag3 = rs.getTimestamp("tid_förslag_3").toLocalDateTime();
                MötesFörslag förslag = new MötesFörslag(förslag1, förslag2, förslag3, mötesId, mötesTitel);
                mötesFörslagFörAnvändare.add(förslag);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mötesFörslagFörAnvändare;
    }
   
    }

