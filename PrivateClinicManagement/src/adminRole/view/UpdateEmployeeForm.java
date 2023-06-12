/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adminRole.view;

import Model.Employee;
import adminRole.controller.UpdateEmployeeFormController;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author GIAHUY
 */
public class UpdateEmployeeForm extends javax.swing.JDialog {
    private Employee employee;
    /**
     * Creates new form UpdateEmployeeForm
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
    public UpdateEmployeeForm(java.awt.Frame parent, boolean modal, Employee employee, EmployeePage parent2) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.employee = employee;
        roleField.setEditable(false);
        roleField.setBackground(Color.WHITE);
        saveButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        birthdateField.getCalendarButton().setBackground(Color.WHITE);
        startDayField.getCalendarButton().setBackground(Color.WHITE);
        employeeIDField.setEditable(false);
        employeeIDField.setBackground(Color.WHITE);
        //
        cancelButton.addActionListener(e -> {
            int c = JOptionPane.showConfirmDialog(this, "Continue editing?", "", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.NO_OPTION) {
                UpdateEmployeeForm.this.dispose();
                parent2.refreshData();
            }
        });
        saveButton.addActionListener(e -> {
            phoneNoti.setVisible(false);
            passNoti.setVisible(false);
            dateNoti.setVisible(false);
            salaryNoti.setVisible(false);
            nameNoti.setVisible(false);
            Employee updatedEmployee = new Employee(employee.getEmployeeID());
            
            
            boolean isFormatOk = true;
            if (!checkName(employeeNameField.getText())) {
                nameNoti.setVisible(true);
                isFormatOk = false;
            }
            if (!phoneCheck(phoneField.getText())) {
                phoneNoti.setVisible(true);
                isFormatOk = false;
            }
            if (passField.getText().equals("")) {
                passNoti.setVisible(true);
                isFormatOk = false;
            }
            
            Integer salaryPerDay = 0;
            try {
                salaryPerDay = Integer.valueOf(salaryPerDayField.getText());
                if (salaryPerDay < 0) {
                    salaryNoti.setVisible(true);
                    isFormatOk = false;
                }
            } catch (NumberFormatException ee) {
                salaryNoti.setVisible(true);
                isFormatOk = false;
            }
             
            if (isFormatOk) {
                try {
                    updatedEmployee.setName(employeeNameField.getText());
                    updatedEmployee.setAddress(addressField.getText());
                    updatedEmployee.setHometown(hometownField.getText());
                    updatedEmployee.setPhone(phoneField.getText());
                    updatedEmployee.setSalaryPerDay(salaryPerDay);
                    updatedEmployee.setRoleName(employee.getRoleName());
                    updatedEmployee.setRoleId(employee.getRoleId());
                    updatedEmployee.setBirthday(birthdateField.getCalendar());
                    updatedEmployee.setStartDay(startDayField.getCalendar());
                    new UpdateEmployeeFormController().updateEmployee(updatedEmployee);
                    JOptionPane.showMessageDialog(parent2, "Update successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                    UpdateEmployeeForm.this.dispose();
                } catch (SQLException ee) {
                    dateNoti.setVisible(true);
                    ee.printStackTrace();
                }
            } 
        });
        displayData();
    }
    private void displayData() {
        this.employeeIDField.setText(employee.getEmployeeID().toString());
        this.employeeNameField.setText(employee.getName());
        this.phoneField.setText(employee.getPhone());
        this.passField.setText(employee.getPassword());
        this.addressField.setText(employee.getAddress());
        this.hometownField.setText(employee.getHometown());
        this.roleField.setText(employee.getRoleName());
        this.salaryPerDayField.setText(employee.getSalaryPerDay().toString());
        Calendar c = Calendar.getInstance(); c.setTimeInMillis(employee.getBirthday().getTimeInMillis());
        this.birthdateField.setCalendar(c);
        Calendar c2 = Calendar.getInstance(); c2.setTimeInMillis(employee.getStartDay().getTimeInMillis());
        this.startDayField.setCalendar(c2);
        phoneNoti.setVisible(false);
        passNoti.setVisible(false);
        dateNoti.setVisible(false);
        salaryNoti.setVisible(false);
        nameNoti.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        employeeIDField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        employeeNameField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        passField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        birthdateField = new com.toedter.calendar.JDateChooser();
        startDayField = new com.toedter.calendar.JDateChooser();
        hometownField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        roleField = new javax.swing.JTextField();
        salaryPerDayField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        salaryNoti = new javax.swing.JLabel();
        phoneNoti = new javax.swing.JLabel();
        passNoti = new javax.swing.JLabel();
        dateNoti = new javax.swing.JLabel();
        nameNoti = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(760, 360));
        setMinimumSize(new java.awt.Dimension(760, 360));
        setPreferredSize(new java.awt.Dimension(760, 360));
        setResizable(false);
        getContentPane().setLayout(null);

        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        getContentPane().add(cancelButton);
        cancelButton.setBounds(571, 290, 71, 20);

        saveButton.setText("Save");
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        getContentPane().add(saveButton);
        saveButton.setBounds(660, 290, 69, 20);
        getContentPane().add(employeeIDField);
        employeeIDField.setBounds(92, 30, 123, 22);

        jLabel1.setText("Employee ID:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(6, 33, 80, 16);
        getContentPane().add(phoneField);
        phoneField.setBounds(92, 77, 169, 22);

        jLabel3.setText("Phone:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(6, 80, 68, 16);

        jLabel2.setText("Name:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(310, 40, 50, 16);
        getContentPane().add(employeeNameField);
        employeeNameField.setBounds(388, 37, 302, 22);

        jLabel7.setText("Birthday:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(310, 77, 46, 16);
        getContentPane().add(passField);
        passField.setBounds(92, 117, 169, 22);

        jLabel5.setText("Password:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(6, 120, 68, 16);

        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        getContentPane().add(addressField);
        addressField.setBounds(92, 157, 455, 22);

        jLabel4.setText("Role:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(6, 240, 68, 16);

        jLabel8.setText("Start date:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(310, 123, 65, 16);

        jLabel9.setText("Address:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(6, 160, 68, 16);
        getContentPane().add(birthdateField);
        birthdateField.setBounds(388, 77, 174, 22);
        getContentPane().add(startDayField);
        startDayField.setBounds(387, 117, 174, 22);

        hometownField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hometownFieldActionPerformed(evt);
            }
        });
        getContentPane().add(hometownField);
        hometownField.setBounds(92, 197, 455, 22);

        jLabel11.setText("Hometown:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(6, 200, 68, 16);
        getContentPane().add(roleField);
        roleField.setBounds(92, 237, 141, 22);
        getContentPane().add(salaryPerDayField);
        salaryPerDayField.setBounds(413, 237, 141, 22);

        jLabel12.setText("Salary per day:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(312, 240, 95, 16);

        salaryNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        salaryNoti.setForeground(new java.awt.Color(255, 0, 0));
        salaryNoti.setText("Invalid format");
        getContentPane().add(salaryNoti);
        salaryNoti.setBounds(560, 242, 100, 14);

        phoneNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        phoneNoti.setForeground(new java.awt.Color(255, 0, 0));
        phoneNoti.setText("Invalid format");
        getContentPane().add(phoneNoti);
        phoneNoti.setBounds(92, 100, 100, 14);

        passNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        passNoti.setForeground(new java.awt.Color(255, 0, 0));
        passNoti.setText("Password is empty");
        getContentPane().add(passNoti);
        passNoti.setBounds(92, 141, 100, 14);

        dateNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        dateNoti.setForeground(new java.awt.Color(255, 0, 0));
        dateNoti.setText("Birthday cannot be higher than start date");
        getContentPane().add(dateNoti);
        dateNoti.setBounds(387, 141, 253, 14);

        nameNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nameNoti.setForeground(new java.awt.Color(255, 0, 0));
        nameNoti.setText("Invalid format");
        getContentPane().add(nameNoti);
        nameNoti.setBounds(92, 58, 100, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed

    private void hometownFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hometownFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hometownFieldActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private com.toedter.calendar.JDateChooser birthdateField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dateNoti;
    private javax.swing.JTextField employeeIDField;
    private javax.swing.JTextField employeeNameField;
    private javax.swing.JTextField hometownField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel nameNoti;
    private javax.swing.JTextField passField;
    private javax.swing.JLabel passNoti;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneNoti;
    private javax.swing.JTextField roleField;
    private javax.swing.JLabel salaryNoti;
    private javax.swing.JTextField salaryPerDayField;
    private javax.swing.JButton saveButton;
    private com.toedter.calendar.JDateChooser startDayField;
    // End of variables declaration//GEN-END:variables
}
