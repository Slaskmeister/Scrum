/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;

/**
 *
 * @author sebob
 */
public class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".pdf" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".pdf")||file.getAbsolutePath().endsWith(".docx");
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            
            return "Fel filformat! Filen får endast vara en .pdf eller .docx";
        }
    } 
