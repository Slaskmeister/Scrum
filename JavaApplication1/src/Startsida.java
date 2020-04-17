
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellinor
 */
public class Startsida extends javax.swing.JFrame {
    
    private String inloggadPerson;
    private boolean admin;
    private Connection con;
    private PreparedStatement pst;
    private PreparedStatement pst2;
    private ResultSet rs;
    /**
     * Creates new form Startsida
     */
    public Startsida(String anvandarnamn) {
        initComponents();
        inloggadPerson = anvandarnamn;
        lblValkommen.setText("Välkommen" + " " + inloggadPerson);
        con = null;
        pst = null;
        rs = null;
        pst2 = null;
        admin = true;
          try {
            String sqlAdmin = "Select `anamn`, `admin` from `user` where `anamn`=? and `admin`=?";
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
            pst2 = con.prepareStatement(sqlAdmin);
            pst2.setString(1, inloggadPerson);
            pst2.setString(2, "JA");
            rs =pst2.executeQuery();
            if (rs.next()) {
                rs.getString("admin");
                this.admin = true;
            } else {
                this.admin = false;
            }
        } catch (Exception e) {
            System.out.println("Intern felmeddelande, Hittar inte Admin-Status: " + e.getMessage());
        }
    }
    


  public boolean inLoggning(String anvandarnamn, String losenord) throws SQLException {
        String sql = "Select * from `user` where `anamn`=? and `losenord`=?";
        con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        pst = con.prepareStatement(sql);
        pst.setString(1, anvandarnamn);
        pst.setString(2, losenord);
        rs= pst.executeQuery();
        if(rs.next()){
        return true;
        }
        else{
        return false;
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

        lblValkommen = new javax.swing.JLabel();
        btnInformell = new javax.swing.JButton();
        btnFormell = new javax.swing.JButton();
        btnMinaSidor = new javax.swing.JButton();
        btnUtbildning = new javax.swing.JButton();
        btnForskning = new javax.swing.JButton();
        btnSkapaMöte = new javax.swing.JButton();
        btnUtbildning1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblValkommen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblValkommen.setText("Välkommen");

        btnInformell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInformell.setText("Informella bloggen");
        btnInformell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformellActionPerformed(evt);
            }
        });

        btnFormell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFormell.setText("Formella bloggen");
        btnFormell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormellActionPerformed(evt);
            }
        });

        btnMinaSidor.setText("Mina sidor");
        btnMinaSidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinaSidorActionPerformed(evt);
            }
        });

        btnUtbildning.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUtbildning.setText("Utbildning");
        btnUtbildning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtbildningActionPerformed(evt);
            }
        });

        btnForskning.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnForskning.setText("Forskning");
        btnForskning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForskningActionPerformed(evt);
            }
        });

        btnSkapaMöte.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSkapaMöte.setText("Skapa nytt möte");

        btnUtbildning1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUtbildning1.setText("Se kalender");
        btnUtbildning1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtbildning1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblValkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinaSidor)
                .addGap(65, 65, 65))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFormell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnForskning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformell, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUtbildning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSkapaMöte, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(btnUtbildning1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinaSidor))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInformell, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSkapaMöte, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFormell, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUtbildning1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForskning, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUtbildning, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFormellActionPerformed

    private void btnMinaSidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaSidorActionPerformed
        String anvandare = inloggadPerson;
        MinaSidor minsida = new MinaSidor(anvandare);
        minsida.setVisible(true);
        Startsida.this.dispose();    
    }//GEN-LAST:event_btnMinaSidorActionPerformed

    private void btnUtbildningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtbildningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUtbildningActionPerformed

    private void btnForskningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForskningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnForskningActionPerformed

    private void btnUtbildning1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtbildning1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUtbildning1ActionPerformed

    private void btnInformellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformellActionPerformed

    }//GEN-LAST:event_btnInformellActionPerformed

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
            java.util.logging.Logger.getLogger(Startsida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Startsida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Startsida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Startsida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn = "";
                new Startsida(anvandarnamn).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFormell;
    private javax.swing.JButton btnForskning;
    private javax.swing.JButton btnInformell;
    private javax.swing.JButton btnMinaSidor;
    private javax.swing.JButton btnSkapaMöte;
    private javax.swing.JButton btnUtbildning;
    private javax.swing.JButton btnUtbildning1;
    private javax.swing.JLabel lblValkommen;
    // End of variables declaration//GEN-END:variables
}
