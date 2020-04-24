/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author jenniferhuang
 */
public class MötesFörslag {
    
    LocalDateTime förslag1;
    LocalDateTime förslag2;
    LocalDateTime förslag3;
    String mötesTitel;
    Integer mötesFk;
    
    public MötesFörslag(LocalDateTime förslag1, LocalDateTime förslag2, LocalDateTime förslag3, Integer mötesFk) {
        this.förslag1 = förslag1;
        this.förslag2 = förslag2;
        this.förslag3 = förslag3;
        this.mötesFk = mötesFk;
    }
    
    public MötesFörslag(LocalDateTime förslag1, LocalDateTime förslag2, LocalDateTime förslag3, Integer mötesFk, String mötesTitel) {
        this.förslag1 = förslag1;
        this.förslag2 = förslag2;
        this.förslag3 = förslag3;
        this.mötesFk = mötesFk;
        this.mötesTitel = mötesTitel;
    }
    
    public LocalDateTime getFörslag1() {
        return förslag1;
    }
    public LocalDateTime getFörslag2() {
        return förslag2;
    }
    public LocalDateTime getFörslag3() {
        return förslag3;
    }
    public Integer getMötesFk() {
        return mötesFk;
    }
    public String getMötesTitel() {
        return mötesTitel;
    }
    @Override
    public String toString() {
        return mötesTitel;
    }
    
}
