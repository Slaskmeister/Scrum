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
    
    public Meeting(String meetingName) {
        this.meetingName = meetingName;
    }
    
    public String getMeetingName() {
        return meetingName;
    }
    
}
