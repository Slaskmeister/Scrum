import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel Rosén
 */
public class SkapaInläggForskning extends javax.swing.JFrame {

   private String inloggadPerson;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private File file;
    private String path;
    private String filnamn;
   
    public SkapaInläggForskning(String anvandarnamn) {
        initComponents();
        inloggadPerson = anvandarnamn;
        lblOk.setVisible(false);
        lblFel.setVisible(false);
        fyllComboBox();
        txtaInläggForskning.setLineWrap(true);
    }
    private void fyllComboBox(){
    cbKategori.removeAllItems();
    cbKategori.addItem("Välj");
    try{
    con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
           String sql = "select `namn` from `forskning kategori`";
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
     
    private void publiceraInlagg() {                                         
       if (Validate.inteTomtArea(txtaInläggForskning)){
        
            try {
          FileInputStream fin = new FileInputStream(file);
          int len = (int) file.length();
          Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
          
          PreparedStatement ps = con.prepareStatement("INSERT INTO `dokument`(fil,Fil_namn) values (?,'"+filnamn+"');");
          ps.setBinaryStream(1, fin, len);
          int status = ps.executeUpdate();
          JOptionPane.showInternalMessageDialog(rootPane, "Dokumentet har lagts till");
          txtFilnamn.setText("");
          
         PreparedStatement pst1 = con.prepareStatement("select max(Fil_ID) from `dokument`");
         ResultSet rs = pst1.executeQuery();
         String dokID ="" ;
         while(rs.next())
            {
                dokID = rs.getString(1);
            }
        
            String valdKategori = cbKategori.getSelectedItem().toString();
            String nyttInlagg = txtaInläggForskning.getText();
            String sql = "INSERT INTO `forskning post`(`Text`, `användarnamn`, `dokument_fk`, `kategori_fk`) VALUES (?, ?, ?, ?)";
            con = DriverManager.getConnection("jdbc:mysql://mysqlse.fragnet.net:3306/111653_clientdb", "111653" ,"81374364");
            pst=con.prepareStatement(sql);
            pst.setString(1, nyttInlagg);
            pst.setString(2, inloggadPerson);
            pst.setString(3, dokID);
            pst.setString(4, valdKategori);
            pst.executeUpdate();
            lblOk.setText("Inlägget har publicerats ");
            lblOk.setVisible(true);}
            
            
        
    catch (Exception ex){
            System.out.println(ex.getMessage()); }}
    
    else {
    lblFel.setText("Det gick inte att göra inlägg");
    lblFel.setVisible(true);
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

        btnBifogaFil = new javax.swing.JButton();
        btnPubliceraInlägg = new javax.swing.JButton();
        cbKategori = new javax.swing.JComboBox<>();
        lblFel = new javax.swing.JLabel();
        lblKategori = new javax.swing.JLabel();
        btnTillbaka = new javax.swing.JButton();
        lblOk = new javax.swing.JLabel();
        lblBifoga = new javax.swing.JLabel();
        lblSkrivInlägg = new javax.swing.JLabel();
        txtFilnamn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaInläggForskning = new javax.swing.JTextArea();
        lblFilnamn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBifogaFil.setText("Välj fil");
        btnBifogaFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBifogaFilActionPerformed(evt);
            }
        });

        btnPubliceraInlägg.setText("Publicera inlägg");
        btnPubliceraInlägg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPubliceraInläggActionPerformed(evt);
            }
        });

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblFel.setForeground(new java.awt.Color(255, 51, 51));
        lblFel.setText("fel");

        lblKategori.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        lblKategori.setText("Välj kategori");

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        lblOk.setForeground(new java.awt.Color(0, 204, 0));
        lblOk.setText("ok");

        lblBifoga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBifoga.setText("Bifoga fil");

        lblSkrivInlägg.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblSkrivInlägg.setText("Skriv inlägg");

        txtaInläggForskning.setColumns(20);
        txtaInläggForskning.setRows(5);
        jScrollPane1.setViewportView(txtaInläggForskning);

        lblFilnamn.setText("Nytt filnamn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(72, 72, 72)
                                    .addComponent(lblSkrivInlägg))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(271, 271, 271))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnPubliceraInlägg)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTillbaka))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBifogaFil, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFilnamn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFilnamn)
                                        .addComponent(lblBifoga))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblOk)
                                .addComponent(lblFel, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lblKategori))))
                .addContainerGap(424, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblSkrivInlägg)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblBifoga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFilnamn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(txtFilnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBifogaFil, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPubliceraInlägg)
                    .addComponent(btnTillbaka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOk)
                .addGap(6, 6, 6)
                .addComponent(lblFel)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBifogaFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBifogaFilActionPerformed
     if(txtFilnamn.getText().isEmpty()){
        
        JOptionPane.showInternalMessageDialog(rootPane, "Ange vilket namn filen skall ha");
        
        }
        
        else{
        filnamn = txtFilnamn.getText();
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.showOpenDialog(this);
        
        file = fileChooser.getSelectedFile();
        path = file.getAbsolutePath();}
    }//GEN-LAST:event_btnBifogaFilActionPerformed

    private void btnPubliceraInläggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPubliceraInläggActionPerformed
        publiceraInlagg();
        JavaMail.Skicka();
        
    }//GEN-LAST:event_btnPubliceraInläggActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        String anvandarnamn = inloggadPerson;
        ForskningForumMedKommentar forum = new ForskningForumMedKommentar(anvandarnamn);
        forum.setVisible(true);
        SkapaInläggForskning.this.dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

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
            java.util.logging.Logger.getLogger(SkapaInläggForskning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SkapaInläggForskning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SkapaInläggForskning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SkapaInläggForskning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String anvandarnamn="";
                new SkapaInläggForskning(anvandarnamn).setVisible(true);
            }
        });
    

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBifogaFil;
    private javax.swing.JButton btnPubliceraInlägg;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBifoga;
    private javax.swing.JLabel lblFel;
    private javax.swing.JLabel lblFilnamn;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblOk;
    private javax.swing.JLabel lblSkrivInlägg;
    private javax.swing.JTextField txtFilnamn;
    private javax.swing.JTextArea txtaInläggForskning;
    // End of variables declaration//GEN-END:variables
}
