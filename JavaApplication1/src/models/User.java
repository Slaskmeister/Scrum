/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jenniferhuang
 */
public class User {
    private Integer id;
    private String anamn;
    private String fnamn;
    private String enamn;
    private String email;
    private String admin;
    private String text;
    
    public User(Integer id, String anamn, String fnamn, String enamn, String email, String admin) {
        this.id = id;
        this.anamn = anamn;
        this.fnamn = fnamn;
        this.enamn = enamn;
        this.email = email;
        this.admin = admin;
        this.text = anamn;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getANamn() {
        return anamn;
    }
    
    @Override
    public String toString() {
        return text;
    }
   
}
