/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adminRole.view;

import Model.Appointment;
import Model.Patient;
import Model.Schedule;
import adminRole.controller.AddAppointmentFormController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author GIAHUY
 */
public class AddAppointmentForm extends javax.swing.JDialog {

    private List<Schedule> listOfSchedule = new ArrayList<>();
    private DefaultTableModel dataOfScheduleTable;
    private PatientTab parent2;
    private void queryData(String sql) {
        listOfSchedule.removeAll(listOfSchedule);
        try {
            new AddAppointmentFormController().queryData(sql, listOfSchedule);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayData() {
        scheduleIDField.setText("");
        doctorIDField.setText("");
        doctorNameField.setText("");
        dateField.setText("");
        roomField.setText("");
        serviceFeild.setText("");
        stateField.setText("");
        finalCostField.setText("");
        dataOfScheduleTable.setRowCount(0);
        for (Schedule schedule : listOfSchedule) {
            dataOfScheduleTable.addRow(new Object[]{schedule.getScheduleID(), schedule.getScheduleDate(), schedule.getEmployeeID(), schedule.getNextOrinalNumber(), schedule.getServiceName(), (schedule.getState() == 1 ? "Available" : "Closed"), schedule.getCost()});
        }
    }

    private void refreshData() {
        queryData("select sc.schedule_id, sc.schedule_date, sc.state, sc.next_ordinal_number, sc.service_id, sv.service_name, sc.room_id, sc.employee_id, e.full_name, sv.cost "
                + "from Schedule sc inner join employee e on sc.employee_id = e.employee_id "
                + "inner join service sv on sc.service_id = sv.service_id");
        displayData();
    }

    public AddAppointmentForm(java.awt.Frame parent, boolean modal, PatientTab parent2) {
        super(parent, modal);
        initComponents();
        this.parent2 = parent2;
        this.setLocationRelativeTo(null);
        patientIDField.setText(String.valueOf(parent2.getPatientId()));
        patientNameField.setText(parent2.getPatientName());
        patientIDField.setEditable(false);
        patientNameField.setEditable(false);
        scheduleIDField.setEditable(false);
        doctorIDField.setEditable(false);
        doctorNameField.setEditable(false);
        dateField.setEditable(false);
        roomField.setEditable(false);
        serviceFeild.setEditable(false);
        stateField.setEditable(false);
        finalCostField.setEditable(false);
        tableOfSchedule.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        });
        tableOfSchedule.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataOfScheduleTable = (DefaultTableModel) tableOfSchedule.getModel();
        dataOfScheduleTable.setColumnIdentifiers(new Object[]{"Schedule ID", "Date", "Doctor ID", "Next number", "Service", "State", "Cost"});
        // properties
        saveButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        searchButton.setBackground(Color.WHITE);
        refreshButton.setBackground(Color.WHITE);
        dateScheduleSearching.getCalendarButton().setBackground(Color.WHITE);
        // listener
        cancelButton.addActionListener(e -> {
            AddAppointmentForm.this.dispose();
            parent2.refreshData();
        });
        tableOfSchedule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tableOfSchedule.getSelectedRow();
                scheduleIDField.setText(String.valueOf(listOfSchedule.get(index).getScheduleID()));
                doctorIDField.setText(String.valueOf(listOfSchedule.get(index).getEmployeeID()));
                doctorNameField.setText(listOfSchedule.get(index).getDoctorName());
                dateField.setText(String.valueOf(listOfSchedule.get(index).getScheduleDate()));
                roomField.setText(String.valueOf(listOfSchedule.get(index).getRoomID()));
                serviceFeild.setText(listOfSchedule.get(index).getServiceName());
                stateField.setText(listOfSchedule.get(index).getState() == 1 ? "Available" : "Closed");
                finalCostField.setText(String.valueOf(calculateFinalCost(listOfSchedule.get(index).getCost(), listOfSchedule.get(index).getScheduleDate())));
            }

        });
        refreshButton.addActionListener(e -> refreshData());
        searchButton.addActionListener(e -> {
            Calendar calendar = dateScheduleSearching.getCalendar();
            if (calendar != null) {
                queryData("select sc.schedule_id, sc.schedule_date, sc.state, sc.next_ordinal_number, sc.service_id, sv.service_name, sc.room_id, sc.employee_id, e.full_name, sv.cost "
                        + "from Schedule sc inner join employee e on sc.employee_id = e.employee_id "
                        + "inner join service sv on sc.service_id = sv.service_id "
                        + "where schedule_date = DATE " + String.format("'%04d-%02d-%02d'", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)));
                displayData();
            } else {
                refreshData();
            }
        });
        saveButton.addActionListener(e -> {
            try {
                if (tableOfSchedule.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(parent2, "Please choose a schedule!", "", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Appointment appointment = new Appointment();
                appointment.setScheduleID((Integer) tableOfSchedule.getValueAt(tableOfSchedule.getSelectedRow(), 0));
                appointment.setPatientID(parent2.getPatientId());
                new AddAppointmentFormController().addAnAppointment(appointment);
                AddAppointmentForm.this.dispose();
                JOptionPane.showMessageDialog(parent2, "Add new appointment successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                parent2.refreshData();
            } catch (SQLException ex) {
                //ex.printStackTrace();
                try {
                    if (ex.getErrorCode() == 1403 && new AddAppointmentFormController().getAPatient(parent2.getPatientId()) == null) {
                        this.dispose();
                        parent2.refreshData();
                        JOptionPane.showMessageDialog(parent, "This patient was deleted!", "", JOptionPane.ERROR_MESSAGE);
                        
                    } else if (ex.getErrorCode() == 1403) {
                        JOptionPane.showMessageDialog(this, "Schedule information no longer exits", "", JOptionPane.ERROR_MESSAGE);
                        refreshData();
                        
                    } else if (ex.getErrorCode() == 20001) {
                        JOptionPane.showMessageDialog(this, "Please choose schedule having Available state", "", JOptionPane.ERROR_MESSAGE);
                        refreshData();
                    }
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                }

            }

        });
        
        //load bảng
        refreshData();
    }
    private Long calculateFinalCost(Integer cost, Date sche_date) {
        Long final_cost = (long)0;
        try {
            Patient patient = new AddAppointmentFormController().getAPatient(parent2.getPatientId());
            if (patient != null)
            {
                final_cost = ((Double)(patient.getInsuranceExpiration().getTimeInMillis() > sche_date.getTime()? cost*0.5 : cost)).longValue();
            }
            else
            {
                this.dispose();
                parent2.refreshData();
                JOptionPane.showMessageDialog(parent2, "This patient was deleted!", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
        }
        return final_cost;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        patientIDField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        patientNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        scheduleIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        roomField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        doctorIDField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        doctorNameField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        serviceFeild = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        stateField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOfSchedule = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        finalCostField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dateScheduleSearching = new com.toedter.calendar.JDateChooser();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Patient ID:");

        jLabel2.setText("Patient name:");

        jLabel3.setText("Schedule ID:");

        roomField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Room:");

        jLabel5.setText("Doctor ID:");

        jLabel6.setText("Doctor name:");

        jLabel7.setText("Date:");

        jLabel8.setText("Service:");

        jLabel9.setText("State:");

        tableOfSchedule.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableOfSchedule);

        saveButton.setText("Save");
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel10.setText("Final cost:");

        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(doctorIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patientIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scheduleIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(patientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(serviceFeild, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(doctorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(finalCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateScheduleSearching, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(patientIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(patientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(scheduleIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(doctorIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(doctorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(roomField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(serviceFeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(finalCostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateScheduleSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton)
                        .addComponent(refreshButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField dateField;
    private com.toedter.calendar.JDateChooser dateScheduleSearching;
    private javax.swing.JTextField doctorIDField;
    private javax.swing.JTextField doctorNameField;
    private javax.swing.JTextField finalCostField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField patientIDField;
    private javax.swing.JTextField patientNameField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField roomField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField scheduleIDField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField serviceFeild;
    private javax.swing.JTextField stateField;
    private javax.swing.JTable tableOfSchedule;
    // End of variables declaration//GEN-END:variables
}
