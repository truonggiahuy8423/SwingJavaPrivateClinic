/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Employee;
import adminRole.controller.EmployeeListTabController;
import java.awt.Color;
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
     * Creates new form NewJDialog
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
    public AddEmployeeForm(java.awt.Frame parent, boolean modal, EmployeeListTab parent2) {
        super(parent, modal);
        initComponents();
        
//        saveButton.setBackground(Color.WHITE);
//        cancelButton.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);

//        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
//                @Override
//                public void insertUpdate(DocumentEvent e) {nameNoti1.setText("");
//                }
//
//                @Override
//                public void removeUpdate(DocumentEvent e) {nameNoti1.setText("");
//                }
//
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    nameNoti1.setText("");
//                }
//            });
//            phone.getDocument().addDocumentListener(new DocumentListener() {
//                @Override
//                public void insertUpdate(DocumentEvent e) {phoneNoti1.setText("");
//                }
//
//                @Override
//                public void removeUpdate(DocumentEvent e) {phoneNoti1.setText("");
//                }
//
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    phoneNoti1.setText("");
//                }
//            });
//        saveButton.addActionListener(e -> {
//            
//            nameNoti1.setText("");
//            birthdayNoti1.setText("");
//            phoneNoti1.setText("");
//            insuranceExpiration.setText("");
//            boolean formatIsOk = true;
//            // check name format
//            String sname = name.getText(); boolean c = sname.equals("");
//            if (c || !checkName(sname)) {
//                this.nameNoti1.setText(c ? "Name field must not be empty" : "Name format is invalid");
//                formatIsOk = false;
//            }
//        
//            // check phone format
//            String sphone = phone.getText(); c = sphone.equals("");
//            if (c || !phoneCheck(sphone)) {
//                phoneNoti1.setText(c ? "Phone must not be empty" : "Phone format is invalid");
//                formatIsOk = false;
//            }
//            
//            
//            String saddress = address.getText();
//            String sunderlyingdisease = underlyingDisease.getText();
//            
//            // check birthday format
//            Calendar birthday = null;
//            if (!(bday.getText().equals("") || bmon.getText().equals("") || byear.getText().equals("")))
//            {
//                try {
//                    Integer day = Integer.valueOf(bday.getText());
//                    Integer mon = Integer.valueOf(bmon.getText());
//                    Integer year = Integer.valueOf(byear.getText());
//                    birthday = Calendar.getInstance();
//                    birthday.setLenient(false);
//                    birthday.set(year, mon, day);
//                    birthday.getTime();
//                } catch (NumberFormatException ex)
//                {
//                    birthdayNoti1.setText("Invalid value format"); 
//                    formatIsOk = false;
//                } catch (IllegalArgumentException exx)
//                {
//                    birthdayNoti1.setText("Invalid date"); 
//                    formatIsOk = false;
//                }  
//            }
//            //check insurance date format
//            Calendar insExpiDate = null;
//            if (!(iday.getText().equals("") || imon.getText().equals("") || iyear.getText().equals("")))
//            {
//                try {
//                    Integer day = Integer.valueOf(iday.getText());
//                    Integer mon = Integer.valueOf(imon.getText());
//                    Integer year = Integer.valueOf(iyear.getText());
//                    insExpiDate = Calendar.getInstance();
//                    insExpiDate.setLenient(false);
//                    insExpiDate.set(year, mon, day);
//                    insExpiDate.getTime();
//                } catch (NumberFormatException ex)
//                {
//                    insuranceExpiration.setText("Invalid value format"); 
//                    formatIsOk = false;
//                } catch (IllegalArgumentException exx)
//                {
//                    insuranceExpiration.setText("Invalid date"); 
//                    formatIsOk = false;
//                }  
//            }
//            
//            
//            if (formatIsOk)
//            {
//                Calendar regisDate = Calendar.getInstance(); regisDate.setTimeInMillis(System.currentTimeMillis());
//                Employee employee = new Employee(null, sname, sphone, birthday, regisDate,
//                        insExpiDate, saddress, sunderlyingdisease);
//                try {
//                    new EmployeeListTabController(null).addEmployee(employee);
//                    JOptionPane.showMessageDialog(null, "Add new employee successfully!", "", JOptionPane.INFORMATION_MESSAGE);
//                    parent2.refreshData();
//                    this.dispose();
//                } catch (SQLException ex)
//                {
//                    JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),"", JOptionPane.OK_OPTION);
//                } catch (Exception ee) {ee.printStackTrace();}
//                
//            }
//        });

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        hometownLabel = new javax.swing.JLabel();
        startdayLabel = new javax.swing.JLabel();
        birthdayLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        hometownTextField = new javax.swing.JTextField();
        startdayDateLabel = new javax.swing.JLabel();
        birthdayDateLabel = new javax.swing.JLabel();
        startdayMonthLabel = new javax.swing.JLabel();
        birthdayMonthLabel = new javax.swing.JLabel();
        startdayYearLabel = new javax.swing.JLabel();
        birthdayYearLabel = new javax.swing.JLabel();
        startdayDateComboBox = new javax.swing.JComboBox<>();
        birthdayDateComboBox2 = new javax.swing.JComboBox<>();
        startdayMonthComboBox = new javax.swing.JComboBox<>();
        birthdayMonthComboBox = new javax.swing.JComboBox<>();
        startdayYearComboBox = new javax.swing.JComboBox<>();
        birthdayYearComboBox = new javax.swing.JComboBox<>();
        portraitLabel = new javax.swing.JLabel();
        positionTitleLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        positionTitleComboBox = new javax.swing.JComboBox<>();
        passwordTextField = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();

        nameLabel.setText("Full Name:");

        phoneLabel.setText("Phone:");

        addressLabel.setText("Address:");

        hometownLabel.setText("Hometown:");

        startdayLabel.setText("Start day:");

        birthdayLabel.setText("Birthday:");

        phoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextFieldActionPerformed(evt);
            }
        });

        startdayDateLabel.setText("Date:");

        birthdayDateLabel.setText("Date:");

        startdayMonthLabel.setText("Month:");

        birthdayMonthLabel.setText("Month:");

        startdayYearLabel.setText("Year:");

        birthdayYearLabel.setText("Year:");

        startdayDateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        startdayDateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startdayDateComboBoxActionPerformed(evt);
            }
        });

        birthdayDateComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        startdayMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        startdayMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startdayMonthComboBoxActionPerformed(evt);
            }
        });

        birthdayMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        startdayYearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        startdayYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startdayYearComboBoxActionPerformed(evt);
            }
        });

        birthdayYearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        portraitLabel.setText("Portrait:");

        positionTitleLabel.setText("Position title:");

        passwordLabel.setText("Password:");

        positionTitleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        confirmButton.setText("Confirm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hometownLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel)
                            .addComponent(nameLabel)
                            .addComponent(addressLabel))
                        .addGap(6, 6, 6))
                    .addComponent(startdayLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(birthdayLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdayDateLabel)
                            .addComponent(birthdayDateLabel))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdayDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthdayDateComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthdayMonthLabel)
                            .addComponent(startdayMonthLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdayMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthdayMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthdayYearLabel)
                            .addComponent(startdayYearLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdayYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthdayYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTextField)
                    .addComponent(hometownTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(portraitLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(positionTitleLabel)
                            .addComponent(passwordLabel))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(positionTitleComboBox, 0, 167, Short.MAX_VALUE)
                            .addComponent(passwordTextField))))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(portraitLabel)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hometownLabel)
                    .addComponent(hometownTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startdayLabel)
                    .addComponent(startdayDateLabel)
                    .addComponent(startdayMonthLabel)
                    .addComponent(startdayYearLabel)
                    .addComponent(startdayDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startdayMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startdayYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionTitleLabel)
                    .addComponent(positionTitleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdayLabel)
                    .addComponent(birthdayDateLabel)
                    .addComponent(birthdayMonthLabel)
                    .addComponent(birthdayYearLabel)
                    .addComponent(birthdayDateComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthdayMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthdayYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(confirmButton)
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void phoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextFieldActionPerformed

    private void startdayDateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startdayDateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startdayDateComboBoxActionPerformed

    private void startdayMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startdayMonthComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startdayMonthComboBoxActionPerformed

    private void startdayYearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startdayYearComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startdayYearComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JComboBox<String> birthdayDateComboBox2;
    private javax.swing.JLabel birthdayDateLabel;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JComboBox<String> birthdayMonthComboBox;
    private javax.swing.JLabel birthdayMonthLabel;
    private javax.swing.JComboBox<String> birthdayYearComboBox;
    private javax.swing.JLabel birthdayYearLabel;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel hometownLabel;
    private javax.swing.JTextField hometownTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JLabel portraitLabel;
    private javax.swing.JComboBox<String> positionTitleComboBox;
    private javax.swing.JLabel positionTitleLabel;
    private javax.swing.JComboBox<String> startdayDateComboBox;
    private javax.swing.JLabel startdayDateLabel;
    private javax.swing.JLabel startdayLabel;
    private javax.swing.JComboBox<String> startdayMonthComboBox;
    private javax.swing.JLabel startdayMonthLabel;
    private javax.swing.JComboBox<String> startdayYearComboBox;
    private javax.swing.JLabel startdayYearLabel;
    // End of variables declaration//GEN-END:variables
}
