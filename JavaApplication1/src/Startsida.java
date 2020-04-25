
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String id;
    private PreparedStatement pstm;
    private ResultSet rs1;
    private String person;
    private PreparedStatement pstmt;
    private ResultSet rs2;
    private PreparedStatement ps;
    private ResultSet rs3;
    private String möte;
    /**
     * Creates new form Startsida
     */
    public Startsida(String anvandarnamn) throws SQLException {
        initComponents();
        inloggadPerson = anvandarnamn;
        lblValkommen.setText("Välkommen" + " " + inloggadPerson);
        con = null;
        pst = null;
        rs = null;
        pst2 = null;
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
          if(admin == false){
          btnAdmin.setVisible(false);
          }
          fyllComboBox();
          pnlAdmin.setVisible(false);
          lblBekräftelse.setVisible(false);
          
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
  
  public void fyllComboBox() {
      cbPerson.removeAllItems();
      cbPerson.addItem("Välj");
    String sql = "Select `fnamn` from `user` where `admin`=?";
        try {
          con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
          pst = con.prepareStatement(sql);
          pst.setString(1, "NEJ");
          rs= pst.executeQuery();
          while(rs.next()){
          cbPerson.addItem(rs.getString(1));
          }
        } catch (SQLException ex) {
            Logger.getLogger(Startsida.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
  
  public void gömKnappar(){
      btnFormell.setVisible(false);
      btnInformell.setVisible(false);
      btnForskning.setVisible(false);
      btnUtbildning.setVisible(false);
      btnMinaSidor.setVisible(false);
      btnSkapaMöte.setVisible(false);
      btnAdmin.setVisible(false);
      btnKalender.setVisible(false);
      lblValkommen.setVisible(false);
      btnMöte.setVisible(false);
  }
  
  public void visaKnappar(){
      btnFormell.setVisible(true);
      btnInformell.setVisible(true);
      btnForskning.setVisible(true);
      btnUtbildning.setVisible(true);
      btnMinaSidor.setVisible(true);
      btnSkapaMöte.setVisible(true);
      btnAdmin.setVisible(true);
      btnKalender.setVisible(true);  
     lblValkommen.setVisible(true);
     btnMöte.setVisible(true);
  }

    public void nyAdmin(){
    
        String sql ="update `user` set `admin`=? where `fnamn`=?";
        String admin = cbPerson.getSelectedItem().toString();
        String admin1 = "JA";
        try {
        con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        pst=con.prepareStatement(sql);
        pst.setString(1, admin1);
        pst.setString(2, admin);
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Startsida.class.getName()).log(Level.SEVERE, null, ex);
        }
     pnlAdmin.setVisible(false);
     visaKnappar();
     lblBekräftelse.setText(admin + " " + "har nu adminstatus");
     lblBekräftelse.setVisible(true);
    }
    

    public void notis() throws SQLException{
    String sql = "SELECT `user_fk` from `user_möte`";
    con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
    pst=con.prepareStatement(sql);
    rs = pst.executeQuery(); 
    while(rs.next()){
    id = rs.getString("user_fk");
    String sql1 = "SELECT `id` from `user` where `anamn`=?";
    con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
    pstm= con.prepareStatement(sql1);
    pstm.setString(1, inloggadPerson);
    rs1 = pstm.executeQuery();
    if(rs1.next()){
    person = rs1.getString("id");
    }}
    if(id.equals(person)){
    String sql2 = "Select `möte_fk` from `user_möte` where `user_fk`=?";
    pstmt = con.prepareStatement(sql2);
    pstmt.setString(1, (String) id);
    rs2 = pstmt.executeQuery();
    while(rs2.next()){
    möte = rs2.getString("möte_fk");
    String sql3 = "Select `datum` from `möte` where `ID`=?";
    ps = con.prepareStatement(sql3);
    ps.setString(1, möte);
    rs3 = ps.executeQuery();
     while(rs3.next()){
         if(rs3.getString("datum") != null){
         String datum = rs3.getString("datum");
        JOptionPane.showMessageDialog(null, "Du har ett möte inbokat:" + datum, "Inbokade möten", JOptionPane.PLAIN_MESSAGE);
         }}}}
    else {
     System.out.println("Du har inga inbokade möten!");
     JOptionPane.showMessageDialog(null, "Du har inga möten inbokade", "Inbokade möten", JOptionPane.PLAIN_MESSAGE);
    }}
    
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
        btnKalender = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        lblBekräftelse = new javax.swing.JLabel();
        btnMöte = new javax.swing.JButton();
        pnlAdmin = new javax.swing.JPanel();
        cbPerson = new javax.swing.JComboBox<>();
        lblAdmin = new javax.swing.JLabel();
        lblVälj = new javax.swing.JLabel();
        btnGeAdmin = new javax.swing.JButton();

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
        btnSkapaMöte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkapaMöteActionPerformed(evt);
            }
        });

        btnKalender.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnKalender.setText("Se kalender");
        btnKalender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKalenderActionPerformed(evt);
            }
        });

        btnAdmin.setText("Lägg till ny admin");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        lblBekräftelse.setForeground(new java.awt.Color(0, 204, 0));
        lblBekräftelse.setText("Bekräftelse");

        btnMöte.setText("Mötesnotiser");
        btnMöte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMöteActionPerformed(evt);
            }
        });

        cbPerson.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdmin.setText("Lägg till ny admin");

        lblVälj.setText("Välj användare:");

        btnGeAdmin.setText("Ge adminstatus");
        btnGeAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAdminLayout = new javax.swing.GroupLayout(pnlAdmin);
        pnlAdmin.setLayout(pnlAdminLayout);
        pnlAdminLayout.setHorizontalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdmin)
                    .addGroup(pnlAdminLayout.createSequentialGroup()
                        .addComponent(lblVälj, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(321, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdminLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        pnlAdminLayout.setVerticalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblAdmin)
                .addGap(71, 71, 71)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVälj)
                    .addComponent(cbPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(btnGeAdmin)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblValkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUtbildning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSkapaMöte, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(btnKalender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMöte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMinaSidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65))))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBekräftelse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnForskning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFormell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInformell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                .addContainerGap(328, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinaSidor))
                .addGap(2, 2, 2)
                .addComponent(btnMöte)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInformell, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSkapaMöte, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFormell, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKalender, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForskning, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUtbildning, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBekräftelse)
                    .addComponent(btnAdmin))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormellActionPerformed
        String anvandare = inloggadPerson;
        formellForumMedKommentar forum = new formellForumMedKommentar(anvandare);
        forum.setVisible(true);
        Startsida.this.dispose(); 
    }//GEN-LAST:event_btnFormellActionPerformed

    private void btnMinaSidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaSidorActionPerformed
        String anvandare = inloggadPerson;
        MinaSidor minsida =null;
        try {
            minsida = new MinaSidor(anvandare);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        minsida.setVisible(true);
        Startsida.this.dispose();    
    }//GEN-LAST:event_btnMinaSidorActionPerformed

    private void btnUtbildningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtbildningActionPerformed
        String anvandare = inloggadPerson;
        UtbildningsForumMedKommentar forum = new UtbildningsForumMedKommentar(anvandare);
        forum.setVisible(true);
        Startsida.this.dispose();
    }//GEN-LAST:event_btnUtbildningActionPerformed

    private void btnForskningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForskningActionPerformed
        String anvandare = inloggadPerson;
        ForskningForumMedKommentar forum = new ForskningForumMedKommentar(anvandare);
        forum.setVisible(true);
        Startsida.this.dispose(); 
    }//GEN-LAST:event_btnForskningActionPerformed

    private void btnKalenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKalenderActionPerformed
        String anvandarnamn = inloggadPerson;
        Kalender forum = new Kalender(anvandarnamn);
        forum.setVisible(true);
        Startsida.this.dispose();
    }//GEN-LAST:event_btnKalenderActionPerformed

    private void btnInformellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformellActionPerformed


        try {
            String anvandare = inloggadPerson;
            bloggForumMedKommentar forum = new bloggForumMedKommentar(anvandare);
            forum.setVisible(true); 
            Startsida.this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Startsida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInformellActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        gömKnappar();
        lblBekräftelse.setVisible(false);
        pnlAdmin.setVisible(true);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnGeAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeAdminActionPerformed
       nyAdmin();
    }//GEN-LAST:event_btnGeAdminActionPerformed

    private void btnSkapaMöteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkapaMöteActionPerformed
        String anvandarnamn = inloggadPerson;
        SkapaMöte forum = new SkapaMöte(anvandarnamn);
        forum.setVisible(true);
        Startsida.this.dispose();
    }//GEN-LAST:event_btnSkapaMöteActionPerformed

    private void btnMöteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMöteActionPerformed
        try {
            notis();
        } catch (SQLException ex) {
            Logger.getLogger(Startsida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMöteActionPerformed

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
                try {
                    new Startsida(anvandarnamn).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Startsida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnFormell;
    private javax.swing.JButton btnForskning;
    private javax.swing.JButton btnGeAdmin;
    private javax.swing.JButton btnInformell;
    private javax.swing.JButton btnKalender;
    private javax.swing.JButton btnMinaSidor;
    private javax.swing.JButton btnMöte;
    private javax.swing.JButton btnSkapaMöte;
    private javax.swing.JButton btnUtbildning;
    private javax.swing.JComboBox<String> cbPerson;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblBekräftelse;
    private javax.swing.JLabel lblValkommen;
    private javax.swing.JLabel lblVälj;
    private javax.swing.JPanel pnlAdmin;
    // End of variables declaration//GEN-END:variables
}
