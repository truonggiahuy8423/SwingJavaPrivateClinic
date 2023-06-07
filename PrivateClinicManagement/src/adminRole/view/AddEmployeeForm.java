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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JDialog;
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
            
            // check password format
            String spassword = jTextFieldPassword.getText(); c = spassword.equals("");
            if (c) {
                jLabelPasswordNoti.setText("Enter password");
                formatIsOk = false;
            }
            
            if (formatIsOk)
                jDialogcloseForm.setVisible(true);
        });
        
        //Edit Noti
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                jDialogcloseForm.setVisible(true);
            }           
        });
            
        jButtonNosave.addActionListener (e -> {
            jDialogcloseForm.dispose();
            this.dispose();
            
        });
        jButtonCancel.addActionListener (e -> {
            jDialogcloseForm.dispose();
        });
        jButtonSave.addActionListener (e -> {
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
            
            // check password format
            String spassword = jTextFieldPassword.getText(); c = spassword.equals("");
            if (c) {
                jLabelPasswordNoti.setText("Enter password");
                formatIsOk = false;
            }
            
            String saddress = jTextFieldPassword.getText();
            String shometown = jTextFieldHometown.getText();
                
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
                Employee employee = new Employee('0', sname, '3', sphone,
                        spassword, startDate, saddress, shometown);
                try {
                    new EmployeePageController(null).addEmployee(employee);
                    JOptionPane.showMessageDialog(null, "Add new employee successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                    parent2.refreshData();                          
                    this.dispose();
                    jDialogcloseForm.dispose();
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

        jDialogcloseForm = new javax.swing.JDialog();
        jLabelNoti = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonNosave = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
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
        jLabelPositionTitle = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jComboBoxPositionTitle = new javax.swing.JComboBox<>();
        jTextFieldPassword = new javax.swing.JTextField();
        jButtonConfirm = new javax.swing.JButton();
        jLabelNameNoti = new javax.swing.JLabel();
        jLabelPhoneNoti = new javax.swing.JLabel();
        jLabelPasswordNoti = new javax.swing.JLabel();

        jDialogcloseForm.setMinimumSize(new java.awt.Dimension(500, 217));
        jDialogcloseForm.setModal(true);

        jLabelNoti.setText("Do you want to save this new employee?");

        jButtonCancel.setText("Cancel");

        jButtonNosave.setText("Don't save");
        jButtonNosave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNosaveActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");

        javax.swing.GroupLayout jDialogcloseFormLayout = new javax.swing.GroupLayout(jDialogcloseForm.getContentPane());
        jDialogcloseForm.getContentPane().setLayout(jDialogcloseFormLayout);
        jDialogcloseFormLayout.setHorizontalGroup(
            jDialogcloseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogcloseFormLayout.createSequentialGroup()
                .addGroup(jDialogcloseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogcloseFormLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNosave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))
                    .addGroup(jDialogcloseFormLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabelNoti)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jDialogcloseFormLayout.setVerticalGroup(
            jDialogcloseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogcloseFormLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(jLabelNoti)
                .addGap(42, 42, 42)
                .addGroup(jDialogcloseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonNosave)
                    .addComponent(jButtonSave))
                .addGap(37, 37, 37))
        );

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

        jLabelPositionTitle.setText("Position title:");

        jLabelPassword.setText("Password:");

        jComboBoxPositionTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPasswordActionPerformed(evt);
            }
        });

        jButtonConfirm.setText("Confirm");
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        jLabelNameNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelNameNoti.setForeground(new java.awt.Color(255, 51, 51));

        jLabelPhoneNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelPhoneNoti.setForeground(new java.awt.Color(255, 51, 51));

        jLabelPasswordNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelPasswordNoti.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabelNameNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabelPhoneNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelHometown, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jTextFieldHometown, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelStartday, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabelStartdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxStartdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelStartdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxStartdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabelStartdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxStartdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPositionTitle)
                            .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBoxBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelBirthdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jComboBoxBirthdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabelBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBoxBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPasswordNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))))
                .addGap(32, 32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelName)))
                .addGap(3, 3, 3)
                .addComponent(jLabelNameNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelPhone))
                    .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabelPhoneNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelStartday)
                            .addComponent(jLabelStartdayDate)
                            .addComponent(jLabelStartdayMonth)
                            .addComponent(jLabelStartdayYear))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxBirthdayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBirthdayMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBirthdayYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBirthday)
                            .addComponent(jLabelBirthdayDate)
                            .addComponent(jLabelBirthdayMonth)
                            .addComponent(jLabelBirthdayYear))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPositionTitle)
                    .addComponent(jComboBoxPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPasswordNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConfirm))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldHometownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHometownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHometownActionPerformed

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    private void jButtonNosaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNosaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNosaveActionPerformed

    private void jTextFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPasswordActionPerformed

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
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JButton jButtonNosave;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxBirthdayDate;
    private javax.swing.JComboBox<String> jComboBoxBirthdayMonth;
    private javax.swing.JComboBox<String> jComboBoxBirthdayYear;
    private javax.swing.JComboBox<String> jComboBoxPositionTitle;
    private javax.swing.JComboBox<String> jComboBoxStartdayDate;
    private javax.swing.JComboBox<String> jComboBoxStartdayMonth;
    private javax.swing.JComboBox<String> jComboBoxStartdayYear;
    private javax.swing.JDialog jDialogcloseForm;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelBirthdayDate;
    private javax.swing.JLabel jLabelBirthdayMonth;
    private javax.swing.JLabel jLabelBirthdayYear;
    private javax.swing.JLabel jLabelHometown;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNameNoti;
    private javax.swing.JLabel jLabelNoti;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPasswordNoti;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelPhoneNoti;
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
