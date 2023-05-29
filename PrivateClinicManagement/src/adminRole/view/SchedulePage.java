/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Schedule;
import Model.Room;
import Model.Service;
import Model.Employee;
import adminRole.controller.SchedulePageController;
import  adminRole.controller.RoomController;
import  adminRole.controller.ServiceController;
import  adminRole.controller.EmployeeController;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class SchedulePage extends javax.swing.JPanel {

    /**
     * Creates new form SchedulePage
     */
    private SchedulePageController controller;
    private RoomController roomController;
    private ServiceController serviceController;
    private EmployeeController employeeController;    
    private List<Schedule> listOfSchedule;
    private DefaultTableModel dataOftable;
    
    public SchedulePage() {
        //init components
        initComponents();
        
        controller = new SchedulePageController(this);
        listOfSchedule = new ArrayList<>();

        //set properties components
        btnAdd.setBackground(Color.white);
        btnDelete.setBackground(Color.white);
        btnSearch.setBackground(Color.WHITE);
        btnUpdate.setBackground(Color.WHITE);
        btnRefresh.setBackground(Color.WHITE);
        
        dataOftable = (DefaultTableModel)this.tbSchedule.getModel();
        dataOftable.setColumnIdentifiers(new Object[]{"Schedule ID", "Date", "State", "Next Orinal Number", "Service", "Room", "Employee"});
        
        // load data
        setUpData();
        queryData("select * from schedule");
        displayData();
    }    
    
    @Override
    public String toString() {
        return "Schedule List"; 
    } 
    // Set up data combobox 
   private void setUpData(){
        List<Schedule> ccbSchedule = new ArrayList();
        List<Room> ccbRoom = new ArrayList();
        List<Service> ccbService = new ArrayList();
        List<Employee> ccbEmployee = new ArrayList();
        ccbSchedule.clear();
        ccbRoom.clear();
        ccbService.clear();
        ccbEmployee.clear();
        
        txtScheduleID.removeAllItems();
        txtRoom.removeAllItems();
        txtService.removeAllItems();
        txtDoctor.removeAllItems();

        roomController = new RoomController();
        serviceController = new ServiceController();
        employeeController = new EmployeeController();
        controller.queryData("select * from schedule",ccbSchedule);
        roomController.queryData("select * from room",ccbRoom);
        employeeController.queryData("select * from employee",ccbEmployee);
        serviceController.queryData("select * from service", ccbService);
        
        for(Schedule p: ccbSchedule){
            txtScheduleID.addItem(String.valueOf(p.getScheduleID()));
        }
       
        for(Room p: ccbRoom){
            txtRoom.addItem(String.valueOf(p.getRoomID()));
        }
              
        for(Service p: ccbService){
            txtService.addItem(String.valueOf(p.getServiceID()));
        }
                     
        for(Employee p: ccbEmployee){
            txtDoctor.addItem(String.valueOf(p.getEmployeeID()));
        }
   } 
   
    // Load các record của schedule vào list
    private void queryData(String sql){
        dataOftable.setNumRows(0);
        listOfSchedule.clear();
        controller.queryData(sql, this.listOfSchedule);
    }
    
    // 
    private void executeData(String sql){
        dataOftable.setNumRows(0);
        listOfSchedule.clear();
        controller.executeData(sql);
    }
    
    // Load từ list vào bảng
    private void displayData(){
//        queryData("select * from schedule");
        for (Schedule p : listOfSchedule){
            dataOftable.addRow(new Object[] {p.getScheduleID(), p.getScheduleDate(), p.getState(), p.getNextOrinalNumber(), 
                p.getServiceID(), p.getRoomID(), p.getEmployeeID()});
        }
    }

    // get date from dateChooser and convert format to String
    private String convertDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
        
    // Create "where" condition
    private String createWhere(){
        boolean check = false; // Check if the "where" condition statement has queried an attribute yet, then add "OR" for the following query
        
        // Check if textField is empty or not, then create query statement
        String scheduleID = !(txtScheduleID.getSelectedItem() == null) ? " SCHEDULE_ID = " + txtScheduleID.getSelectedItem() : "-1";
        String dateString = txtDate.getDate() != null ? "SCHEDULE_DATE = \'" + convertDate(txtDate.getDate()) + "\'" : "-1";
        String state = !(txtState.getSelectedItem() == null) ? " STATE = \'" + txtState.getSelectedItem() + "\'": "-1"; 
        String nextOrinalNumber = !(txtNextOrinalNumber.getSelectedItem() == null) ? "NEXT_ORDINAL_NUMBER = " + txtNextOrinalNumber.getSelectedItem() : "-1";
        String service = !(txtService.getSelectedItem() == null) ? " SERVICE_ID = " + txtService.getSelectedItem() : "-1";
        String room = !(txtRoom.getSelectedItem() == null) ? " ROOM_ID = " +  txtRoom.getSelectedItem() : "-1";
        String doctor = !(txtDoctor.getSelectedItem() == null) ? " EMPLOYEE_ID = " + txtDoctor.getSelectedItem() : "-1";
//        
        // Create sql statement
        String sqlWhere = "";
        String[] txtData = {scheduleID, dateString, state, nextOrinalNumber, service, room, doctor};
//        String[] txtData = {scheduleID};
        for(int i = 0; i < 7; i++){
            if(!txtData[i].equals("-1")){
                if(!check){
                    sqlWhere += txtData[i];
                    check = true;
                }
                else{
                    sqlWhere += " OR " + txtData[i];
                }
            }
        }
       
        return sqlWhere;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil2 = new org.jdatepicker.util.JDatePickerUtil();
        jDateComponentFactory1 = new org.jdatepicker.JDateComponentFactory();
        jDatePickerUtil3 = new org.jdatepicker.util.JDatePickerUtil();
        sqlDateModel1 = new org.jdatepicker.impl.SqlDateModel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSchedule = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        txtScheduleID = new javax.swing.JComboBox<>();
        txtDoctor = new javax.swing.JComboBox<>();
        txtRoom = new javax.swing.JComboBox<>();
        txtService = new javax.swing.JComboBox<>();
        txtState = new javax.swing.JComboBox<>();
        txtNextOrinalNumber = new javax.swing.JComboBox<>();

        tbSchedule.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbScheduleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSchedule);

        btnAdd.setText("Add");
        btnAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setText("Schedule ID:");

        jLabel2.setText("Date:");

        jLabel3.setText("Doctor:");

        jLabel4.setText("Room:");

        jLabel5.setText("Service:");

        txtDate.setMaxSelectableDate(new java.util.Date(253370743281000L));

        jLabel6.setText("State:");

        jLabel7.setText("Next Orinal Number:");

        btnRefresh.setText("Refresh");
        btnRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        txtScheduleID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDoctor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtService.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtState.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNextOrinalNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDoctor, 0, 150, Short.MAX_VALUE))
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtService, 0, 150, Short.MAX_VALUE)
                    .addComponent(txtState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextOrinalNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1578, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(txtScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNextOrinalNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)
                        .addComponent(btnDelete)
                        .addComponent(btnSearch)
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnRefresh)
                        .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(677, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Create next ID and nextOrinalNumber
        long autoGenerateNumber = (long)(this.listOfSchedule.size()+1);
        
        String dateString = convertDate(txtDate.getDate());

        // add data to database
        String sql = "insert into schedule values (" + 
                                String.valueOf(autoGenerateNumber) + ", \'" + 
                                dateString                                                +
                                "\', \'Available\'"                                         + ", " + 
                                String.valueOf(autoGenerateNumber)  + ", " + 
                                txtService.getSelectedItem()                                  + ", " + 
                                txtRoom.getSelectedItem()                                     + ", " + 
                                txtDoctor.getSelectedItem()                                    + 
                            ")";
        executeData(sql);
        displayData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
        if(confirmOption == JOptionPane.YES_OPTION){
            String sql = "delete from schedule where " +  createWhere();
            System.out.println(sql);
            executeData(sql);
            displayData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String sql = "update schedule "
                        + "set "
                                + "SCHEDULE_DATE = \'" + convertDate(txtDate.getDate()) + "\', "
                                + "STATE = \'" +txtState.getSelectedItem()+"\', "
                                + "SERVICE_ID = " + txtService.getSelectedItem() + ", "
                                + "ROOM_ID = " + txtRoom.getSelectedItem() + ", "
                                + "EMPLOYEE_ID =" + txtDoctor.getSelectedItem()
                        + "where "
                                + "SCHEDULE_ID = " + txtScheduleID.getSelectedItem();
        executeData(sql);
        queryData("select * from schedule");
        displayData();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // Create sql statement
        String sql = "select * from schedule where " +  createWhere();
        System.out.println(sql);
        queryData(sql);
        displayData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        queryData("select * from schedule");
        displayData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbScheduleMouseClicked
        // TODO add your handling code here:
        int n = tbSchedule.getSelectedRow();
//        txtDate.setDate(convertString(String.valueOf(tbSchedule.getValueAt(n, 1))));
        
        txtScheduleID.setSelectedItem(tbSchedule.getValueAt(n, 0));
        txtState.setSelectedItem(tbSchedule.getValueAt(n, 2));
        txtNextOrinalNumber.setSelectedItem(tbSchedule.getValueAt(n, 3));
        txtService.setSelectedItem(tbSchedule.getValueAt(n, 4));
        txtRoom.setSelectedItem(tbSchedule.getValueAt(n, 5));
        txtDoctor.setSelectedItem(tbSchedule.getValueAt(n, 6));
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateString = sdf.format(tbSchedule.getValueAt(n, 1));
            Date dateParse = sdf.parse(dateString);
            txtDate.setDate(dateParse);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_tbScheduleMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private org.jdatepicker.JDateComponentFactory jDateComponentFactory1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel1;
    private javax.swing.JTable tbSchedule;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JComboBox<String> txtDoctor;
    private javax.swing.JComboBox<String> txtNextOrinalNumber;
    private javax.swing.JComboBox<String> txtRoom;
    private javax.swing.JComboBox<String> txtScheduleID;
    private javax.swing.JComboBox<String> txtService;
    private javax.swing.JComboBox<String> txtState;
    // End of variables declaration//GEN-END:variables
}
