import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.Meeting;
import models.User;
import repository.MeetingRepository;
import repository.UserRepository;

/**
 *
 * @author jenniferhuang
 */
public class jennifer extends javax.swing.JFrame {
    
    private List<User> users;
    private UserRepository userRepository;
    private MeetingRepository meetingRepository;
   
    private List<User> usersToInvite;
   
    
    /**
     * Creates new form jennifer
     */
    public jennifer() {
        initComponents();
        userRepository = new UserRepository();
        meetingRepository = new MeetingRepository();
        usersToInvite = new ArrayList();
        fetchUsers();
    }
    
    public void fetchUsers() {
       users = userRepository.getUsers();
       addUsersToCombobox();
    }
    
    public void addUsersToCombobox() {
        usersCombobox.removeAllItems();
        users.forEach(user -> {
            usersCombobox.addItem(user);
        });
    }
    
    public void updateInviteList() {
        DefaultListModel<User> model = new DefaultListModel<>();
        usersToInvite.forEach(user -> {
            model.addElement(user);
        });
        invitedList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usersCombobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        meetingTitleTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        skapaMöteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        invitedList = new javax.swing.JList<>();
        inviteUserBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        removeUsersBtn = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Användare");

        jLabel2.setText("Titel:");

        skapaMöteBtn.setText("Skapa möte");
        skapaMöteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skapaMöteBtnActionPerformed(evt);
            }
        });

        invitedList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                invitedListFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                invitedListFocusLost(evt);
            }
        });
        invitedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                invitedListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(invitedList);

        inviteUserBtn.setText("->");
        inviteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inviteUserBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Inbjudna");

        removeUsersBtn.setText("Ta bort inbjudna");
        removeUsersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUsersBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usersCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(inviteUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(meetingTitleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(skapaMöteBtn)
                    .addComponent(removeUsersBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(removeUsersBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skapaMöteBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(meetingTitleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usersCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inviteUserBtn))
                .addGap(55, 55, 55)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inviteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inviteUserBtnActionPerformed
        User user = (User)usersCombobox.getSelectedItem();
        usersToInvite.add(user);
        usersCombobox.removeItem(user);
        updateInviteList();
    }//GEN-LAST:event_inviteUserBtnActionPerformed

    private void invitedListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_invitedListFocusGained
        removeUsersBtn.setEnabled(true);
    }//GEN-LAST:event_invitedListFocusGained

    private void invitedListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_invitedListFocusLost
        removeUsersBtn.setEnabled(false);
    }//GEN-LAST:event_invitedListFocusLost

  
    private void removeUsersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUsersBtnActionPerformed
        usersToInvite.clear();
        updateInviteList();
        fetchUsers();
    }//GEN-LAST:event_removeUsersBtnActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
    }//GEN-LAST:event_formMouseClicked

    private void invitedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_invitedListValueChanged

    }//GEN-LAST:event_invitedListValueChanged

    private void skapaMöteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skapaMöteBtnActionPerformed
             
        String title = meetingTitleTxt.getText();
            
        Meeting meeting = new Meeting(title, null, usersToInvite);
        meetingRepository.createMeeting(meeting);

        JOptionPane.showMessageDialog(null, "MÖTE SKAPAT");
   
    
      
    }//GEN-LAST:event_skapaMöteBtnActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new jennifer().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton inviteUserBtn;
    private javax.swing.JList<User> invitedList;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField meetingTitleTxt;
    private javax.swing.JButton removeUsersBtn;
    private javax.swing.JButton skapaMöteBtn;
    private javax.swing.JComboBox<User> usersCombobox;
    // End of variables declaration//GEN-END:variables
}
