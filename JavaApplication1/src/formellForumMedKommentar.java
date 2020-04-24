


import com.itextpdf.text.Document;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebbe
 */
public class formellForumMedKommentar extends javax.swing.JFrame {
    private String inloggadPerson;
    private String typ;
    private PreparedStatement pst2;
    private ResultSet rs;
    private Connection con;
    private boolean admin;
    private FileOutputStream output;
    private InputStream input;
    private Document doc;
    private Desktop dsk;
    private String sparaFil;
    /**
     * Creates new form formellForum
     */
    public formellForumMedKommentar(String anvandarnamn) {
        initComponents();
        typ= "formell";
        pst2=null;
        rs=null;
        con= null;
        admin = true;
        inloggadPerson = anvandarnamn;
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
        if (admin == true){
        btnLäggTillKategori.setVisible(true);
        }
        else {
        btnLäggTillKategori.setVisible(false);
        }
        fyllComboBox();
    }
    public void öppnaPdf() {
        byte[] fileBytes;
        String query; 
        String postID = tblPoster.getValueAt(tblPoster.getSelectedRow(), 4).toString();
        try {
            query = 
             "select `fil` from `dokument` where `Fil_ID`= " + postID;
             con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(query);
            output = new FileOutputStream(new File("Dokument"));
            File file = new File("Dokument");
            
        while (rs.next()) {

            input = rs.getBinaryStream("fil"); 
            int r = 0;

            while ((r = input.read()) != -1) {
                output.write(r);

            }
        }
        
        Desktop.getDesktop().open(file);
       }
        
       catch(Exception e){
       System.out.println(e.getMessage());
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

        lblVäljKategori = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        btnVisaPost = new javax.swing.JButton();
        btnLäggTillKategori = new javax.swing.JButton();
        btnSkapaInlägg = new javax.swing.JButton();
        btnStartsida = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPoster = new javax.swing.JTable();
        btnTaBort = new javax.swing.JButton();
        btnVisaKommentera = new javax.swing.JButton();
        btnKommentera = new javax.swing.JButton();
        jtKommentar = new javax.swing.JTextField();
        btnVisaInlägg = new javax.swing.JButton();
        bntÖppna = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblVäljKategori.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVäljKategori.setText("Välj kategori");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVisaPost.setText("Visa poster");
        btnVisaPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaPostActionPerformed(evt);
            }
        });

        btnLäggTillKategori.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLäggTillKategori.setText("Lägg till kategori");
        btnLäggTillKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLäggTillKategoriActionPerformed(evt);
            }
        });

        btnSkapaInlägg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSkapaInlägg.setText("Skapa inlägg");
        btnSkapaInlägg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkapaInläggActionPerformed(evt);
            }
        });

        btnStartsida.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnStartsida.setText("Startsida");
        btnStartsida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartsidaActionPerformed(evt);
            }
        });

        tblPoster.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPoster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Postinlägg"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPoster);
        tblPoster.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnTaBort.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btnTaBort.setText("Ta bort inlägg");

        btnVisaKommentera.setText("Visa Kommentarer");
        btnVisaKommentera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaKommenteraActionPerformed(evt);
            }
        });

        btnKommentera.setText("Kommentera");
        btnKommentera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKommenteraActionPerformed(evt);
            }
        });

        jtKommentar.setColumns(3);

        btnVisaInlägg.setText("Läs inlägg");
        btnVisaInlägg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaInläggActionPerformed(evt);
            }
        });

        bntÖppna.setText("Öppna bifogad fil");
        bntÖppna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntÖppnaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVäljKategori)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnVisaPost, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnVisaInlägg, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTaBort)
                                .addGap(18, 18, 18)
                                .addComponent(btnVisaKommentera)
                                .addGap(53, 53, 53)
                                .addComponent(jtKommentar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnKommentera, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bntÖppna)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSkapaInlägg)
                                .addGap(67, 67, 67)
                                .addComponent(btnLäggTillKategori)))
                        .addGap(74, 74, 74)
                        .addComponent(btnStartsida)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVäljKategori)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnVisaPost)
                        .addGap(24, 24, 24)
                        .addComponent(btnVisaInlägg))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaBort)
                    .addComponent(btnVisaKommentera)
                    .addComponent(jtKommentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKommentera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntÖppna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSkapaInlägg)
                    .addComponent(btnLäggTillKategori)
                    .addComponent(btnStartsida))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartsidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartsidaActionPerformed
                   String anvandarnamn = inloggadPerson;
                   Startsida start = new Startsida(anvandarnamn);
                   start.setVisible(true);
                   formellForumMedKommentar.this.dispose();
    }//GEN-LAST:event_btnStartsidaActionPerformed

    private void btnLäggTillKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLäggTillKategoriActionPerformed
                   String anvandarnamn = inloggadPerson;
                   Sebbe start = new Sebbe(anvandarnamn);
                   start.setVisible(true);
    }//GEN-LAST:event_btnLäggTillKategoriActionPerformed

    private void btnVisaPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaPostActionPerformed
        try {
            HamtaInlagg();
        } catch (Exception ex) {
         System.out.println("Det gick inte att visa inlägg" + ex.getMessage());
        }
    }//GEN-LAST:event_btnVisaPostActionPerformed

    private void btnSkapaInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkapaInläggActionPerformed
                   String anvandarnamn = inloggadPerson;
                   SkapaInläggFormell skapa = new SkapaInläggFormell(anvandarnamn);
                   skapa.setVisible(true);
                   formellForumMedKommentar.this.dispose();
    }//GEN-LAST:event_btnSkapaInläggActionPerformed

    private void btnVisaKommenteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaKommenteraActionPerformed
       
        if(tblPoster.getSelectionModel().isSelectionEmpty()){
        
        JOptionPane.showInternalMessageDialog(rootPane, "Välj en post");
        }
        
        else{
         try {   
        int i = tblPoster.getSelectedRow();    
        TableModel model = tblPoster.getModel();
        int PostID = Integer.parseInt(model.getValueAt(i,0).toString());
        
          
        new Kommentarer(PostID,typ).setVisible(true);
        
            } catch (SQLException ex) {
                Logger.getLogger(formellForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    }//GEN-LAST:event_btnVisaKommenteraActionPerformed

    private void btnKommenteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKommenteraActionPerformed
        
        String nyKommentar = jtKommentar.getText().toString();
        
        if( jtKommentar.getText().isEmpty()||tblPoster.getSelectionModel().isSelectionEmpty())
        {
           
        JOptionPane.showInternalMessageDialog(rootPane, "Välj en post");
        
        }else
        {
         try
        {
         con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
         
       
         
         String sqlUserid = "SELECT `id` FROM user WHERE anamn='?'";
         con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
         pst2 = con.prepareStatement(sqlUserid);
         pst2.setString(1,inloggadPerson);
         rs =pst2.executeQuery();
         
        
        
         int senderID= rs.getInt(1);
         
         
         
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	 Date date = new Date();
         String timestamp= dateFormat.format(date);
         
         
         
         
         int i = tblPoster.getSelectedRow();    
         TableModel model = tblPoster.getModel();
         int PostID = Integer.parseInt(model.getValueAt(i,0).toString());
         String SQLInsert = "INSERT INTO `kommentarer`(`Timestamp`, `Text`, `Post_ID`, `User_ID`, `Typ`) VALUES (?,?,?,?,?)";
         pst2=con.prepareStatement(SQLInsert);
         pst2.setString(1, timestamp);
         pst2.setString(2, nyKommentar);
         pst2.setInt(3, PostID);
         pst2.setInt(4,senderID);
         pst2.setString(5,typ);
         pst2.executeUpdate();
         JOptionPane.showInternalMessageDialog(rootPane, "Kommentaren är postad");
         jtKommentar.setText("");
         
        }   
         catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }   
            
            
        }
            
            
        
    }//GEN-LAST:event_btnKommenteraActionPerformed

    private void btnVisaInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaInläggActionPerformed
       
       String inlägg;
       inlägg = tblPoster.getValueAt(tblPoster.getSelectedRow(), 1).toString();

       JOptionPane.showMessageDialog(null, inlägg, "Inlägg", JOptionPane.PLAIN_MESSAGE);
       
    
    }//GEN-LAST:event_btnVisaInläggActionPerformed

    private void bntÖppnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntÖppnaActionPerformed
       öppnaPdf();
    }//GEN-LAST:event_bntÖppnaActionPerformed
     public void HamtaInlagg() throws SQLException{
     try
     { 
       String valdKategori = cbKategori.getSelectedItem().toString();
       String sql = "Select * from `formella poster` where `kategori_fk`=?";
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       pst2 = con.prepareStatement(sql);
       pst2.setString(1, valdKategori);
       rs =pst2.executeQuery();
       tblPoster.setModel(DbUtils.resultSetToTableModel(rs));
        tblPoster.getTableHeader();
        tblPoster.getColumnModel().getColumn(2).setHeaderValue("Kategori");
        tblPoster.getColumnModel().getColumn(4).setHeaderValue("Dokument");
        tblPoster.getTableHeader().repaint();
    }
            
        
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }
 }
    
    
    private void fyllComboBox(){
    cbKategori.removeAllItems();
    cbKategori.addItem("Välj");
    try{
    con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
           String sql = "select namn from kategorier";
           PreparedStatement pst2 = con.prepareStatement(sql);
           ResultSet rs = pst2.executeQuery();
           while (rs.next()){
           cbKategori.addItem(rs.getString(1));
           }
    }
    catch (Exception E){
    System.out.println("Gick ej att fylla box" + E.getMessage());
    }
    
    }
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
            java.util.logging.Logger.getLogger(formellForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formellForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formellForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formellForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn = "";
                new formellForumMedKommentar(anvandarnamn).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntÖppna;
    private javax.swing.JButton btnKommentera;
    private javax.swing.JButton btnLäggTillKategori;
    private javax.swing.JButton btnSkapaInlägg;
    private javax.swing.JButton btnStartsida;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnVisaInlägg;
    private javax.swing.JButton btnVisaKommentera;
    private javax.swing.JButton btnVisaPost;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtKommentar;
    private javax.swing.JLabel lblVäljKategori;
    private javax.swing.JTable tblPoster;
    // End of variables declaration//GEN-END:variables
}
