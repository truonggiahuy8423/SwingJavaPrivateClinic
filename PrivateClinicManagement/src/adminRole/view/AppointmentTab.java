/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Appointment;
import Model.Result;
import adminRole.controller.AppointmentTabController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class AppointmentTab extends javax.swing.JPanel implements Tab {
    private Long appointment_id;
    private Appointment appointment;
    private List<Result> listOfResults;
    private DefaultTableModel dataOfResultTable;
    /**
     * Creates new form AppointmentTab
     */
    
    @Override
    public String toString() {
        return "Appointment " + String.format("%08d", appointment_id);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public AppointmentTab(Long appointment_id, PatientPage parent) {
        initComponents();
        listOfResults = new ArrayList<>();
        this.appointment_id = appointment_id;
        // properties
        addButton.setBackground(Color.WHITE);
        deleteButton.setBackground(Color.WHITE);
        refreshButton.setBackground(Color.WHITE);
        closeButton.setBackground(Color.WHITE);        
        scheduleDateField.getCalendarButton().setBackground(Color.WHITE);
        resultTable.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        });
        dataOfResultTable = (DefaultTableModel) resultTable.getModel();
        resultTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataOfResultTable.setColumnIdentifiers(new Object[] {"Result ID", "Diagnosis", "Reminder"});
        
        appointmentIDField.setEditable(false);
        patientIDField.setEditable(false);        
        patientNameField.setEditable(false);
        finalCostField.setEditable(false);
        scheduleIDField.setEditable(false);
        scheduleDateField.setEnabled(false);
        doctorIDField.setEditable(false);
        doctorNameField.setEditable(false);
        ordinalNumberField.setEditable(false);
        serviceField.setEditable(false);
        roomField.setEditable(false);
        
        
        // listener
        closeButton.addActionListener(e -> {
            parent.getTabbedPane().remove(parent.getTabbedPane().getSelectedIndex());
        });
        refreshButton.addActionListener(e -> refreshData());
        addButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Add a new result?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
                try {
                    Result result = new Result();
                    result.setAppointment_id(appointment_id);
                    new AppointmentTabController().addResult(result);
                    JOptionPane.showMessageDialog(this, "Add a new result successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "This appointment was deleted!", "", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            refreshData();
        });
        deleteButton.addActionListener(e -> {
            if (resultTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please choose a result to be deleted!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Long result_id = (Long) resultTable.getValueAt(resultTable.getSelectedRow(), 0);
            if (JOptionPane.showConfirmDialog(this, "Delele result " + String.format("%08d", appointment_id) + "?", "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    new AppointmentTabController().deleteResult(result_id);
                    JOptionPane.showMessageDialog(this, "Delete result " + String.format("%08d", result_id) + "successfully!", "", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ee) {
                    JOptionPane.showMessageDialog(this, "Result " + String.format("%08d", appointment_id) + " no longer exists", "", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                
            }
            refreshData();
        });
        this.resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    parent.addNewTab(new ResultTab((Long)resultTable.getValueAt(resultTable.getSelectedRow(), 0), parent));
                }
            }
            
        });
        refreshData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        finalCostField = new javax.swing.JTextField();
        scheduleDateField = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        patientIDField = new javax.swing.JTextField();
        patientNameField = new javax.swing.JTextField();
        appointmentIDField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        doctorNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        scheduleIDField = new javax.swing.JTextField();
        ordinalNumberField = new javax.swing.JTextField();
        noti = new javax.swing.JLabel();
        doctorIDField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        serviceField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        roomField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1230, 718));
        setMinimumSize(new java.awt.Dimension(1230, 718));
        setLayout(null);

        finalCostField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(finalCostField);
        finalCostField.setBounds(140, 200, 120, 30);

        scheduleDateField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(scheduleDateField);
        scheduleDateField.setBounds(600, 80, 207, 30);

        jLabel1.setText("Patient ID:");
        add(jLabel1);
        jLabel1.setBounds(20, 90, 100, 16);

        jLabel2.setText("Full Name:");
        add(jLabel2);
        jLabel2.setBounds(20, 150, 130, 16);

        jLabel3.setText("Appointment ID:");
        add(jLabel3);
        jLabel3.setBounds(20, 30, 130, 16);

        jLabel4.setText("Final cost:");
        add(jLabel4);
        jLabel4.setBounds(20, 210, 90, 16);

        jLabel7.setText("Date:");
        add(jLabel7);
        jLabel7.setBounds(500, 90, 180, 16);

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(resultTable);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 300, 1210, 410);

        patientIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(patientIDField);
        patientIDField.setBounds(140, 80, 180, 30);

        patientNameField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(patientNameField);
        patientNameField.setBounds(140, 140, 270, 30);

        appointmentIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(appointmentIDField);
        appointmentIDField.setBounds(140, 20, 180, 30);

        deleteButton.setText("Delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(deleteButton);
        deleteButton.setBounds(1140, 270, 75, 20);

        addButton.setText("Add");
        addButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(addButton);
        addButton.setBounds(1050, 270, 75, 20);

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton);
        refreshButton.setBounds(960, 270, 75, 20);

        jLabel8.setText("Doctor:");
        add(jLabel8);
        jLabel8.setBounds(500, 150, 160, 16);

        doctorNameField.setMinimumSize(new java.awt.Dimension(0, 0));
        doctorNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorNameFieldActionPerformed(evt);
            }
        });
        add(doctorNameField);
        doctorNameField.setBounds(600, 140, 300, 30);

        jLabel10.setText("Schedule ID:");
        add(jLabel10);
        jLabel10.setBounds(500, 30, 130, 16);

        scheduleIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        scheduleIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleIDFieldActionPerformed(evt);
            }
        });
        add(scheduleIDField);
        scheduleIDField.setBounds(600, 20, 170, 30);

        ordinalNumberField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(ordinalNumberField);
        ordinalNumberField.setBounds(1070, 80, 120, 30);

        noti.setForeground(new java.awt.Color(255, 0, 0));
        noti.setText("    ");
        add(noti);
        noti.setBounds(10, 280, 580, 20);

        doctorIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(doctorIDField);
        doctorIDField.setBounds(1070, 140, 120, 30);

        jLabel6.setText("Doctor ID:");
        add(jLabel6);
        jLabel6.setBounds(970, 150, 100, 16);

        serviceField.setMinimumSize(new java.awt.Dimension(0, 0));
        serviceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceFieldActionPerformed(evt);
            }
        });
        add(serviceField);
        serviceField.setBounds(600, 200, 240, 30);

        jLabel9.setText("Service:");
        add(jLabel9);
        jLabel9.setBounds(500, 210, 160, 16);

        jLabel11.setText("Room:");
        add(jLabel11);
        jLabel11.setBounds(970, 210, 100, 16);

        roomField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(roomField);
        roomField.setBounds(1070, 200, 120, 30);

        jLabel12.setText("Ordinal Number:");
        add(jLabel12);
        jLabel12.setBounds(960, 90, 100, 16);

        closeButton.setText("Close");
        closeButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        add(closeButton);
        closeButton.setBounds(1140, 20, 75, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void doctorNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorNameFieldActionPerformed

    private void scheduleIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleIDFieldActionPerformed

    private void serviceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceFieldActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField appointmentIDField;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField doctorIDField;
    private javax.swing.JTextField doctorNameField;
    private javax.swing.JTextField finalCostField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel noti;
    private javax.swing.JTextField ordinalNumberField;
    private javax.swing.JTextField patientIDField;
    private javax.swing.JTextField patientNameField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextField roomField;
    private com.toedter.calendar.JDateChooser scheduleDateField;
    private javax.swing.JTextField scheduleIDField;
    private javax.swing.JTextField serviceField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshData() {
        String sql = "select r.result_id, r.appointment_id, r.reminder, r.diagnosis, a.schedule_id, a.patient_id, p.full_name, p.birthday, e.employee_id, e.full_name, p.underlying_disease "
                + "from result r inner join appointment a on r.appointment_id = a.appointment_id "
                + "inner join patient p on a.patient_id = p.patient_id "
                + "inner join schedule s on a.schedule_id = s.schedule_id "
                + "inner join employee e on s.employee_id = e.employee_id where a.appointment_id = " + appointment_id;
        
        queryData(sql);
        displayData();
    }
    
    private void queryData(String sql)
    {
        try {
            listOfResults.removeAll(listOfResults);
            appointment = new AppointmentTabController().queryData(appointment_id, sql, listOfResults);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void displayData() {
        dataOfResultTable.setRowCount(0);
        if (appointment == null) {
            appointmentIDField.setText("--");
            patientIDField.setText("--");
            patientNameField.setText("--");
            finalCostField.setText("--");
            scheduleIDField.setText("--");
            scheduleDateField.setCalendar(null);
            doctorIDField.setText("--");
            doctorNameField.setText("--");
            ordinalNumberField.setText("--");
            serviceField.setText("--");
            roomField.setText("--");
            noti.setText("This appointment no longer exists");
        } else {
            noti.setText("");
            appointmentIDField.setText(String.format("%d", appointment.getAppointmentID()));
            patientIDField.setText(String.format("%d", appointment.getPatientID()));
            patientNameField.setText(appointment.getPatientName());
            finalCostField.setText(appointment.getFinal_cost().toString());
            scheduleIDField.setText(String.format("%d", appointment.getScheduleID()));
            scheduleDateField.setCalendar(appointment.getDate());
            doctorIDField.setText(String.format("%d", appointment.getDoctorID()));
            doctorNameField.setText(appointment.getDoctorName());
            ordinalNumberField.setText(appointment.getOrdinalNumber().toString());
            serviceField.setText(appointment.getService());
            roomField.setText(appointment.getRoom().toString());
            for (Result r : listOfResults) {
                dataOfResultTable.addRow(new Object[] {r.getResult_id(), r.getDiagnosis(), r.getReminder()});
            }
        }
    }
    private String convert_calendar(Calendar c)
    {
        return c == null ? "----/--/--" : "" + String.format("%02d", c.get(Calendar.YEAR)) + "/" + String.format("%02d", c.get(Calendar.MONTH)) + "/"+ String.format("%02d", c.get(Calendar.DATE));

    }
    
}
