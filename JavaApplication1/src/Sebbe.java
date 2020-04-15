
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class Sebbe extends javax.swing.JFrame {
    Connection con = null;  
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * Creates new form Sebbe
     */
    public Sebbe() {
        initComponents();
        döljMeddelande();
        DisplayTable();
    }
    
    private void DisplayTable(){
    
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
           String sql = "select kategori from kategorier";
           PreparedStatement pstmt = con.prepareStatement(sql);
           ResultSet rs = pstmt.executeQuery();
           t1.setModel(DbUtils.resultSetToTableModel(rs));
           
       }
       
       catch (Exception ex){
       
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

        pnlSkapaKategori = new javax.swing.JPanel();
        lblSkapaKategori = new javax.swing.JLabel();
        txtKategorinamn = new javax.swing.JTextField();
        btnLaggTillKategori = new javax.swing.JButton();
        lblKategorinamn = new javax.swing.JLabel();
        lblBekraftelse = new javax.swing.JLabel();
        lblFel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSkapaKategori.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSkapaKategori.setText("Lägg till kategori");

        txtKategorinamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKategorinamnActionPerformed(evt);
            }
        });

        btnLaggTillKategori.setText("Lägg till kategori");
        btnLaggTillKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillKategoriActionPerformed(evt);
            }
        });

        lblKategorinamn.setText("Kategorinamn:");

        lblBekraftelse.setForeground(new java.awt.Color(0, 204, 0));
        lblBekraftelse.setText("Tom");

        lblFel.setForeground(new java.awt.Color(255, 0, 0));
        lblFel.setText("Tom");

        javax.swing.GroupLayout pnlSkapaKategoriLayout = new javax.swing.GroupLayout(pnlSkapaKategori);
        pnlSkapaKategori.setLayout(pnlSkapaKategoriLayout);
        pnlSkapaKategoriLayout.setHorizontalGroup(
            pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                .addGroup(pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                        .addGroup(pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(lblSkapaKategori))
                            .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLaggTillKategori)
                                    .addComponent(txtKategorinamn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblKategorinamn)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSkapaKategoriLayout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBekraftelse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlSkapaKategoriLayout.setVerticalGroup(
            pnlSkapaKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkapaKategoriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSkapaKategori)
                .addGap(23, 23, 23)
                .addComponent(lblKategorinamn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKategorinamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLaggTillKategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBekraftelse, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFel)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        t1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Kategorier"
            }
        ));
        jScrollPane2.setViewportView(t1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(pnlSkapaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSkapaKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaggTillKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillKategoriActionPerformed
    if (Validate.inteTomt(txtKategorinamn)){
        döljMeddelande();
        String nyKategori = "";
        try{
            nyKategori = txtKategorinamn.getText();
            String query = "INSERT INTO `kategorier`(`kategori`) VALUES (?)";
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
            pstmt=con.prepareStatement(query);
            pstmt.setString(1, txtKategorinamn.getText());
            pstmt.executeUpdate();
            lblBekraftelse.setText("Kategori " + nyKategori + " är nu registrerad");
            lblBekraftelse.setVisible(true);
            
        }
    catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Det gick inte att visa kategori"); }
    }
    else {
    lblFel.setText("Vänligen fyll i ovanstående fält");
    lblFel.setVisible(true);
    }
    }//GEN-LAST:event_btnLaggTillKategoriActionPerformed

    private void txtKategorinamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKategorinamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKategorinamnActionPerformed
    
    public void döljMeddelande(){
    lblBekraftelse.setVisible(false);
    lblFel.setVisible(false);
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
            java.util.logging.Logger.getLogger(Sebbe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sebbe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sebbe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sebbe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sebbe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTillKategori;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBekraftelse;
    private javax.swing.JLabel lblFel;
    private javax.swing.JLabel lblKategorinamn;
    private javax.swing.JLabel lblSkapaKategori;
    private javax.swing.JPanel pnlSkapaKategori;
    private javax.swing.JTable t1;
    private javax.swing.JTextField txtKategorinamn;
    // End of variables declaration//GEN-END:variables
}