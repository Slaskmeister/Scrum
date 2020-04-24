/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author ellinor
 */
public class MinaSidor extends javax.swing.JFrame {

    private String inloggadPerson;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
     
   public MinaSidor(String anvandarnamn) throws SQLException {
        initComponents();
        label.setBackground(Color.lightGray);
        label.setOpaque(true);
        lblBekräftelse.setVisible(false);
        inloggadPerson = anvandarnamn;
        pst= null;
        rs=null;
        con= null;
        lblÄndraLösen.setText("Ändra Lösenord för" + " " + inloggadPerson);
        visaBild();
    }


 
    public void bytLösen()  {  
        if(Validate.inteTomt(pwÄndraLösen)){
        try {
        String lösen = pwÄndraLösen.getText();
        String ändraLösen = "UPDATE `user` SET `losenord`=? WHERE `anamn`=?";
        con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        pst=con.prepareStatement(ändraLösen);
        pst.setString(1, lösen);
        pst.setString(2, inloggadPerson);
        pst.executeUpdate();
        lblBekräftelse.setText("Lösenordet har uppdaterats till" + " " + pwÄndraLösen.getText());
        lblBekräftelse.setVisible(true);
        } catch (Exception e) {
            System.out.println("Intern felmeddelande, Hittar inte Admin-Status: " + e.getMessage());
        }
    }
    }
    
     
    public void ändraBild() throws FileNotFoundException {
        
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              label.setIcon(ResizeImage(path));
              InputStream bild= new FileInputStream(selectedFile);
            
             
              try {
               String profilBild = "Update `user` set `bild`=? where `anamn`=?";   
               con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
               pst=con.prepareStatement(profilBild);
               pst.setBinaryStream(1, (InputStream) bild, (int)(selectedFile.length()));
               pst.setString(2,inloggadPerson);
               pst.execute();
              } catch (Exception ex) {
                  System.out.println("Fel" + ex.getMessage());
              }
          }

          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("Ingen fil är vald");
          }
        }
    
       public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
       public void visaBild() throws SQLException{
       
       try{ byte[] imageBytes;
        Image image;
        con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        pst = con.prepareStatement("Select `bild` from `user` where `anamn`=?");
        pst.setString(1,inloggadPerson);
        rs = pst.executeQuery();

        while (rs.next()) {
            imageBytes=rs.getBytes(1);
            image=Toolkit.getDefaultToolkit().createImage(imageBytes);
            Image nyBild = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(nyBild);
            label.setIcon(icon);
        }
       }
       catch (Exception e) {
            System.out.println("Intern felmeddelande" + e.getMessage());
        }
          
       }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblÄndraLösen = new javax.swing.JLabel();
        lblNyttLösen = new javax.swing.JLabel();
        pwÄndraLösen = new javax.swing.JPasswordField();
        lblBekräftelse = new javax.swing.JLabel();
        btnÄndraLösen = new javax.swing.JButton();
        btnMöte = new javax.swing.JButton();
        btnStartsida = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        btnProfilbild = new javax.swing.JButton();
        btnSeSvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblÄndraLösen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblÄndraLösen.setText("Ändra lösenord");

        lblNyttLösen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNyttLösen.setText("Nytt lösenord:");

        pwÄndraLösen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pwÄndraLösen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwÄndraLösenActionPerformed(evt);
            }
        });

        lblBekräftelse.setForeground(new java.awt.Color(0, 204, 0));
        lblBekräftelse.setText("Bekräftelse");

        btnÄndraLösen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnÄndraLösen.setText("Ändra lösenord");
        btnÄndraLösen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnÄndraLösenActionPerformed(evt);
            }
        });

        btnMöte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMöte.setText("Se mötesförfrågningar");
        btnMöte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMöteActionPerformed(evt);
            }
        });

        btnStartsida.setText("Startsida");
        btnStartsida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartsidaActionPerformed(evt);
            }
        });

        label.setBackground(new java.awt.Color(102, 102, 102));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("Profilbild saknas");

        btnProfilbild.setText("Ändra profilbild");
        btnProfilbild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfilbildActionPerformed(evt);
            }
        });

        btnSeSvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSeSvar.setText("Se svar på mötesförfrågningar");
        btnSeSvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeSvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStartsida, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblBekräftelse, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblÄndraLösen)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNyttLösen, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pwÄndraLösen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnSeSvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnMöte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnÄndraLösen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProfilbild, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStartsida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnMöte)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeSvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(lblÄndraLösen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNyttLösen)
                            .addComponent(pwÄndraLösen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnÄndraLösen)
                    .addComponent(btnProfilbild))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBekräftelse)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pwÄndraLösenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwÄndraLösenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwÄndraLösenActionPerformed

    private void btnÄndraLösenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnÄndraLösenActionPerformed
        bytLösen();
    }//GEN-LAST:event_btnÄndraLösenActionPerformed

    private void btnStartsidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartsidaActionPerformed
        try {
            String anvandarnamn = inloggadPerson;
            Startsida start = new Startsida(anvandarnamn);
            start.setVisible(true);
            MinaSidor.this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(MinaSidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStartsidaActionPerformed

    private void btnProfilbildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfilbildActionPerformed
        try {
            ändraBild();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MinaSidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnProfilbildActionPerformed

    private void btnMöteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMöteActionPerformed
                   String anvandarnamn = inloggadPerson;
                   Mötesförfrågning start = new Mötesförfrågning(anvandarnamn);
                   start.setVisible(true);
                   MinaSidor.this.dispose();
    }//GEN-LAST:event_btnMöteActionPerformed

    private void btnSeSvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeSvarActionPerformed
                   String anvandarnamn = inloggadPerson;
                   SeBesvaradeMötesförfrågningar start = new SeBesvaradeMötesförfrågningar(anvandarnamn);
                   start.setVisible(true);
                   MinaSidor.this.dispose();
    }//GEN-LAST:event_btnSeSvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MinaSidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinaSidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinaSidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinaSidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn = "";
                try {
                    new MinaSidor(anvandarnamn).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MinaSidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMöte;
    private javax.swing.JButton btnProfilbild;
    private javax.swing.JButton btnSeSvar;
    private javax.swing.JButton btnStartsida;
    private javax.swing.JButton btnÄndraLösen;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblBekräftelse;
    private javax.swing.JLabel lblNyttLösen;
    private javax.swing.JLabel lblÄndraLösen;
    private javax.swing.JPasswordField pwÄndraLösen;
    // End of variables declaration//GEN-END:variables
}
