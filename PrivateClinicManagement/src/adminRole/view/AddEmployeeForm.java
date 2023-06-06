/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adminRole.view;

import Model.Employee;
import adminRole.controller.EmployeePageController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author sun sun
 */
public class AddEmployeeForm extends javax.swing.JDialog {

    /**
     * Creates new form Add
     */
    private boolean checkName(String s)
    {
        for (int i = 0;i<s.length(); i++)
        {
            if ((s.charAt(i) >= 48 && s.charAt(i) <= 57)) return false;
        }
        return true;
    }
    private boolean phoneCheck(String s)
    {
        if (s.length() != 10) return false;
        for (int i = 0;i<s.length(); i++)
        {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) return false;
        }
        return true;
    }
    
    public AddEmployeeForm(java.awt.Frame parent, boolean modal, EmployeePage parent2) {
        super(parent, modal);
        initComponents();
        
        //clear data of combobox
        jComboBoxStartdayDate.removeAllItems();
        jComboBoxStartdayMonth.removeAllItems();
        jComboBoxStartdayYear.removeAllItems();
        jComboBoxBirthdayDate.removeAllItems();
        jComboBoxBirthdayMonth.removeAllItems();
        jComboBoxBirthdayYear.removeAllItems();
        jComboBoxPositionTitle.removeAllItems();
        
        //add value to combobox
        for (int i = 1; i < 13; i++) {
            jComboBoxStartdayMonth.addItem(String.valueOf(i));
        }
        for (int i = 1; i < 32; i++) {
            jComboBoxStartdayDate.addItem(String.valueOf(i));
        }
        for (int i = 2003; i < 2032; i++) {
            jComboBoxStartdayYear.addItem(String.valueOf(i));
        }
        for (int i = 1; i < 32; i++) {
            jComboBoxBirthdayDate.addItem(String.valueOf(i));
        }
        for (int i = 1; i < 12; i++) {
            jComboBoxBirthdayMonth.addItem(String.valueOf(i));
        }
        for (int i = 1955; i < 2024; i++) {
            jComboBoxBirthdayYear.addItem(String.valueOf(i));
        }
                
        jComboBoxPositionTitle.addItem("Admin");
        jComboBoxPositionTitle.addItem("Doctor");
        jComboBoxPositionTitle.addItem("Receptionist");
        
        //Check date format
        jComboBoxStartdayDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = jComboBoxStartdayDate.getSelectedIndex();
                if (selectedItem == 30) {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 9 || i == 11)
                            continue;
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                    }
                }
                else if (selectedItem == 29) {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) {
                        if (i == 2)
                            continue;
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                    }
                }
                else {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) 
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                }
            }
        });
        jComboBoxBirthdayDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = jComboBoxBirthdayDate.getSelectedIndex();
                if (selectedItem == 30) {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 9 || i == 11)
                            continue;
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                    }
                }
                else if (selectedItem == 29) {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) {
                        if (i == 2)
                            continue;
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                    }
                }
                else {
                    jComboBoxStartdayMonth.removeAllItems();
                    for (int i = 1; i < 13; i++) 
                        jComboBoxStartdayMonth.addItem(String.valueOf(i));
                }
            }
        });
        
        jButtonConfirm.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);

        jTextFieldName.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {jLabelNameNoti.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {jLabelNameNoti.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    jLabelNameNoti.setText("");
                }
            });
        jTextFieldPhone.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {jLabelPhoneNoti.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {jLabelPhoneNoti.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    jLabelPhoneNoti.setText("");
                }
            });
        jButtonConfirm.addActionListener(e -> {
            
            jLabelNameNoti.setText("");
            jLabelPasswordNoti.setText("");
            jLabelPhoneNoti.setText("");
            boolean formatIsOk = true;
            // check name format
            String sname = jTextFieldName.getText(); boolean c = sname.equals("");
            if (c || !checkName(sname)) {
                this.jLabelNameNoti.setText(c ? "Enter name" : "Name format is invalid");
                formatIsOk = false;
            }
        
            // check phone format
            String sphone = jTextFieldPhone.getText(); c = sphone.equals("");
            if (c || !phoneCheck(sphone)) {
                jLabelPhoneNoti.setText(c ? "Enter phone number" : "Phone format is invalid");
                formatIsOk = false;
            }
            String saddress = jTextFieldPassword.getText();
            String shometown = jTextFieldHometown.getText();
            String spassword = jTextFieldPassword.getText();
            
            Calendar insBirthday = null;
            Integer day = Integer.valueOf((String) jComboBoxBirthdayDate.getSelectedItem());
            Integer mon = Integer.valueOf((String) jComboBoxBirthdayMonth.getSelectedItem());
            Integer year = Integer.valueOf((String) jComboBoxBirthdayYear.getSelectedItem());
            insBirthday = Calendar.getInstance();
            insBirthday.setLenient(false);
            insBirthday.set(year, mon, day);
            insBirthday.getTime();
            
            if (formatIsOk)
            {
                Calendar startDate = Calendar.getInstance(); startDate.setTimeInMillis(System.currentTimeMillis());
                Employee employee = new Employee(0, sname, 0, sphone,
                        spassword, insBirthday, saddress, shometown);
                try {
                    new EmployeePageController(null).addEmployee(employee);
                    JOptionPane.showMessageDialog(null, "Add new employee successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                    parent2.refreshData();
                    this.dispose();
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),"", JOptionPane.OK_OPTION);
                } catch (Exception ee) {ee.printStackTrace();}
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        jLabelPhone = new javax.swing.JLabel();
        jLabelAddress = new javax.swing.JLabel();
        jLabelHometown = new javax.swing.JLabel();
        jLabelStartday = new javax.swing.JLabel();
        jLabelBirthday = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldPhone = new javax.swing.JTextField();
        jTextFieldAddress = new javax.swing.JTextField();
        jTextFieldHometown = new javax.swing.JTextField();
        jLabelStartdayDate = new javax.swing.JLabel();
        jLabelBirthdayDate = new javax.swing.JLabel();
        jLabelStartdayMonth = new javax.swing.JLabel();
        jLabelBirthdayMonth = new javax.swing.JLabel();
        jLabelStartdayYear = new javax.swing.JLabel();
        jLabelBirthdayYear = new javax.swing.JLabel();
        jComboBoxStartdayDate = new javax.swing.JComboBox<>();
        jComboBoxBirthdayDate = new javax.swing.JComboBox<>();
        jComboBoxStartdayMonth = new javax.swing.JComboBox<>();
        jComboBoxBirthdayMonth = new javax.swing.JComboBox<>();
        jComboBoxStartdayYear = new javax.swing.JComboBox<>();
        jComboBoxBirthdayYear = new javax.swing.JComboBox<>();
        jLabelPortrait = new javax.swing.JLabel();
        jLabelPositionTitle = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jComboBoxPositionTitle = new javax.swing.JComboBox<>();
        jTextFieldPassword = new javax.swing.JTextField();
        jButtonConfirm = new javax.swing.JButton();
        jLabelNameNoti = new javax.swing.JLabel();
        jLabelPhoneNoti = new javax.swing.JLabel();
        jLabelPasswordNoti = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabelName.setText("Full Name:");

        jLabelPhone.setText("Phone:");

        jLabelAddress.setText("Address:");

        jLabelHometown.setText("Hometown:");

        jLabelStartday.setText("Start day:");

        jLabelBirthday.setText("Birthday:");

        jTextFieldHometown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHometownActionPerformed(evt);
            }
        });

        jLabelStartdayDate.setText("Date:");

        jLabelBirthdayDate.setText("Date:");

        jLabelStartdayMonth.setText("Month:");

        jLabelBirthdayMonth.setText("Month:");

        jLabelStartdayYear.setText("Year:");

        jLabelBirthdayYear.setText("Year:");

        jComboBoxStartdayDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxStartdayDate.setSelectedItem(1);

        jComboBoxBirthdayDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxStartdayMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxBirthdayMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxStartdayYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxBirthdayYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelPortrait.setText("Portrait:");

        jLabelPositionTitle.setText("Position title:");

        jLabelPassword.setText("Password:");

        jComboBoxPositionTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonConfirm.setText("Confirm");
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelNameNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(261, 261, 261)
                        .addComponent(jLabelPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPhoneNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelHometown)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldHometown, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelStartday, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabelStartdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxStartdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelStartdayMonth)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxStartdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabelStartdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxStartdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(jLabelPositionTitle)
                        .addGap(37, 37, 37)
                        .addComponent(jComboBoxPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabelBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelBirthdayMonth)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxBirthdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabelBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelPasswordNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(135, 135, 135))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonConfirm)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNameNoti)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelName)
                            .addComponent(jLabelPortrait))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelPhone))
                    .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPhoneNoti))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelAddress))
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelHometown))
                    .addComponent(jTextFieldHometown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxStartdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStartdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStartdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelStartday)
                            .addComponent(jLabelStartdayDate)
                            .addComponent(jLabelStartdayMonth)
                            .addComponent(jLabelStartdayYear)
                            .addComponent(jLabelPositionTitle))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBirthdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPasswordNoti)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBirthday)
                            .addComponent(jLabelBirthdayDate)
                            .addComponent(jLabelBirthdayMonth)
                            .addComponent(jLabelBirthdayYear)
                            .addComponent(jLabelPassword))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButtonConfirm)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldHometownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHometownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHometownActionPerformed

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EmployeePage e = new EmployeePage();
//                AddEmployeeForm dialog = new AddEmployeeForm(new javax.swing.JFrame(), true, e);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JComboBox<String> jComboBoxBirthdayDate;
    private javax.swing.JComboBox<String> jComboBoxBirthdayMonth;
    private javax.swing.JComboBox<String> jComboBoxBirthdayYear;
    private javax.swing.JComboBox<String> jComboBoxPositionTitle;
    private javax.swing.JComboBox<String> jComboBoxStartdayDate;
    private javax.swing.JComboBox<String> jComboBoxStartdayMonth;
    private javax.swing.JComboBox<String> jComboBoxStartdayYear;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelBirthdayDate;
    private javax.swing.JLabel jLabelBirthdayMonth;
    private javax.swing.JLabel jLabelBirthdayYear;
    private javax.swing.JLabel jLabelHometown;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNameNoti;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPasswordNoti;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelPhoneNoti;
    private javax.swing.JLabel jLabelPortrait;
    private javax.swing.JLabel jLabelPositionTitle;
    private javax.swing.JLabel jLabelStartday;
    private javax.swing.JLabel jLabelStartdayDate;
    private javax.swing.JLabel jLabelStartdayMonth;
    private javax.swing.JLabel jLabelStartdayYear;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldHometown;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPhone;
    // End of variables declaration//GEN-END:variables
}
