/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jenniferhuang
 */
public class Meeting {
    private String meetingName;
    private LocalDateTime date;
    private List<User> invitedUsers;
    
    public Meeting(String meetingName, LocalDateTime date, List<User> invitedUsers) {
        this.meetingName = meetingName;
        this.date = date;
        this.invitedUsers = invitedUsers;
    }
    
    public String getMeetingName() {
        return meetingName;
    }
    
    public LocalDateTime getMeetingDate() {
        return date;
    }
    
    public List<User> getInvitedUsers() {
        return invitedUsers;
    }
}
