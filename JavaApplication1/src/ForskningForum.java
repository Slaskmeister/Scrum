import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Axel Rosén
 */
public class ForskningForum extends javax.swing.JFrame {

   private String inloggadPerson;
    private PreparedStatement pst2;
    private ResultSet rs;
    private Connection con;
    private boolean admin;
    private String värde;
    private boolean harVärde;
    private String värde2;
   
    public ForskningForum(String anvandarnamn) {
        initComponents();
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
        //if (admin == true){
       // btnLäggTillKategori.setVisible(true);
       // }
       // else {
        //btnLäggTillKategori.setVisible(false);
       // }
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

        lblVäljKategori = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        btnVisaPost = new javax.swing.JButton();
        btnSkapaInlägg = new javax.swing.JButton();
        btnStartsida = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPoster = new javax.swing.JTable();
        btnTaBort = new javax.swing.JButton();
        btnVisaInlägg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblVäljKategori.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVäljKategori.setText("Välj kategori");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVisaPost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSkapaInlägg)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblVäljKategori)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVisaPost, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVisaInlägg)
                                .addGap(18, 18, 18)
                                .addComponent(btnTaBort)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(113, 113, 113))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btnStartsida)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblVäljKategori)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisaPost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaBort)
                    .addComponent(btnVisaInlägg))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSkapaInlägg)
                    .addComponent(btnStartsida))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        ForskningForum.this.dispose();
    }//GEN-LAST:event_btnSkapaInläggActionPerformed

    private void btnStartsidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartsidaActionPerformed
        String anvandarnamn = inloggadPerson;
        Startsida start = new Startsida(anvandarnamn);
        start.setVisible(true);
        ForskningForum.this.dispose();
    }//GEN-LAST:event_btnStartsidaActionPerformed

    private void tblPosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPosterMouseClicked
        värde=null;
        värde2=null;
        int kolumn = 0;
       
        int rad = tblPoster.getSelectedRow();                                   //Visar markrad rad i tabellen
        värde = tblPoster.getModel().getValueAt(rad, kolumn).toString();
        värde2 = tblPoster.getModel().getValueAt(rad, 2).toString();
    }//GEN-LAST:event_tblPosterMouseClicked

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
     public void visaInlägg(){
         String inlägg;
       inlägg = tblPoster.getValueAt(tblPoster.getSelectedRow(), 1).toString();
       JOptionPane.showMessageDialog(null, inlägg);
     }
     
     public void HamtaInlagg() throws SQLException{
         try
     { 
       String valdKategori = cbKategori.getSelectedItem().toString();
       String sql = "Select * from `Forskning poster` where `kategori_fk`=?";
       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       pst2 = con.prepareStatement(sql);
       pst2.setString(1, valdKategori);
       rs =pst2.executeQuery();
       tblPoster.setModel(DbUtils.resultSetToTableModel(rs));
    }
            
        
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Något gick fel, kontrollera uppkoppling till db");
            }
 }
     private void TaBortVärde(){
            try
     {          
       String sqlr = "Delete from `Forskning poster` where `ID`="+värde;
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
           String sql = "select namn from Forskning kategori";
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
            java.util.logging.Logger.getLogger(ForskningForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForskningForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForskningForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForskningForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn= ""; 
                new ForskningForum(anvandarnamn).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSkapaInlägg;
    private javax.swing.JButton btnStartsida;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnVisaInlägg;
    private javax.swing.JButton btnVisaPost;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblVäljKategori;
    private javax.swing.JTable tblPoster;
    // End of variables declaration//GEN-END:variables
}
