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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Meeting;
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
    
    public void createMeeting(Meeting meeting) {
        Integer meetingId = saveMeeting(meeting.getMeetingName(), meeting.getMeetingDate());
        if(meetingId != null) {
            boolean success = addInvitedUsers(meeting.getInvitedUsers(), meetingId);
            if(success) {
                System.out.println("Succes");
            }
        }
    }
    
    private Integer saveMeeting(String meetingName, Date date) {
        try {

            String query = "INSERT INTO `möte`(`titel`, `datum`) VALUES (?, ?)";
            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, meetingName);
            pst.setDate(2, new java.sql.Date(new Date().getTime()));
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
    
    private boolean addInvitedUsers(List<User> invitedUsers, Integer meetingId) {
        String query = "INSERT INTO `user_möte`(`user_fk`, `möte_fk`) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(query);
            
            for(User user : invitedUsers) {
                Integer userId = user.getId();
                pst.setInt(1, userId);
                pst.setInt(2, meetingId);
                pst.addBatch();
            }
            pst.executeBatch();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
