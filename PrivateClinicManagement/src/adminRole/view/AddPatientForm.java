/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adminRole.view;


import Model.Patient;
import adminRole.controller.PatientListTabController;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author GIAHUY
 */
public class AddPatientForm extends javax.swing.JDialog {

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
    public AddPatientForm(java.awt.Frame parent, boolean modal, PatientListTab parent2) {
        super(parent, modal);
        initComponents();
        
        saveButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);

        name.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {nameNoti1.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {nameNoti1.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    nameNoti1.setText("");
                }
            });
            phone.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {phoneNoti1.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {phoneNoti1.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    phoneNoti1.setText("");
                }
            });
        saveButton.addActionListener(e -> {
            
            nameNoti1.setText("");
            birthdayNoti1.setText("");
            phoneNoti1.setText("");
            insuranceExpiration.setText("");
            boolean formatIsOk = true;
            // check name format
            String sname = name.getText(); boolean c = sname.equals("");
            if (c || !checkName(sname)) {
                this.nameNoti1.setText(c ? "Name field must not be empty" : "Name format is invalid");
                formatIsOk = false;
            }
        
            // check phone format
            String sphone = phone.getText(); c = sphone.equals("");
            if (c || !phoneCheck(sphone)) {
                phoneNoti1.setText(c ? "Phone must not be empty" : "Phone format is invalid");
                formatIsOk = false;
            }
            
            
            String saddress = address.getText();
            String sunderlyingdisease = underlyingDisease.getText();
            
            // check birthday format
            Calendar birthday = null;
            if (!(bday.getText().equals("") || bmon.getText().equals("") || byear.getText().equals("")))
            {
                try {
                    Integer day = Integer.valueOf(bday.getText());
                    Integer mon = Integer.valueOf(bmon.getText());
                    Integer year = Integer.valueOf(byear.getText());
                    birthday = Calendar.getInstance();
                    birthday.setLenient(false);
                    birthday.set(year, mon - 1, day);
                    birthday.getTime();
                } catch (NumberFormatException ex)
                {
                    birthdayNoti1.setText("Invalid value format"); 
                    formatIsOk = false;
                } catch (IllegalArgumentException exx)
                {
                    birthdayNoti1.setText("Invalid date"); 
                    formatIsOk = false;
                }  
            }
            //check insurance date format
            Calendar insExpiDate = null;
            if (!(iday.getText().equals("") || imon.getText().equals("") || iyear.getText().equals("")))
            {
                try {
                    Integer day = Integer.valueOf(iday.getText());
                    Integer mon = Integer.valueOf(imon.getText());
                    Integer year = Integer.valueOf(iyear.getText());
                    insExpiDate = Calendar.getInstance();
                    insExpiDate.setLenient(false);
                    insExpiDate.set(year, mon - 1, day);
                    insExpiDate.getTime();
                } catch (NumberFormatException ex)
                {
                    insuranceExpiration.setText("Invalid value format"); 
                    formatIsOk = false;
                } catch (IllegalArgumentException exx)
                {
                    insuranceExpiration.setText("Invalid date"); 
                    formatIsOk = false;
                }  
            }
            
            
            if (formatIsOk)
            {
                Calendar regisDate = Calendar.getInstance(); regisDate.setTimeInMillis(System.currentTimeMillis());
                Patient patient = new Patient(null, sname, sphone, birthday, regisDate,
                        insExpiDate, saddress, sunderlyingdisease);
                try {
                    new PatientListTabController(null).addPatient(patient);
                    JOptionPane.showMessageDialog(null, "Add new patient successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                    parent2.refreshData();
                    this.dispose();
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),"", JOptionPane.OK_OPTION);
                } catch (Exception ee) {ee.printStackTrace();}
                
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        underlyingDisease = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        iyear = new javax.swing.JTextField();
        imon = new javax.swing.JTextField();
        bmon = new javax.swing.JTextField();
        byear = new javax.swing.JTextField();
        iday = new javax.swing.JTextField();
        bday = new javax.swing.JTextField();

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        name = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        nameNoti1 = new javax.swing.JLabel();
        phoneNoti1 = new javax.swing.JLabel();
        birthdayNoti1 = new javax.swing.JLabel();
        insuranceExpiration = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(757, 588));
        setResizable(false);
        getContentPane().setLayout(null);

        underlyingDisease.setColumns(20);
        underlyingDisease.setRows(5);
        jScrollPane1.setViewportView(underlyingDisease);


        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(180, 370, 480, 108);

        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        getContentPane().add(cancelButton);
        cancelButton.setBounds(560, 510, 70, 20);

        saveButton.setText("Save");
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        getContentPane().add(saveButton);
        saveButton.setBounds(650, 510, 70, 20);


        iyear.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(iyear);
        iyear.setBounds(550, 300, 64, 30);

        imon.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(imon);
        imon.setBounds(370, 300, 64, 30);

        bmon.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(bmon);
        bmon.setBounds(370, 230, 64, 30);

        byear.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(byear);
        byear.setBounds(550, 230, 64, 30);

        iday.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(iday);
        iday.setBounds(180, 300, 64, 30);

        bday.setMaximumSize(new java.awt.Dimension(64, 22));
        getContentPane().add(bday);
        bday.setBounds(180, 230, 64, 30);


        jLabel5.setText("Insurance Expiration:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 310, 124, 16);

        jLabel6.setText("Underlying Disease");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 380, 113, 16);

        jLabel4.setText("Birthday:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 240, 80, 16);

        jLabel3.setText("Adress");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 170, 60, 16);

        jLabel2.setText("Phone:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 110, 110, 16);

        jLabel1.setText("Name:");
        getContentPane().add(jLabel1);

        jLabel1.setBounds(40, 50, 110, 16);
        getContentPane().add(name);
        name.setBounds(180, 40, 348, 30);
        getContentPane().add(phone);
        phone.setBounds(180, 100, 348, 30);
        getContentPane().add(address);
        address.setBounds(180, 160, 515, 30);

        nameNoti1.setBackground(new java.awt.Color(204, 204, 204));
        nameNoti1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nameNoti1.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(nameNoti1);
        nameNoti1.setBounds(180, 70, 240, 16);

        phoneNoti1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        phoneNoti1.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(phoneNoti1);
        phoneNoti1.setBounds(180, 130, 300, 16);

        birthdayNoti1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        birthdayNoti1.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(birthdayNoti1);
        birthdayNoti1.setBounds(180, 260, 300, 16);

        insuranceExpiration.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        insuranceExpiration.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(insuranceExpiration);
        insuranceExpiration.setBounds(180, 330, 300, 16);


        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JTextField bday;
    private javax.swing.JLabel birthdayNoti1;
    private javax.swing.JTextField bmon;
    private javax.swing.JTextField byear;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField iday;
    private javax.swing.JTextField imon;
    private javax.swing.JLabel insuranceExpiration;
    private javax.swing.JTextField iyear;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JTextField name;
    private javax.swing.JLabel nameNoti1;
    private javax.swing.JTextField phone;
    private javax.swing.JLabel phoneNoti1;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextArea underlyingDisease;

    // End of variables declaration//GEN-END:variables
}
