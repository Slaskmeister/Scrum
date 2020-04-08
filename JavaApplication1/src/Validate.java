/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebbe
 */
import  javax.swing.JOptionPane;
import  javax.swing.JTextField;

public class Validate {

public static boolean inteTomt(JTextField textField){
if (textField.getText().isEmpty()){
    JOptionPane.showMessageDialog(null, "Textfältet " + textField.getToolTipText() + " är tomt!", "Tomt fält", 1);
    return false;
} else {
    return true;
}
}
}