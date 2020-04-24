import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Axel Rosén
 */
public class UtbildningsForumMedKommentar extends javax.swing.JFrame {

    private String inloggadPerson;
    private String typ;
    private PreparedStatement pst2;
    private ResultSet rs;
    private Connection con;
    private boolean admin;
    private String värde;
    private boolean harVärde;
    private String värde2;
   
    public UtbildningsForumMedKommentar(String anvandarnamn) {
        initComponents();
        typ = "utbildning";
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
        
        fyllComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTaBort = new javax.swing.JButton();
        btnVisaInlägg = new javax.swing.JButton();
        lblVäljKategori = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        btnVisaPost = new javax.swing.JButton();
        btnSkapaInlägg = new javax.swing.JButton();
        btnStartsida = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPoster = new javax.swing.JTable();
        btnKommentarer = new javax.swing.JButton();
        jtKommentar = new javax.swing.JTextField();
        jtKommentera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTaBort.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btnTaBort.setText("Ta bort inlägg");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        btnVisaInlägg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnVisaInlägg.setText("Läs inlägg");
        btnVisaInlägg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaInläggActionPerformed(evt);
            }
        });

        lblVäljKategori.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVäljKategori.setText("Välj kategori");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriActionPerformed(evt);
            }
        });

        btnVisaPost.setText("Visa alla inlägg");
        btnVisaPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaPostActionPerformed(evt);
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
        tblPoster.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPosterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPoster);

        btnKommentarer.setText("Visa Kommentarer");
        btnKommentarer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKommentarerActionPerformed(evt);
            }
        });

        jtKommentera.setText("Kommentera");
        jtKommentera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtKommenteraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSkapaInlägg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVäljKategori)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisaPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKommentarer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtKommentar)
                    .addComponent(jtKommentera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btnStartsida)
                        .addGap(0, 318, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVisaInlägg)
                                .addGap(18, 18, 18)
                                .addComponent(btnTaBort)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVäljKategori)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisaPost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKommentarer)
                        .addGap(39, 39, 39)
                        .addComponent(jtKommentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtKommentera))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaBort)
                            .addComponent(btnVisaInlägg))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSkapaInlägg)
                            .addComponent(btnStartsida))
                        .addGap(8, 8, 8))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnVisaInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaInläggActionPerformed
        visaInlägg();
    }//GEN-LAST:event_btnVisaInläggActionPerformed

    private void btnVisaPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaPostActionPerformed
        try {
           HamtaInlagg();
        } catch (Exception ex) {
            System.out.println("Det gick inte att visa inlägg" + ex.getMessage());
        }
    }//GEN-LAST:event_btnVisaPostActionPerformed

    private void btnSkapaInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkapaInläggActionPerformed
        String anvandarnamn = inloggadPerson;
        SkapaInläggUtbildning skapa = new SkapaInläggUtbildning(anvandarnamn);
        skapa.setVisible(true);
        UtbildningsForumMedKommentar.this.dispose();
    }//GEN-LAST:event_btnSkapaInläggActionPerformed

    private void btnStartsidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartsidaActionPerformed
        try {
            String anvandarnamn = inloggadPerson;
            Startsida start = new Startsida(anvandarnamn);
            start.setVisible(true);
            UtbildningsForumMedKommentar.this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(UtbildningsForumMedKommentar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStartsidaActionPerformed

    private void tblPosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPosterMouseClicked
        värde=null;
        värde2=null;
        int kolumn = 0;
        
        int rad = tblPoster.getSelectedRow();                                   //Visar markrad rad i tabellen
        värde = tblPoster.getModel().getValueAt(rad, kolumn).toString();
        värde2 = tblPoster.getModel().getValueAt(rad, 2).toString();
    }//GEN-LAST:event_tblPosterMouseClicked

    private void btnKommentarerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKommentarerActionPerformed
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
    }//GEN-LAST:event_btnKommentarerActionPerformed

    private void jtKommenteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtKommenteraActionPerformed
         
        
        String nyKommentar = jtKommentar.getText().toString();
        
        if( jtKommentar.getText().isEmpty()||tblPoster.getSelectionModel().isSelectionEmpty())
        {
           
        JOptionPane.showInternalMessageDialog(rootPane, "Välj en post");
        
        }else
        {
         try
        {
         
         
       
         
         String anvandare = inloggadPerson;
         con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
         pst2 = con.prepareStatement("SELECT * FROM `user` WHERE `anamn`=?");
         pst2.setString(1,anvandare);
         rs =pst2.executeQuery();
         
         if (rs.next()){
         int senderID = rs.getInt("id");

       
           
        
        
         
         
         
         
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
        }   
         catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }   
            
            
        }
            
         
    }//GEN-LAST:event_jtKommenteraActionPerformed

    private void cbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKategoriActionPerformed
     public void visaInlägg(){
         String inlägg;
       inlägg = tblPoster.getValueAt(tblPoster.getSelectedRow(), 1).toString();
       JOptionPane.showMessageDialog(null, inlägg, "Inlägg", JOptionPane.PLAIN_MESSAGE);
     }
     
     public void HamtaInlagg() throws SQLException{
         try
     { 
       String valdKategori = cbKategori.getSelectedItem().toString();
       String sql = "Select * from `utbildning post` where kategori_fk = ?;";
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       pst2 = con.prepareStatement(sql);
       pst2.setString(1, valdKategori);
       rs =pst2.executeQuery();
       tblPoster.setModel(DbUtils.resultSetToTableModel(rs));
        tblPoster.getTableHeader();
        tblPoster.getColumnModel().getColumn(2).setHeaderValue("Användarnamn");
        tblPoster.getColumnModel().getColumn(4).setHeaderValue("Kategori");
        tblPoster.getColumnModel().getColumn(3).setHeaderValue("Dokument");
        tblPoster.getTableHeader().repaint();
    }
            
        
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }
 }
     private void TaBortVärde(){
            try
     {          
       String sqlr = "Delete from `utbildnig poster` where `ID`="+värde;
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       pst2 = con.prepareStatement(sqlr);
       pst2.executeUpdate(sqlr);
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
    
    private void fyllComboBox(){
    cbKategori.removeAllItems();
    cbKategori.addItem("Välj");
    try{
    con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
           String sql = "SELECT namn FROM `utbildning kategori`;";
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
            java.util.logging.Logger.getLogger(UtbildningsForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UtbildningsForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UtbildningsForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UtbildningsForumMedKommentar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn="";
                new UtbildningsForumMedKommentar(anvandarnamn).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKommentarer;
    private javax.swing.JButton btnSkapaInlägg;
    private javax.swing.JButton btnStartsida;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnVisaInlägg;
    private javax.swing.JButton btnVisaPost;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtKommentar;
    private javax.swing.JButton jtKommentera;
    private javax.swing.JLabel lblVäljKategori;
    private javax.swing.JTable tblPoster;
    // End of variables declaration//GEN-END:variables
}
