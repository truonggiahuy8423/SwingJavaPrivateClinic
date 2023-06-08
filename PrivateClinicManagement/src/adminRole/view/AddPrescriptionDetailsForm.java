/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adminRole.view;

import Model.Medicine;
import Model.PrescriptionDetails;
import adminRole.controller.AddPrescriptionDetailsFormController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author GIAHUY
 */
public class AddPrescriptionDetailsForm extends javax.swing.JDialog {
    List<Medicine> listOfMedicine = new ArrayList<>();
    DefaultTableModel dataOfMedicineTable;
    /**
     * Creates new form AddPrescriptionDetailsForm
     */
    public AddPrescriptionDetailsForm(java.awt.Frame parent, boolean modal, ResultTab parent2) {
        super(parent, modal);
        initComponents();
        // properties
        this.setLocationRelativeTo(null);
        searchButton.setBackground(Color.WHITE);
        saveButton.setBackground(Color.WHITE);
        refreshButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        quantitySpinner.setModel(new SpinnerNumberModel(0, 0, 100000, 1));
        medicineIDField.setEditable(false);
        nameField.setEditable(false);
        unitField.setEditable(false);
        descriptionField.setEditable(false);
        medicineTable.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
        });
        dataOfMedicineTable = (DefaultTableModel)medicineTable.getModel();
        
        medicineTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medicineTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                   
                        if ((new AddPrescriptionDetailsFormController().getAResult(parent2.getResultID())) != null) {
                            medicineIDField.setText(medicineTable.getValueAt(medicineTable.getSelectedRow(), 0).toString());
                            nameField.setText(medicineTable.getValueAt(medicineTable.getSelectedRow(), 1).toString());
                            unitField.setText(medicineTable.getValueAt(medicineTable.getSelectedRow(), 3).toString());
                            descriptionField.setText(medicineTable.getValueAt(medicineTable.getSelectedRow(), 2).toString());
                        } else {
                            AddPrescriptionDetailsForm.this.dispose();
                            parent2.refreshData();
                            JOptionPane.showMessageDialog(parent2, "This result was deleted!", "", JOptionPane.ERROR_MESSAGE);
                        }
                    
                } catch (SQLException ee) {
                    ee.printStackTrace();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            
        });
        //(medicineID, name, decription, unitID, unitName)
        dataOfMedicineTable.setColumnIdentifiers(new Object[] {"Medicine ID", "Name", "Description", "Unit"});
        // listener
        cancelButton.addActionListener(e -> {
            AddPrescriptionDetailsForm.this.dispose();
            parent2.refreshData();
        });
        saveButton.addActionListener(e -> {
            int indexRow = medicineTable.getSelectedRow();
            PrescriptionDetails presc = new PrescriptionDetails();
            presc.setResult_id(parent2.getResultID());
            presc.setMedicine_id((Integer)medicineTable.getValueAt(indexRow, 0));
            presc.setQuantity((Integer)quantitySpinner.getValue());
            presc.setInstruction(instructionField.getText());
            try {
               new AddPrescriptionDetailsFormController().addAPresc(presc);
               AddPrescriptionDetailsForm.this.dispose();
               JOptionPane.showMessageDialog(parent2, "Add a new medicine into result succesfully", "", JOptionPane.INFORMATION_MESSAGE);
               parent2.refreshData();
            } catch (SQLException ee) {
                try {
                if (ee.getErrorCode() == 1) {
                    JOptionPane.showMessageDialog(parent2, "This medicine was already inserted", "", JOptionPane.ERROR_MESSAGE);
                } else if (ee.getErrorCode() == 2292 && new AddPrescriptionDetailsFormController().getAResult(parent2.getResultID()) == null) {
                    JOptionPane.showMessageDialog(parent2, "This result no longer exists", "", JOptionPane.ERROR_MESSAGE);
                    AddPrescriptionDetailsForm.this.dispose();
                    parent2.refreshData();
                } else if (ee.getErrorCode() == 2292) {
                    JOptionPane.showMessageDialog(parent2, "This medicine no longer exists", "", JOptionPane.ERROR_MESSAGE);
                    AddPrescriptionDetailsForm.this.refreshData();
                }} catch (SQLException eee) {
                    eee.printStackTrace();
                }
                // 2292
                // 2292 vs result null?
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
        refreshData();
    }
    private void queryData(String sql) {
        try {
            listOfMedicine.removeAll(listOfMedicine);
            new AddPrescriptionDetailsFormController().queryData(sql, listOfMedicine);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayData() {
        medicineIDField.setText(" ");
        nameField.setText(" ");
        unitField.setText(" ");
        descriptionField.setText(" ");
        dataOfMedicineTable.setRowCount(0);
        for (Medicine m : listOfMedicine) {
            dataOfMedicineTable.addRow(new Object[]{m.getMedicineID(), m.getName(), m.getDecription(), m.getUnitName()});
        }
    }
    private void refreshData() {
        String sql = "select m.medicine_id, m.medicine_name, m.description, u.unit_id, u.unit_name from medicine m inner join unit u on m.unit_id = u.unit_id";
        queryData(sql);
        displayData();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        medicineIDField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        unitField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        instructionField = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        medicineTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        medicineIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineIDFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Medicine ID:");

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Name:");

        unitField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitFieldActionPerformed(evt);
            }
        });

        jLabel7.setText("Unit:");

        jLabel3.setText("Description:");

        jLabel8.setText("Quantity:");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        jLabel4.setText("Instruction:");

        instructionField.setColumns(20);
        instructionField.setRows(5);
        jScrollPane2.setViewportView(instructionField);

        medicineTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(medicineTable);

        searchTextField.setForeground(new java.awt.Color(153, 153, 153));
        searchTextField.setText("Medicine ID");
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        refreshButton.setText("Refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(medicineIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(unitField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(147, 147, 147)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(medicineIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unitFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void medicineIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineIDFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JTextArea instructionField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField medicineIDField;
    private javax.swing.JTable medicineTable;
    private javax.swing.JTextField nameField;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTextField unitField;
    // End of variables declaration//GEN-END:variables
}
