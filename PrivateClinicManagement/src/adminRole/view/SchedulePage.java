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
import java.text.ParseException;
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
        txtNextOrinalNumber.setEditable(false);
        dataOftable = (DefaultTableModel)this.tbSchedule.getModel();
        dataOftable.setColumnIdentifiers(new Object[]{"Schedule ID", "Date", "State", "Next Orinal Number", "Service", "Room", "Employee"});
        
        // load data
        setUpComboboxData();
        queryData("select * from schedule order by schedule_id asc");
        displayData();
    }    
    
    @Override
    public String toString() {
        return "Schedule List"; 
    } 
    // Set up data combobox 
   private void setUpComboboxData(){
        List<Schedule> dataSchedule = new ArrayList();
        List<Room> dataRoom = new ArrayList();
        List<Service> dataService = new ArrayList();
        List<Employee> dataEmployee = new ArrayList();
        List<Schedule> dataNextOrinalNumber = new ArrayList();
        String[] dataState = {"Available", "Unavailable"};
        
        // Clear data in List and combobox before load/reload
        dataSchedule.clear();
        dataNextOrinalNumber.clear();
        dataService.clear();
        dataRoom.clear();
        dataEmployee.clear();
        
        cbbScheduleID.removeAllItems();
        cbbState.removeAllItems();
        cbbService.removeAllItems();
        cbbRoom.removeAllItems();
        cbbDoctor.removeAllItems();
        
        
        // Load data
        roomController = new RoomController();
        serviceController = new ServiceController();
        employeeController = new EmployeeController();
        controller.queryData("select * from schedule order by schedule_id asc",dataSchedule);
        controller.queryData("select * from schedule order by next_ordinal_number asc",dataNextOrinalNumber);
        serviceController.queryData("select * from service", dataService);
        roomController.queryData("select * from room",dataRoom);
        employeeController.queryData("select * from employee",dataEmployee);
        
        // Add data into combobox
        cbbState.addItem(dataState[0]);
        cbbState.addItem(dataState[1]);
        
        for(Schedule p: dataSchedule){
            cbbScheduleID.addItem(String.valueOf(p.getScheduleID()));
        }
       
       for(Service p: dataService){
            cbbService.addItem(String.valueOf(p.getServiceID()));
        }
       
        for(Room p: dataRoom){
            cbbRoom.addItem(String.valueOf(p.getRoomID()));
        }
        
        for(Employee p: dataEmployee){
            cbbDoctor.addItem(String.valueOf(p.getEmployeeID()));
        }
        
        

   } 
   
    // Load các record của schedule vào list
    private void queryData(String sql){
        this.listOfSchedule.clear();
        controller.queryData(sql, this.listOfSchedule);
    }
    
    // Used for Add, Delete and Update features
    private void executeData(String sql){
        this.listOfSchedule.clear();
        controller.executeData(sql);
    }
    
    // Load từ list vào bảng
    private void displayData(){
        dataOftable.setNumRows(0);
//        queryData("select * from schedule order by schedule_id asc");

        for (Schedule p : listOfSchedule){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateString = sdf.format(p.getScheduleDate());
            dataOftable.addRow(new Object[] {p.getScheduleID(), dateString, p.getState(), p.getNextOrinalNumber(), 
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
        String scheduleID = !(cbbScheduleID.getSelectedItem() == null) ? " SCHEDULE_ID = " + cbbScheduleID.getSelectedItem() : "-1";
        String dateString = txtDate.getDate() != null ? "SCHEDULE_DATE = \'" + convertDate(txtDate.getDate()) + "\'" : "-1";
        String state = !(cbbState.getSelectedItem() == null) ? " STATE = \'" + cbbState.getSelectedItem() + "\'": "-1"; 
        String nextOrinalNumber = !(txtNextOrinalNumber.getText().length() <= 0) ? "NEXT_ORDINAL_NUMBER = " + txtNextOrinalNumber.getText() : "-1";
        String service = !(cbbService.getSelectedItem() == null) ? " SERVICE_ID = " + cbbService.getSelectedItem() : "-1";
        String room = !(cbbRoom.getSelectedItem() == null) ? " ROOM_ID = " +  cbbRoom.getSelectedItem() : "-1";
        String doctor = !(cbbDoctor.getSelectedItem() == null) ? " EMPLOYEE_ID = " + cbbDoctor.getSelectedItem() : "-1";
        
        // Create sql statement
        String sqlWhere = "";
        String[] txtData = {scheduleID, dateString, state, nextOrinalNumber, service, room, doctor};
        
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
        cbbScheduleID = new javax.swing.JComboBox<>();
        cbbDoctor = new javax.swing.JComboBox<>();
        cbbRoom = new javax.swing.JComboBox<>();
        cbbService = new javax.swing.JComboBox<>();
        cbbState = new javax.swing.JComboBox<>();
        txtNextOrinalNumber = new javax.swing.JTextField();

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

        cbbScheduleID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbDoctor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbRoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbService.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbState.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                    .addComponent(cbbScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbDoctor, 0, 150, Short.MAX_VALUE))
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbService, 0, 150, Short.MAX_VALUE)
                    .addComponent(cbbState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextOrinalNumber)))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(cbbScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNextOrinalNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
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
                        .addComponent(cbbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(679, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try{
            String dateString = convertDate(txtDate.getDate());

            // add data to database
            String sql = "insert into schedule values (" + 
                                    "schedule_id_sequence.nextval"              + ", \'" + 
                                    dateString                                                 +"\', \'" +
                                    cbbState.getSelectedItem()                      + "\', " + 
                                    "schedule_id_sequence.nextval"              + ", " + 
                                    cbbService.getSelectedItem()                   + ", " + 
                                    cbbRoom.getSelectedItem()                      + ", " + 
                                    cbbDoctor.getSelectedItem()                     + 
                                ")";
            executeData(sql);
            setUpComboboxData();
            queryData("select * from schedule order by schedule_id asc");
            displayData();
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
        if(confirmOption == JOptionPane.YES_OPTION){
            String sql = "delete from schedule where schedule_id = " +  cbbScheduleID.getSelectedItem();
            System.out.println(sql);
            executeData(sql);
            setUpComboboxData();
            displayData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String sql = "update schedule "
                        + "set "
                                + "SCHEDULE_DATE = \'" + convertDate(txtDate.getDate()) + "\', "
                                + "STATE = \'" +cbbState.getSelectedItem()+"\', "
                                + "SERVICE_ID = " + cbbService.getSelectedItem() + ", "
                                + "ROOM_ID = " + cbbRoom.getSelectedItem() + ", "
                                + "EMPLOYEE_ID =" + cbbDoctor.getSelectedItem()
                        + "where "
                                + "SCHEDULE_ID = " + cbbScheduleID.getSelectedItem();
        executeData(sql);
//        queryData("select * from schedule");
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
        queryData("select * from schedule order by schedule_id asc");
        setUpComboboxData();
        displayData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbScheduleMouseClicked
        // TODO add your handling code here:
        int n = tbSchedule.getSelectedRow();
//        txtDate.setDate(convertString(String.valueOf(tbSchedule.getValueAt(n, 1))));
        for(int i = 0; i < cbbScheduleID.getItemCount(); i++){
            if(cbbScheduleID.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 0).toString())){
                cbbScheduleID.setSelectedIndex(i);
            }
        }
        for(int i = 0; i < cbbState.getItemCount(); i++){
            if(cbbState.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 2).toString())){
                cbbState.setSelectedIndex(i);
            }
        }
        
        txtNextOrinalNumber.setText(String.valueOf((tbSchedule.getValueAt(n, 3))));

        for(int i = 0; i < cbbService.getItemCount(); i++){
            if(cbbService.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 4).toString())){
                cbbService.setSelectedIndex(i);
            }
        }
        for(int i = 0; i < cbbRoom.getItemCount(); i++){
            if(cbbRoom.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 5).toString())){
                cbbRoom.setSelectedIndex(i);
            }
        }
        for(int i = 0; i < cbbDoctor.getItemCount(); i++){
            if(cbbDoctor.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 6).toString())){
                cbbDoctor.setSelectedIndex(i);
            }
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Date dateParseTemp = sdf.parse((String)tbSchedule.getValueAt(n, 1));
            String dateString = sdf.format(dateParseTemp);
            Date dateParse = sdf.parse(dateString);
            txtDate.setDate(dateParse);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_tbScheduleMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbDoctor;
    private javax.swing.JComboBox<String> cbbRoom;
    private javax.swing.JComboBox<String> cbbScheduleID;
    private javax.swing.JComboBox<String> cbbService;
    private javax.swing.JComboBox<String> cbbState;
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
    private javax.swing.JTextField txtNextOrinalNumber;
    // End of variables declaration//GEN-END:variables
}
