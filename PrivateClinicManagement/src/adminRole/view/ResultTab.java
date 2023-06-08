/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Patient;
import Model.PrescriptionDetails;
import Model.Result;
import adminRole.controller.PatientTabController;
import adminRole.controller.ResultTabController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class ResultTab extends javax.swing.JPanel implements Tab{
    private Integer result_id;
    private Result result;
    List<PrescriptionDetails> listOfPresc = new ArrayList<>();
    DefaultTableModel dataOfPrescTable;
    @Override
    public String toString() {
        return "Result " + String.format("%08d", result_id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    /**
     * Creates new form ResultTab
     */
    public ResultTab(Integer result_id, PatientPage parent) {
        initComponents();
        this.prescTable.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        });
        dataOfPrescTable = (DefaultTableModel)prescTable.getModel();
        this.result_id = result_id;
        this.addButton.setBackground(Color.WHITE);
        this.deleteButton.setBackground(Color.WHITE);
        this.closeButton.setBackground(Color.WHITE);
        this.refreshButton.setBackground(Color.WHITE);
        this.modifyResultInformationButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.RED);
        cancelButton.setVisible(false);
        this.resultIDField.setEditable(false);
        this.apppointmentIDField.setEditable(false);
        this.patientIDField.setEditable(false);
        this.patientNameField.setEditable(false);
        this.doctorIDField.setEditable(false);
        this.doctorNameField.setEditable(false);
        this.reminderField.setEditable(false);
        this.underlyingDiseaseField.setEditable(false);
        this.diagnosisField.setEditable(false);
        this.prescTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.dataOfPrescTable.setColumnIdentifiers(new Object[] {"Medicine ID", "Result ID", "Name", "Description", "Quantity", "Unit", "Instruction"});
        //listener
        refreshButton.addActionListener(e -> refreshData());
        closeButton.addActionListener(e -> {
            parent.getTabbedPane().remove(parent.getTabbedPane().getSelectedIndex());
        });
        
        addButton.addActionListener(e -> {
            new AddPrescriptionDetailsForm(null, true, this).setVisible(true);
        });
        cancelButton.addActionListener(e -> {
            refreshData();
        });
        deleteButton.addActionListener(e -> {
            if (prescTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please choose a prescription detail to be deleted!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Delele this prescription?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) 
            { Integer result_id_ = (Integer) prescTable.getValueAt(prescTable.getSelectedRow(), 1);
            Integer medicine_id = (Integer) prescTable.getValueAt(prescTable.getSelectedRow(), 0);
            try {
                new ResultTabController().deletePresc(result_id, medicine_id);
                JOptionPane.showMessageDialog(this, "Delete successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                refreshData();
            } catch (SQLException ee) {
                JOptionPane.showMessageDialog(this, "This prescription no longer exists", "", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ee) {
                ee.printStackTrace();
            }}
        });
        modifyResultInformationButton.addActionListener(e -> {
            if (!stateOfModifyingButton) {
                setModifyingState(!stateOfModifyingButton); 
                return;
            }
            if (!checkIDFormat(apppointmentIDField.getText())) {
                appNoti.setText("Appointment ID is invalid");
                return;
            }
            if (JOptionPane.showConfirmDialog(parent, "Save result information?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
                Result result = new Result();   
                result.setResult_id(result_id);
                result.setDiagnosis(diagnosisField.getText());
                result.setReminder(reminderField.getText());
                result.setAppointment_id(Integer.valueOf(apppointmentIDField.getText()));
                try {
                    new ResultTabController().updateResult(result);
                    JOptionPane.showMessageDialog(parent, "Update result information successfully", "", JOptionPane.INFORMATION_MESSAGE);
                    refreshData();
                } catch (SQLException ex) {
                    appNoti.setText("Appointment ID doesn't exists");
                    ex.printStackTrace();
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        refreshData();
    }
    public Integer getResultID() {
        return result_id;
    }
    private boolean checkIDFormat(String id) {
        try {
            Integer.valueOf(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

        resultIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apppointmentIDField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        patientIDField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        patientNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        doctorIDField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        doctorNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnosisField = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reminderField = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        prescTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        underlyingDiseaseField = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        resultExistsNoti = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        modifyResultInformationButton = new javax.swing.JButton();
        appNoti = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1230, 718));
        setMinimumSize(new java.awt.Dimension(1230, 718));
        setLayout(null);

        resultIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(resultIDField);
        resultIDField.setBounds(140, 20, 180, 30);

        jLabel3.setText("Result ID:");
        add(jLabel3);
        jLabel3.setBounds(20, 30, 130, 16);

        apppointmentIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(apppointmentIDField);
        apppointmentIDField.setBounds(140, 80, 180, 30);

        jLabel4.setText("Appointment ID:");
        add(jLabel4);
        jLabel4.setBounds(20, 90, 130, 16);

        patientIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(patientIDField);
        patientIDField.setBounds(140, 140, 180, 30);

        jLabel5.setText("Patient ID:");
        add(jLabel5);
        jLabel5.setBounds(20, 150, 130, 16);

        patientNameField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(patientNameField);
        patientNameField.setBounds(140, 200, 310, 30);

        jLabel6.setText("Patient Name:");
        add(jLabel6);
        jLabel6.setBounds(20, 210, 130, 16);

        doctorIDField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(doctorIDField);
        doctorIDField.setBounds(590, 20, 180, 30);

        jLabel7.setText("Doctor ID:");
        add(jLabel7);
        jLabel7.setBounds(470, 30, 130, 16);

        doctorNameField.setMinimumSize(new java.awt.Dimension(0, 0));
        add(doctorNameField);
        doctorNameField.setBounds(590, 80, 300, 30);

        jLabel8.setText("Doctor:");
        add(jLabel8);
        jLabel8.setBounds(470, 90, 130, 16);

        jLabel10.setText("Underlying Disease:");
        add(jLabel10);
        jLabel10.setBounds(470, 150, 130, 16);

        diagnosisField.setColumns(20);
        diagnosisField.setRows(5);
        jScrollPane1.setViewportView(diagnosisField);

        add(jScrollPane1);
        jScrollPane1.setBounds(590, 260, 530, 80);

        jLabel11.setText("Diagnosis:");
        add(jLabel11);
        jLabel11.setBounds(470, 270, 130, 16);

        jLabel12.setText("Reminder:");
        add(jLabel12);
        jLabel12.setBounds(20, 270, 130, 16);

        reminderField.setColumns(20);
        reminderField.setRows(5);
        jScrollPane3.setViewportView(reminderField);

        add(jScrollPane3);
        jScrollPane3.setBounds(140, 260, 310, 80);

        prescTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(prescTable);

        add(jScrollPane4);
        jScrollPane4.setBounds(10, 400, 1210, 310);

        underlyingDiseaseField.setColumns(20);
        underlyingDiseaseField.setRows(5);
        jScrollPane5.setViewportView(underlyingDiseaseField);

        add(jScrollPane5);
        jScrollPane5.setBounds(590, 140, 530, 90);

        closeButton.setText("Close");
        closeButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        add(closeButton);
        closeButton.setBounds(1140, 20, 75, 20);

        addButton.setText("Add");
        addButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(addButton);
        addButton.setBounds(1050, 370, 75, 20);

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton);
        refreshButton.setBounds(960, 370, 75, 20);

        deleteButton.setText("Delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(deleteButton);
        deleteButton.setBounds(1140, 370, 75, 20);

        resultExistsNoti.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        resultExistsNoti.setForeground(new java.awt.Color(255, 51, 0));
        resultExistsNoti.setText("   ");
        add(resultExistsNoti);
        resultExistsNoti.setBounds(10, 380, 510, 14);

        cancelButton.setBackground(new java.awt.Color(255, 51, 0));
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        add(cancelButton);
        cancelButton.setBounds(780, 370, 75, 20);

        modifyResultInformationButton.setText("Modify");
        modifyResultInformationButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        modifyResultInformationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyResultInformationButtonActionPerformed(evt);
            }
        });
        add(modifyResultInformationButton);
        modifyResultInformationButton.setBounds(870, 370, 75, 20);

        appNoti.setForeground(new java.awt.Color(255, 0, 0));
        appNoti.setText("   ");
        add(appNoti);
        appNoti.setBounds(140, 110, 270, 16);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void modifyResultInformationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyResultInformationButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyResultInformationButtonActionPerformed
    private void queryData(String sql) {
        try {
            listOfPresc.removeAll(listOfPresc);
            result = new ResultTabController().queryData(result_id, sql, listOfPresc);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayData() {
        if (result == null) {
            this.resultExistsNoti.setText("This result no longer exists");
            this.resultIDField.setText("--");
            this.apppointmentIDField.setText("--");
            this.patientIDField.setText("--");
            this.patientNameField.setText("--");
            this.doctorIDField.setText("--");
            this.doctorNameField.setText("--");
            this.reminderField.setText("--");
            this.underlyingDiseaseField.setText("--");
            this.dataOfPrescTable.setRowCount(0);
            this.diagnosisField.setText("--");
        } else {
            this.resultExistsNoti.setText(" ");
            this.resultIDField.setText(result.getResult_id().toString());
            this.apppointmentIDField.setText(result.getAppointment_id().toString());
            this.patientIDField.setText(result.getPatient_id().toString());
            this.patientNameField.setText(result.getPatient_name());
            this.doctorIDField.setText(result.getDoctor_id().toString());
            this.doctorNameField.setText(result.getDoctor_name());
            this.reminderField.setText(result.getReminder());
            this.underlyingDiseaseField.setText(result.getUnderlying_disease());
            this.diagnosisField.setText(result.getDiagnosis());
            this.dataOfPrescTable.setRowCount(0);
            for (PrescriptionDetails pre : this.listOfPresc) {
                dataOfPrescTable.addRow(new Object[] {pre.getMedicine_id(), pre.getResult_id(), pre.getName(), pre.getDescription(), pre.getQuantity(), pre.getUnit(), pre.getInstruction()});
            }
            // list
        }
    }
    @Override
    public void refreshData() {
        setModifyingState(false);
        String sql = "select m.medicine_id, rd.result_id, m.medicine_name, m.description, rd.quantity, u.unit_name, rd.instruction "
                + " from result_detail rd inner join medicine m on rd.medicine_id = m.medicine_id "
                + "inner join unit u on m.unit_id = u.unit_id where rd.result_id = " + result_id;
        queryData(sql);
        displayData(); System.out.println("adminRole.view.ResultTab.refreshData()");
    }
    private boolean stateOfModifyingButton = false; 
    private void setModifyingState(boolean state) {
        stateOfModifyingButton = state;
        this.apppointmentIDField.setEditable(stateOfModifyingButton);
        this.diagnosisField.setEditable(stateOfModifyingButton);
        this.reminderField.setEditable(stateOfModifyingButton);
        cancelButton.setVisible(stateOfModifyingButton);
        if (stateOfModifyingButton)
        {
            modifyResultInformationButton.setBackground(Color.GREEN);
            modifyResultInformationButton.setForeground(Color.WHITE);
            modifyResultInformationButton.setText("Save");
        }
        else
        {
            modifyResultInformationButton.setBackground(Color.WHITE);
            modifyResultInformationButton.setForeground(Color.BLACK);
            modifyResultInformationButton.setText("Modify");
            appNoti.setText(" ");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel appNoti;
    private javax.swing.JTextField apppointmentIDField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea diagnosisField;
    private javax.swing.JTextField doctorIDField;
    private javax.swing.JTextField doctorNameField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton modifyResultInformationButton;
    private javax.swing.JTextField patientIDField;
    private javax.swing.JTextField patientNameField;
    private javax.swing.JTable prescTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextArea reminderField;
    private javax.swing.JLabel resultExistsNoti;
    private javax.swing.JTextField resultIDField;
    private javax.swing.JTextArea underlyingDiseaseField;
    // End of variables declaration//GEN-END:variables
}
