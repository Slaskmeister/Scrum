import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JSplitPane.TOP;
import static javax.swing.text.html.HTML.Tag.SELECT;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.lang.String;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Simon Berg
 */
public class bloggForumMedKommentar extends javax.swing.JFrame {
    private String inloggadPerson;
    private String typ;
    private boolean admin;
    private String värde; 
    private String värde2;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Statement st = null;
    Timer update;
    String anvandare;
    private Boolean harVärde;
    

    /**
     * Creates new form bloggForum
     */
       public bloggForumMedKommentar(String anvandare) throws SQLException{
        initComponents();
        typ = "informell";
        HamtaInlagg();
        inloggadPerson = anvandare;
        admin=true;
        
        
         try {
            String sqlAdmin = "Select `anamn`, `admin` from `user` where `anamn`=? and `admin`=?";
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
            pstmt = con.prepareStatement(sqlAdmin);
            pstmt.setString(1, inloggadPerson);
            pstmt.setString(2, "JA");
            rs =pstmt.executeQuery();
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
     public void HamtaInlagg() throws SQLException{
     try
     {          
       String sql = "Select * from `post`";
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       pstmt = con.prepareStatement(sql);
       rs =pstmt.executeQuery();  
       tblInlägg.setModel(DbUtils.resultSetToTableModel(rs));
    }
            
        
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }
 }   
             
    public void runInlaggUpdate() throws SQLException, InterruptedException{
        ActionListener upd = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
            };
            Timer update = new Timer(1000, upd);
            update.start();
            Thread.sleep(2000);

            while (update.isRunning() && tblInlägg.isVisible());
            {
            //HamtaInlagg();
            JOptionPane.showMessageDialog(null, "Timer Fungerar");
            }
            if (!tblInlägg.isVisible());
            update.stop();
    }
        private void TaBortVärde(){
            try
     {          
       String sqlr = "Delete from `post` where `postid`="+värde;
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       st = con.prepareStatement(sqlr);
       st.executeUpdate(sqlr);
     }
            catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
    }
        public boolean KollaVärde(){
        harVärde = false;
        if (värde==null){
        harVärde=false;
        }
                else{
                harVärde=true;
                }
        return harVärde;
        }
        
       public void visaInlägg() throws SQLException{
       String inlägg;
       inlägg = tblInlägg.getValueAt(tblInlägg.getSelectedRow(), 1).toString();
              
       try{ 
        byte[] imageBytes;
        Image image;
        con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
        pstmt = con.prepareStatement("Select `bild` from `post` where `Text`=?");
        pstmt.setString(1,inlägg);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            imageBytes=rs.getBytes(1);
            image=Toolkit.getDefaultToolkit().createImage(imageBytes);
            Image nyBild = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(nyBild);
                        JOptionPane.showMessageDialog(
                        null,
                        new JLabel(inlägg, icon, JLabel.LEFT),
                        "Inlägg", JOptionPane.PLAIN_MESSAGE);
           
                  
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

        btnTillbaka = new javax.swing.JButton();
        btnTest = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInlägg = new javax.swing.JTable();
        btnTaBort = new javax.swing.JButton();
        btnLäsInlägg = new javax.swing.JButton();
        jtKommentar = new javax.swing.JTextField();
        btnKommentera = new javax.swing.JButton();
        btnVisaKommentarer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTillbaka.setText("Startsida");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        btnTest.setText("Skriv nytt inlagg");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        tblInlägg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alla inlägg"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInlägg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInläggMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInlägg);

        btnTaBort.setText("Ta Bort Inlägg");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        btnLäsInlägg.setText("Läs Inlägg");
        btnLäsInlägg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLäsInläggActionPerformed(evt);
            }
        });

        btnKommentera.setText("Kommentera");
        btnKommentera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKommenteraActionPerformed(evt);
            }
        });

        btnVisaKommentarer.setText("Visa kommentarer");
        btnVisaKommentarer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaKommentarerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnLäsInlägg)
                .addGap(18, 18, 18)
                .addComponent(btnTaBort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jtKommentar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKommentera)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnTest)
                .addGap(47, 47, 47)
                .addComponent(btnVisaKommentarer)
                .addGap(36, 36, 36)
                .addComponent(btnTillbaka)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLäsInlägg)
                        .addComponent(btnTaBort))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtKommentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnKommentera, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnTest)
                    .addComponent(btnVisaKommentarer))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        try {
            String anvandare = inloggadPerson;
            Startsida skapa = new Startsida(anvandare);
            skapa.setVisible(true);
            bloggForumMedKommentar.this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(bloggForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTillbakaActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
                   String anvandare = inloggadPerson;
                   SkapaInlägg skapa = new SkapaInlägg(anvandare);
                   skapa.setVisible(true);
                   bloggForumMedKommentar.this.dispose();
    }//GEN-LAST:event_btnTestActionPerformed

    private void tblInläggMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInläggMouseClicked
       värde=null;
       värde2=null;
        int kolumn = 0;
        int rad = tblInlägg.getSelectedRow();                                   //Visar markrad rad i tabellen
        värde = tblInlägg.getModel().getValueAt(rad, kolumn).toString();
        värde2 = tblInlägg.getModel().getValueAt(rad, 2).toString();
    }//GEN-LAST:event_tblInläggMouseClicked

    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        if (admin == true){
        
        KollaVärde();
        if(harVärde = true){
                TaBortVärde();
            try {
                HamtaInlagg();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
                } }
        else if(värde2.equals(inloggadPerson)){
        TaBortVärde();
            try {
                HamtaInlagg();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else {
             JOptionPane.showMessageDialog(null, "Du är inte behörig att ta bort detta inlägg");
        }
    }//GEN-LAST:event_btnTaBortActionPerformed

    private void btnLäsInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLäsInläggActionPerformed
        try {
            visaInlägg();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(bloggForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLäsInläggActionPerformed

    private void btnVisaKommentarerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaKommentarerActionPerformed
        if(tblInlägg.getSelectionModel().isSelectionEmpty()){
        
        JOptionPane.showMessageDialog(null, "Välj en post");
        }
        
        else{
         try {   
        int i = tblInlägg.getSelectedRow();    
        TableModel model = tblInlägg.getModel();
        int PostID = Integer.parseInt(model.getValueAt(i,0).toString());
            
        new Kommentarer(PostID,typ).setVisible(true);
        
            } catch (SQLException ex) {
                Logger.getLogger(formellForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    }//GEN-LAST:event_btnVisaKommentarerActionPerformed

    private void btnKommenteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKommenteraActionPerformed
         
        String nyKommentar = jtKommentar.getText().toString();
        
        if( jtKommentar.getText().isEmpty()||tblInlägg.getSelectionModel().isSelectionEmpty())
        {
           
        JOptionPane.showMessageDialog(null, "Välj en post","Felmeddelande",JOptionPane.PLAIN_MESSAGE);
        
        }else
        {
         try {
         String anvandare = inloggadPerson;
         con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
         pstmt = con.prepareStatement("SELECT * FROM `user` WHERE `anamn`=?");
         pstmt.setString(1,anvandare);
         rs =pstmt.executeQuery();
         
         if (rs.next()){
         int senderID = rs.getInt("id");
         
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	 java.util.Date date = new java.util.Date();
         String timestamp= dateFormat.format(date);
         
         int i = tblInlägg.getSelectedRow();    
         TableModel model = tblInlägg.getModel();
         int PostID = Integer.parseInt(model.getValueAt(i,0).toString());
         String SQLInsert = "INSERT INTO `kommentarer`(`Timestamp`, `Text`, `Post_ID`, `User_ID`, `Typ`) VALUES (?,?,?,?,?)";
         pstmt=con.prepareStatement(SQLInsert);
         pstmt.setString(1, timestamp);
         pstmt.setString(2, nyKommentar);
         pstmt.setInt(3, PostID);
         pstmt.setInt(4,senderID);
         pstmt.setString(5,typ);
         pstmt.executeUpdate();
         JOptionPane.showMessageDialog(null, "Kommentaren är postad","",JOptionPane.PLAIN_MESSAGE);
         jtKommentar.setText("");
         }
        }   
         catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }   
            
            
        }
            
         
    }//GEN-LAST:event_btnKommenteraActionPerformed

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
            java.util.logging.Logger.getLogger(bloggForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bloggForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bloggForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bloggForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String anvandarnamn="";
                    new bloggForumMedKommentar(anvandarnamn).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(bloggForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKommentera;
    private javax.swing.JButton btnLäsInlägg;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JToggleButton btnTest;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton btnVisaKommentarer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtKommentar;
    private javax.swing.JTable tblInlägg;
    // End of variables declaration//GEN-END:variables
}   
        
