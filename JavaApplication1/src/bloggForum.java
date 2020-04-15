import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JSplitPane.TOP;
import static javax.swing.text.html.HTML.Tag.SELECT;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.Timer;

/**
 *
 * @author Axel Rosén
 */
public class bloggForum extends javax.swing.JFrame {
    
    

    /**
     * Creates new form bloggForum
     */
       public bloggForum() {
        initComponents();
       }
       
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
            
     public void HamtaInlagg() throws SQLException{
         
     try
     {          

       con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
       String sql = "Select * from inlagg";
       PreparedStatement pstmt = con.prepareStatement(sql);
       ResultSet rs = pstmt.executeQuery();

            while (rs.next()); 
            {

            String anvandare  = rs.getString("Användare");
            String inlaggText = rs.getString("Text");
            String tts = inlaggText.toString();
                if (txtForumRuta.contains(tts))
                {
                    rs.next();
                }
                else{
                    txtForumRuta.setText("    "+anvandare+"    "+inlaggText);
                }
            }
        con.close();
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
            Timer update = new Timer(1900, upd);
            update.start();
            Thread.sleep(5000);

            if (update.isRunning() && txtForumRuta.isVisible());
            HamtaInlagg();

            }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        txtForumRuta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTillbaka.setText("Tillbaka");

        txtForumRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtForumRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(291, Short.MAX_VALUE)
                .addComponent(btnTillbaka)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(txtForumRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(txtForumRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnTillbaka)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtForumRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtForumRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtForumRutaActionPerformed

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
            java.util.logging.Logger.getLogger(bloggForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bloggForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bloggForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bloggForum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bloggForum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JTextField txtForumRuta;
    // End of variables declaration//GEN-END:variables
}
