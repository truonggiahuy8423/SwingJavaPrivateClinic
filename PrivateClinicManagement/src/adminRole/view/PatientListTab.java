/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Patient;
import adminRole.controller.PatientListTabController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Calendar;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author GIAHUY
 */
public class PatientListTab extends javax.swing.JPanel implements Tab{

    /**
     * Creates new form PatientListTab
     */
    private final int NEWEST = 0;
    private final int OLDEST = 1;
    private final int ID_ASC = 2;
    private final int ID_DESC = 3;
    private int sortMode = NEWEST;
    private PatientListTabController controller;
    private PatientPage parent;
    private List<Patient> listOfPatient;
    private DefaultTableModel dataOftable;

    private void sortPatientList()
    {
        switch (this.sortMode) {
            case ID_ASC:
                Collections.sort(listOfPatient, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1.getPatientId() > o2.getPatientId())
                    return 1;
                else if (o1.getPatientId() < o2.getPatientId())
                    return -1;
                else return 0;
            }
        });
                break;
            case ID_DESC:
                Collections.sort(listOfPatient, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1.getPatientId() < o2.getPatientId())
                    return 1;
                else if (o1.getPatientId() > o2.getPatientId())
                    return -1;
                else return 0;
            }
        });
                break;
            case NEWEST:
                Collections.sort(listOfPatient, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1.getRegistrationDay() == null && o2.getRegistrationDay() == null) return 0;
                if (o1.getRegistrationDay() == null && o2.getRegistrationDay() != null) return 1;
                if (o1.getRegistrationDay() != null && o2.getRegistrationDay() == null) return -1;
                if (o1.getRegistrationDay().getTimeInMillis() < o2.getRegistrationDay().getTimeInMillis())
                    return 1;
                else if (o1.getRegistrationDay().getTimeInMillis() > o2.getRegistrationDay().getTimeInMillis())
                    return -1;
                else return 0;
            }
        });
                break;
            case OLDEST:
                Collections.sort(listOfPatient, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1.getRegistrationDay() == null && o2.getRegistrationDay() == null) return 0;
                if (o1.getRegistrationDay() == null && o2.getRegistrationDay() != null) return 1;
                if (o1.getRegistrationDay() != null && o2.getRegistrationDay() == null) return -1;
                if (o1.getRegistrationDay().getTimeInMillis() > o2.getRegistrationDay().getTimeInMillis())
                    return 1;
                else if (o1.getRegistrationDay().getTimeInMillis() < o2.getRegistrationDay().getTimeInMillis())
                    return -1;
                else return 0;
            }
        });
                break;
        }
    }
    private String convert_calendar(Calendar c)
    {
        return c == null ? "----/--/--" : "" + String.format("%02d", c.get(Calendar.YEAR)) + "/" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "/"+ String.format("%02d", c.get(Calendar.DATE));

    }
    @Override
    public String toString() {
        return "Patient List"; 
    } 
    private void queryData(String sql) // load các record của patient vào list (Lưu ý thứ tự và các cột của câu query phải trùng khớp với lúc lấy data từ ResultSet)
    {
        listOfPatient.removeAll(listOfPatient);
        try {
            controller.queryData(sql, this.listOfPatient); 
        } catch (SQLException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
            //Patient p = null;
        
    }
    private void displayData() // load từ list vào bảng
    {
        dataOftable.setRowCount(0);
        
        for (Patient p : this.listOfPatient)
            {

                dataOftable.addRow(new Object[] {p.getPatientId() , p.getFullname(), p.getFullname(), p.getPhone(),
                    convert_calendar(p.getBirthday()), convert_calendar(p.getRegistrationDay()), convert_calendar(p.getInsuranceExpiration()), 
                    p.getAddress() == null ? "None" : p.getAddress(), p.getUnderlyingDisease() == null ? "None" : p.getUnderlyingDisease()});

            }
        
    }
    @Override
    public void refreshData()
    {
        queryData("select patient_id, full_name, phone, birthday, registration_day, insurance_expiration, address, underlying_disease from patient");
        sortPatientList();
        displayData();
    }
    
    public PatientListTab(PatientPage parent) {
        initComponents();
        //init components
        controller = new PatientListTabController(this);
        this.parent = parent;
        listOfPatient = new ArrayList<>();
        //set properties components
        tableOfPatient.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        } );
        sortChooser.addItem("Newest");
        sortChooser.addItem("Oldest");
        sortChooser.addItem("ID ASC");
        sortChooser.addItem("ID DESC");
        sortChooser.setSelectedIndex(0);
        addButton.setBackground(Color.white);
        deleteButton.setBackground(Color.white);
        searchButton.setBackground(Color.WHITE);
        sortChooser.setBackground(Color.WHITE);
        dataOftable = (DefaultTableModel)this.tableOfPatient.getModel(); 
        dataOftable.setColumnIdentifiers(new Object[]{"Patient ID", "Full name", "Last name", "Phone", "Birthday", "Registration Date", "Insurance Expiration", "Address", "Underlying Disease"});
        refreshButton.setBackground(Color.WHITE);
        // set action event
        addButton.addActionListener(e -> {
            AddPatientForm form = new AddPatientForm(null, true, this); 
            form.setVisible(true);
        });
        refreshButton.addActionListener(e -> {refreshData();});
        sortChooser.addItemListener(e -> {
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("Newest"))
            {
                sortMode = NEWEST; refreshData();
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("Oldest"))
            {
                sortMode = OLDEST; refreshData();
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("ID ASC"))
            {
                sortMode = ID_ASC; refreshData();
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("ID DESC"))
            {
                sortMode = ID_DESC; refreshData();
            }
            refreshData();

        });
        this.deleteButton.addActionListener(e -> {
            int[] selectedRow = this.tableOfPatient.getSelectedRows(); 
            if (selectedRow.length != 0)
            {
                Integer id = (Integer)(tableOfPatient.getValueAt(selectedRow[0], 0));
                if (JOptionPane.showConfirmDialog(this, "Delete patient " + String.format("%08d", id), "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                {
                // delete
                try 
                {
                    controller.deletePatient(id);
                    JOptionPane.showMessageDialog(this, "Successfully delete " + id, "", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException sqlE)
                {
                    JOptionPane.showMessageDialog(this, (sqlE.getErrorCode() == 2292 ? "Delete appointments of this patient first" : "Patient information no longer exists") , "", JOptionPane.INFORMATION_MESSAGE);
                }
                
                //tableOfPatient.get
                refreshData();}
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "Please choose a patient to be deleted!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        tableOfPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                {
                    Integer id = (Integer)tableOfPatient.getValueAt(tableOfPatient.getSelectedRow(), 0);
                    queryData("select * from Patient");
                    if (listOfPatient.indexOf(new Patient(id)) == -1)
                    {
                        JOptionPane.showMessageDialog(parent, "Patient no longer exists", "",  JOptionPane.INFORMATION_MESSAGE);
                        refreshData();
                    } else 
                    {
                        parent.addNewTab(new PatientTab(id, parent));
                        parent.getTabbedPane().setSelectedIndex(parent.getTabbedPane().getTabCount() - 1);
                    }
                }
            }
            
        });
        
        searchButton.addActionListener(e -> {
            
            if (!isNumber(searchTextField.getText())) 
            {
                JOptionPane.showMessageDialog(parent, "Patient id format is invalid!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String sql = "select patient_id, full_name, phone, birthday, registration_day, insurance_expiration, address, underlying_disease from patient "
                    +(searchTextField.getText().equals("") ? "" : ("where patient_id = " + searchTextField.getText()));
            //System.out.println(sql);
            queryData(sql);
            displayData();
        });
        // load data
        refreshData();
    }    
    private boolean isNumber(String s)
    {
        for (char c : s.toCharArray())
        {
            if (!(c >= 48 && c <= 57))
                return false;
        }
        return true;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOfPatient = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        sortChooser = new javax.swing.JComboBox<>();
        refreshButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1230, 718));
        setMinimumSize(new java.awt.Dimension(1230, 718));
        setPreferredSize(new java.awt.Dimension(1230, 718));
        setLayout(null);

        tableOfPatient.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableOfPatient);

        add(jScrollPane1);
        jScrollPane1.setBounds(6, 53, 1218, 660);

        addButton.setText("Add");
        addButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        add(addButton);
        addButton.setBounds(1086, 20, 60, 20);

        deleteButton.setText("Delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton);
        deleteButton.setBounds(1164, 20, 60, 20);

        searchTextField.setForeground(new java.awt.Color(153, 153, 153));
        searchTextField.setText("Patient ID");
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        add(searchTextField);
        searchTextField.setBounds(6, 19, 122, 22);

        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        add(searchButton);
        searchButton.setBounds(134, 20, 60, 20);

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton);
        refreshButton.setBounds(1008, 20, 60, 20);

        sortChooser.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        add(sortChooser);
        sortChooser.setBounds(862, 19, 128, 22);

        refreshButton1.setText("Refresh");
        refreshButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        refreshButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton1);
        refreshButton1.setBounds(0, 0, 0, 0);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> sortChooser;
    private javax.swing.JTable tableOfPatient;
    // End of variables declaration//GEN-END:variables
}
