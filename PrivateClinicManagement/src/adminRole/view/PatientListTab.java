/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Patient;
import adminRole.controller.PatientListTabController;
import java.awt.Color;
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
/**
 *
 * @author GIAHUY
 */
public class PatientListTab extends javax.swing.JPanel {

    /**
     * Creates new form PatientListTab
     */
    private final int NEWEST = 0;
    private final int OLDEST = 1;
    private final int ID_ASC = 2;
    private final int ID_DESC = 3;
    private int sortMode = ID_ASC;
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
        return c == null ? "----/--/--" : "" + String.format("%02d", c.get(Calendar.YEAR)) + "/" + String.format("%02d", c.get(Calendar.MONTH)) + "/"+ String.format("%02d", c.get(Calendar.DATE));

    }
    @Override
    public String toString() {
        return "Patient List"; 
    } 
    private void queryData(String sql) // load các record của patient vào list (Lưu ý thứ tự và các cột của câu query phải trùng khớp với lúc lấy data từ ResultSet)
    {
        listOfPatient.removeAll(listOfPatient);
        controller.queryData(sql, this.listOfPatient); 
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
    public void refreshData()
    {
        queryData("select patient_id, fullname, phone, birthday, registration_day, insurance_expiration, address, underlying_disease from patient");
        sortPatientList();
        displayData();
    }
    public void refreshData()
    {
        queryData("select patient_id, fullname, phone, birthday, registration_day, insurance_expiration, address, underlying_disease from patient");
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
        addButton.setBackground(Color.white);
        deleteButton.setBackground(Color.white);
        searchButton.setBackground(Color.WHITE);

        sortChooser.setBackground(Color.WHITE);
        dataOftable = (DefaultTableModel)this.tableOfPatient.getModel(); 
        dataOftable.setColumnIdentifiers(new Object[]{"Patient ID", "Full name", "Last name", "Phone", "Birthday", "Registration Date", "Insurance Expiration", "Adress", "Underlying Disease"});
        refreshButton.setBackground(Color.WHITE);
        // set action event
        addButton.addActionListener(e -> {
            AddPatientForm form = new AddPatientForm(null, true, this); 
            form.setVisible(true);
        });
        refreshButton.addActionListener(e -> {refreshData();});
        deleteButton.addActionListener(e -> {});
        sortChooser.addItem("Newest");
        sortChooser.addItem("Oldest");
        sortChooser.addItem("ID ASC");
        sortChooser.addItem("ID DESC");
        sortChooser.addItemListener(e -> {
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("Newest"))
            {
                sortMode = NEWEST;
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("Oldest"))
            {
                sortMode = OLDEST;
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("ID ASC"))
            {
                sortMode = ID_ASC;
            }
            if (((String)PatientListTab.this.sortChooser.getSelectedItem()).equals("ID ASC"))
            {
                sortMode = ID_DESC;
            }
            refreshData();

        });
        // load data
        refreshData();
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


        setMaximumSize(new java.awt.Dimension(1230, 759));
        setMinimumSize(new java.awt.Dimension(1230, 759));
        setPreferredSize(new java.awt.Dimension(1230, 759));

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

        addButton.setText("Add");
        addButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        searchTextField.setText("Patient ID");

        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });


        sortChooser.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1218, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        .addComponent(sortChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)

                        .addGap(18, 18, 18)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()

                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton)
                        .addComponent(refreshButton))
                    .addComponent(sortChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> sortChooser;
    private javax.swing.JTable tableOfPatient;
    // End of variables declaration//GEN-END:variables
}
