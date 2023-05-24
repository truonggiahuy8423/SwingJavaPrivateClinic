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
/**
 *
 * @author GIAHUY
 */
public class PatientListTab extends javax.swing.JPanel {

    /**
     * Creates new form PatientListTab
     */
    private PatientListTabController controller;
    private PatientPage parent;
    private List<Patient> listOfPatient;
    private DefaultTableModel dataOftable;
    @Override
    public String toString() {
        return "Patient List"; 
    } 
    private void queryData(String sql) // load các record của patient vào list (Lưu ý thứ tự và các cột của câu query phải trùng khớp với lúc lấy data từ ResultSet)
    {
        dataOftable.setNumRows(0);
        listOfPatient.removeAll(listOfPatient);
        controller.queryData(sql, this.listOfPatient);
            //Patient p = null;
        
    }
    private void displayData() // load từ list vào bảng
    {
        for (Patient p : this.listOfPatient)
            {
                dataOftable.addRow(new Object[] {p.getPatientId(), p.getFullname(), p.getFullname(), p.getPhone(),
                p.getBirthday(), p.getRegistrationDay(), p.getInsuranceExpiration(), p.getAddress()});
            }
    }
    public PatientListTab(PatientPage parent) {
        initComponents();
        //init components
        controller = new PatientListTabController(this);
        this.parent = parent;
        listOfPatient = new ArrayList<>();
        
        //set properties components
        addButton.setBackground(Color.white);
        deleteButton.setBackground(Color.white);
        searchButton.setBackground(Color.WHITE);
        sortButton.setBackground(Color.WHITE);
        dataOftable = (DefaultTableModel)this.tableOfPatient.getModel();
        dataOftable.setColumnIdentifiers(new Object[]{"Patient ID", "Full name", "Last name", "Phone", "Birthday", "Registration Date", "Insurance Expiration", "Adress"});
        
        // set action event
        // load data
        queryData("select patient_id, fullname, phone, birthday, registration_day, insurance_expiration, address, underlying_disease from patient");
        displayData();
    }    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOfPatient = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

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

        sortButton.setText("Sort");
        sortButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
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
                        .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(sortButton)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sortButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton sortButton;
    private javax.swing.JTable tableOfPatient;
    // End of variables declaration//GEN-END:variables
}
