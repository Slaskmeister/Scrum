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
import javax.swing.JPasswordField;
import  javax.swing.JTextField;
import javax.swing.JTextArea;

public class Validate {

public static boolean inteTomt(JTextField textField){
if (textField.getText().isEmpty()){
    JOptionPane.showMessageDialog(null, "Textfältet " +  " är tomt!", "Tomt fält", 1);
    return false;
} else {
    return true;
}
}

public static boolean inloggKontroll(JTextField txtAnvandarnamn, JPasswordField txtLosenord){
if (txtAnvandarnamn.getText().isEmpty()|| txtLosenord.getText().isEmpty()){
JOptionPane.showMessageDialog(null, "Vänligen fyll i alla fält", "Tomt fält", 1);
return false;}
else {
return true;}
}

public static boolean inteTomtArea(JTextArea textArea){
if (textArea.getText().isEmpty()){
    JOptionPane.showMessageDialog(null, "Textfältet " +  " är tomt!", "Tomt fält", 1);
    return false;
} else {
    return true;
}
}

}