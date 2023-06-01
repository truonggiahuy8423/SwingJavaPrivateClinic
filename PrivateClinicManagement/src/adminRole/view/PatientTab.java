/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Appointment;
import Model.Patient;
import adminRole.controller.PatientTabController;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class PatientTab extends javax.swing.JPanel implements Tab{

    /**
     * Creates new form PatientTab
     */
    private Long patient_id;
    private Patient patient;
    private List<Appointment> listOfAppointment;
    private DefaultTableModel dataOfAppointmentTable;
    private boolean stateOfModifyingButton = false; 
    @Override
    public String toString() {
        return "Patient " + String.format("%08d", patient_id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public PatientTab(Long id, PatientPage parent) {
        initComponents();
        this.patient_id = id;
        this.listOfAppointment = new ArrayList<>();
        patientIDField.setEditable(false);
        ageField.setEditable(false);
        dataOfAppointmentTable = (DefaultTableModel)apointmentTable.getModel();
        dataOfAppointmentTable.setColumnIdentifiers(new Object[] {"Appointment ID", "Schedule ID", "Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Ordinal Number", "Date", "Room", "Service", "Final Cost"});
        // properties of components
        regisField.setDate(null);
        cancelButton.setBackground((new Color(255, 0, 0)));
        cancelButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.WHITE);
        deleteButton.setBackground(Color.WHITE);
        refreshButton.setBackground(Color.WHITE);
        modifyPatientInfomationButton.setBackground(Color.WHITE);
        // listener
        
        closeTabButton.addActionListener(e -> {
            parent.getTabbedPane().remove(parent.getTabbedPane().getSelectedIndex());
        });
        nameField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {nameNoti.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {nameNoti.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    nameNoti.setText("");
                }
            });
            phoneField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {phoneNoti.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {phoneNoti.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    phoneNoti.setText("");
                }
            });
        modifyPatientInfomationButton.addActionListener(e -> {
            if (!stateOfModifyingButton) {
                setModifyingState(!stateOfModifyingButton); 
                return;
            }
            
                // kiem tra format
                boolean isFormatOk = true;
                String sname = nameField.getText(); boolean c = sname.equals("");
                if (c || !checkName(sname)) {
                    this.nameNoti.setText(c ? "Name field must not be empty" : "Name format is invalid");
                    isFormatOk = false;
                }
        
            // check phone format
                String sphone = phoneField.getText(); c = sphone.equals("");
                if (c || !phoneCheck(sphone)) {
                    phoneNoti.setText(c ? "Phone must not be empty" : "Phone format is invalid");
                    isFormatOk = false;
                }
                if (isFormatOk && JOptionPane.showConfirmDialog(parent, "Save patient information?", "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                {
                    try {
                        Patient updatedPatient = new Patient(patient_id, nameField.getText(), phoneField.getText(),
                                birthdayField.getCalendar(),
                                regisField.getCalendar(),
                                insField.getCalendar(),
                                addressField.getText(),
                                underTextArea.getText());
                        new PatientTabController().updatePatient(updatedPatient);
                        JOptionPane.showMessageDialog(parent, "Update successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                        refreshData();
                    } catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(parent, ex, "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // update
                
                
            
        });
        cancelButton.addActionListener(e -> {
            setModifyingState(stateOfModifyingButton = !stateOfModifyingButton); 
            refreshData();
        });
        refreshButton.addActionListener(e -> refreshData());
        refreshData();
    }

    public void queryData(String sql)
    {
        try {
        listOfAppointment.removeAll(listOfAppointment);
        patient = (new PatientTabController()).queryData(patient_id, sql, listOfAppointment);
        } catch (SQLException e) { 
            System.out.println(e);
        } catch (Exception e) {}
    }
    public void displayData()
    {
        dataOfAppointmentTable.setRowCount(0);
        if (patient == null)
        {
            patientIDField.setText("--");
            nameField.setText("--");
            phoneField.setText("--");
            ageField.setText("--");
            birthdayField.setCalendar(null);
            regisField.setCalendar(null);
            insField.setCalendar(null);
        }
        else 
        {
            // load patient
            patientIDField.setText(String.valueOf(patient.getPatientId()));
            nameField.setText(patient.getFullname());
            phoneField.setText(patient.getPhone());
            Calendar cur = Calendar.getInstance();
            ageField.setText(patient.getBirthday() != null ? String.valueOf(cur.get(Calendar.YEAR)- patient.getBirthday().get(Calendar.YEAR)) : "--");
            birthdayField.setCalendar(patient.getBirthday());
            regisField.setCalendar(patient.getRegistrationDay());
            insField.setCalendar(patient.getInsuranceExpiration());
            // load appointment
            for (Appointment a : listOfAppointment)
            {
                dataOfAppointmentTable.addRow(new Object[] {a.getAppointmentID(), a.getScheduleID(), a.getPatientID(), a.getPatientName(), a.getDoctorID(), a.getDoctorName(), a.getOrdinalNumber(), convert_calendar(a.getDate()), a.getRoom(), a.getService(), a.getFinal_cost()});
            }
        }
    }
    private String convert_calendar(Calendar c)
    {
        return c == null ? "----/--/--" : "" + String.format("%02d", c.get(Calendar.YEAR)) + "/" + String.format("%02d", c.get(Calendar.MONTH)) + "/"+ String.format("%02d", c.get(Calendar.DATE));

    }
    @Override
    public void refreshData() {
        setModifyingState(false);
        
        // load
        String sql = "select a.appointment_id, s.schedule_id, p.patient_id, p.full_name, e.employee_id, e.full_name, a.ordinal_number, s.schedule_date, s.room_id, sv.service_name, a.fee "
                + "from Appointment a inner join Patient p on a.patient_id = p.patient_id "
                + "inner join Schedule s on a.schedule_id = s.schedule_id "
                + "inner join Employee e on s.employee_id = e.employee_id "
                + "inner join Service sv on s.service_id = sv.service_id "
                + "where a.patient_id = " + String.valueOf(patient_id);
        queryData(sql);
        if (patient == null)
            isPatientExistNoti.setText("Patient " + String.format("%08d", patient_id) + "was deleted!");
        else 
            isPatientExistNoti.setText("");
        // display
        displayData();
    }
    
    private void setModifyingState(boolean state)
    {
        stateOfModifyingButton = state;
        cancelButton.setVisible(stateOfModifyingButton);
        nameField.setEditable(stateOfModifyingButton);
        phoneField.setEditable(stateOfModifyingButton);
        birthdayField.setEnabled(stateOfModifyingButton);
        regisField.setEnabled(stateOfModifyingButton);
        insField.setEnabled(stateOfModifyingButton);
        addressField.setEditable(stateOfModifyingButton);
        underTextArea.setEditable(stateOfModifyingButton);
        if (!stateOfModifyingButton) 
        {   
            nameNoti.setText("");
            phoneNoti.setText("");
        }
        if (stateOfModifyingButton)
        {
            modifyPatientInfomationButton.setBackground(Color.GREEN);
            modifyPatientInfomationButton.setForeground(Color.WHITE);
            modifyPatientInfomationButton.setText("Save");
        }
        else
        {
            modifyPatientInfomationButton.setBackground(Color.WHITE);
            modifyPatientInfomationButton.setForeground(Color.BLACK);
            modifyPatientInfomationButton.setText("Modify");
        }
    }
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ageField = new javax.swing.JTextField();
        regisField = new com.toedter.calendar.JDateChooser();
        insField = new com.toedter.calendar.JDateChooser();
        birthdayField = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        apointmentTable = new javax.swing.JTable();
        closeTabButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        patientIDField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        modifyPatientInfomationButton = new javax.swing.JButton();
        isPatientExistNoti = new javax.swing.JLabel();
        phoneNoti = new javax.swing.JLabel();
        nameNoti = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        underTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1230, 718));
        setMinimumSize(new java.awt.Dimension(1230, 718));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1230, 718));
        setLayout(null);

        ageField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(ageField);
        ageField.setBounds(140, 200, 60, 30);

        regisField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(regisField);
        regisField.setBounds(790, 90, 207, 30);

        insField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(insField);
        insField.setBounds(790, 150, 207, 30);

        birthdayField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(birthdayField);
        birthdayField.setBounds(790, 30, 207, 30);

        jLabel1.setText("Patient ID:");
        add(jLabel1);
        jLabel1.setBounds(20, 30, 100, 16);

        jLabel2.setText("Full Name:");
        add(jLabel2);
        jLabel2.setBounds(20, 90, 130, 16);

        jLabel3.setText("Phone:");
        add(jLabel3);
        jLabel3.setBounds(20, 150, 130, 16);

        jLabel4.setText("Age:");
        add(jLabel4);
        jLabel4.setBounds(20, 210, 90, 16);

        jLabel5.setText("Birthday:");
        add(jLabel5);
        jLabel5.setBounds(650, 40, 130, 16);

        jLabel6.setText("Register Date:");
        add(jLabel6);
        jLabel6.setBounds(650, 100, 120, 16);

        jLabel7.setText("Insurance Exipiration:");
        add(jLabel7);
        jLabel7.setBounds(650, 160, 180, 16);

        apointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(apointmentTable);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 360, 1210, 350);

        closeTabButton.setBackground(new java.awt.Color(255, 51, 0));
        closeTabButton.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        closeTabButton.setForeground(new java.awt.Color(255, 255, 255));
        closeTabButton.setText("X");
        closeTabButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        closeTabButton.setBorderPainted(false);
        add(closeTabButton);
        closeTabButton.setBounds(1200, 0, 20, 20);

        nameField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(nameField);
        nameField.setBounds(140, 80, 270, 30);

        phoneField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(phoneField);
        phoneField.setBounds(140, 140, 270, 30);

        patientIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(patientIDField);
        patientIDField.setBounds(140, 20, 170, 30);

        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(cancelButton);
        cancelButton.setBounds(780, 330, 75, 20);

        deleteButton.setText("Delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(deleteButton);
        deleteButton.setBounds(1140, 330, 75, 20);

        addButton.setText("Add");
        addButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(addButton);
        addButton.setBounds(1050, 330, 75, 20);

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(refreshButton);
        refreshButton.setBounds(960, 330, 75, 20);

        modifyPatientInfomationButton.setText("Modify");
        modifyPatientInfomationButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(modifyPatientInfomationButton);
        modifyPatientInfomationButton.setBounds(870, 330, 75, 20);

        isPatientExistNoti.setForeground(new java.awt.Color(255, 51, 0));
        add(isPatientExistNoti);
        isPatientExistNoti.setBounds(10, 310, 420, 0);

        phoneNoti.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        phoneNoti.setForeground(new java.awt.Color(255, 0, 0));
        add(phoneNoti);
        phoneNoti.setBounds(140, 170, 340, 16);

        nameNoti.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        nameNoti.setForeground(new java.awt.Color(255, 0, 0));
        add(nameNoti);
        nameNoti.setBounds(140, 110, 340, 16);

        underTextArea.setColumns(20);
        underTextArea.setRows(5);
        jScrollPane2.setViewportView(underTextArea);

        add(jScrollPane2);
        jScrollPane2.setBounds(140, 260, 430, 70);

        jLabel8.setText("Address:");
        add(jLabel8);
        jLabel8.setBounds(650, 230, 160, 16);

        jLabel9.setText("Underlying disease:");
        add(jLabel9);
        jLabel9.setBounds(20, 270, 160, 16);

        addressField.setMinimumSize(new java.awt.Dimension(0, 0));
        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        add(addressField);
        addressField.setBounds(790, 220, 420, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField ageField;
    private javax.swing.JTable apointmentTable;
    private com.toedter.calendar.JDateChooser birthdayField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeTabButton;
    private javax.swing.JButton deleteButton;
    private com.toedter.calendar.JDateChooser insField;
    private javax.swing.JLabel isPatientExistNoti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton modifyPatientInfomationButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameNoti;
    private javax.swing.JTextField patientIDField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneNoti;
    private javax.swing.JButton refreshButton;
    private com.toedter.calendar.JDateChooser regisField;
    private javax.swing.JTextArea underTextArea;
    // End of variables declaration//GEN-END:variables

    
}
