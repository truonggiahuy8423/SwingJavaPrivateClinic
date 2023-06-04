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
    private Schedule currentSchedule;
    private DefaultTableModel dataOftable;
    
    public SchedulePage() {
        //init components
        initComponents();
        controller = new SchedulePageController(this);
        listOfSchedule = new ArrayList<>();
        
        errorDate.setText("");
        errorState.setText("");
        errorService.setText("");
        errorRoom.setText("");
        errorDoctor.setText("");

        //set properties components
        btnAdd.setBackground(Color.WHITE);
        btnDelete.setBackground(Color.WHITE);
        btnSearch.setBackground(Color.WHITE);
        btnUpdate.setBackground(Color.WHITE);
        btnRefresh.setBackground(Color.WHITE);
        cbbScheduleID.setBackground(Color.WHITE);
        cbbState.setBackground(Color.WHITE);
        cbbService.setBackground(Color.WHITE);
        cbbRoom.setBackground(Color.WHITE);
        cbbDoctor.setBackground(Color.WHITE);
        txtNextOrdinalNumber.setBackground(Color.WHITE);
        txtNextOrdinalNumber.setEditable(false);
        
        dataOftable = (DefaultTableModel)this.tbSchedule.getModel();
        dataOftable.setColumnIdentifiers(new Object[]{"Schedule ID", "Date", "State", "Next Ordinal Number", "Service", "Room", "Doctor"});
        
        // load data
        setUpComboboxData();
        queryData("select * from schedule order by schedule_id asc");
        displayData(this.listOfSchedule);
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
        List<Schedule> dataNextOrdinalNumber = new ArrayList();
        String[] dataState = {"Available", "Closed"};
        
        // Clear data in List and combobox before load/reload
        dataSchedule.clear();
        dataNextOrdinalNumber.clear();
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
        serviceController.queryData(dataService);
        roomController.queryData(dataRoom);
        employeeController.queryData("select * from employee",dataEmployee);
        
        // Add data into combobox
        cbbState.insertItemAt(null, 0);
        cbbState.addItem(dataState[0]);
        cbbState.addItem(dataState[1]);
        
        cbbScheduleID.insertItemAt(null, 0);
        for(Schedule p: dataSchedule){
            cbbScheduleID.addItem(String.valueOf(p.getScheduleID()));
        }
       
        cbbService.insertItemAt(null, 0);
       for(Service p: dataService){
            cbbService.addItem(String.valueOf(p.getServiceID()));
        }
       
       cbbRoom.insertItemAt(null, 0);
        for(Room p: dataRoom){
            cbbRoom.addItem(String.valueOf(p.getRoomID()));
        }
        
        cbbDoctor.insertItemAt(null, 0);
        for(Employee p: dataEmployee){
            cbbDoctor.addItem(String.valueOf(p.getEmployeeID()));
        }
        
        txtDate.setDate(null);
        txtNextOrdinalNumber.setText("");
   } 
   
    // Load các record của schedule vào list
    private void queryData(String sql){
        this.listOfSchedule.clear();
        controller.queryData(sql, this.listOfSchedule);
    }
    
    private void setBlankError(){
        errorDate.setText("");
        errorState.setText("");
        errorService.setText("");
        errorRoom.setText("");
        errorDoctor.setText("");
    }
    
    // Load từ list vào bảng
    private void displayData(List<Schedule> listOfSchedule){
        dataOftable.setNumRows(0);
//        queryData("select * from schedule order by schedule_id asc");

        for (Schedule p : listOfSchedule){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = sdf.format(p.getScheduleDate());
            String state = p.getState() == 1 ? "Available" : "Closed";
            dataOftable.addRow(new Object[] {p.getScheduleID(), dateString, state, p.getNextOrdinalNumber(), 
                p.getServiceID(), p.getRoomID(), p.getDoctorID()});
        }
    }

    private boolean checkNULL(){
        boolean isOk = true;
        setBlankError();
        
        if(txtDate.getDate() == null){
            errorDate.setText("Date must not be empty");
            isOk = false;
        }
        
        if(cbbState.getSelectedItem() == null){
            errorState.setText("State must not be empty");
            isOk = false;
        }
        
        if(cbbService.getSelectedItem() == null){
            errorService.setText("Service must not be empty");
            isOk = false;
        }
        
        if(cbbRoom.getSelectedItem() == null){
            errorRoom.setText("Room must not be empty");
            isOk = false;
        }
        
        if(cbbDoctor.getSelectedItem() == null){
            errorDoctor.setText("Doctor must not be empty");
            isOk = false;
        }
        
        return isOk;
    }
    
    // get date from dateChooser and convert format to String
    private String convertDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        txtNextOrdinalNumber = new javax.swing.JTextField();
        errorDoctor = new javax.swing.JLabel();
        errorService = new javax.swing.JLabel();
        errorState = new javax.swing.JLabel();
        errorRoom = new javax.swing.JLabel();
        errorDate = new javax.swing.JLabel();

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
        tbSchedule.setRowHeight(40);
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

        jLabel7.setText("Next Ordinal Number:");

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

        errorDoctor.setForeground(new java.awt.Color(255, 0, 0));
        errorDoctor.setText("error");
        errorDoctor.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorService.setForeground(new java.awt.Color(255, 0, 0));
        errorService.setText("error");
        errorService.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorState.setForeground(new java.awt.Color(255, 0, 0));
        errorState.setText("error");

        errorRoom.setForeground(new java.awt.Color(255, 0, 0));
        errorRoom.setText("error");

        errorDate.setForeground(new java.awt.Color(255, 0, 0));
        errorDate.setText("error");
        errorDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextOrdinalNumber)))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(errorRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbDoctor, 0, 150, Short.MAX_VALUE))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(errorState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbService, 0, 150, Short.MAX_VALUE)
                    .addComponent(cbbState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1613, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(cbbScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(errorDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(errorService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNextOrdinalNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cbbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(cbbState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnDelete)
                        .addComponent(btnUpdate)
                        .addComponent(btnSearch)
                        .addComponent(btnRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorState)
                    .addComponent(errorRoom))
                .addContainerGap(632, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(checkNULL() == true){
            Schedule addSchedule = new Schedule();
            Integer state = String.valueOf(cbbState.getSelectedItem()).equals("Available") ? 1 : 2;
            addSchedule.setScheduleID(null);
            addSchedule.setScheduleDate(txtDate.getDate());
            addSchedule.setNextOrdinalNumber(null);
            addSchedule.setState(state);
            addSchedule.setServiceID(Integer.valueOf(String.valueOf(cbbService.getSelectedItem())));
            addSchedule.setRoomID(Integer.valueOf(String.valueOf(cbbRoom.getSelectedItem())));
            addSchedule.setDoctorID(Integer.valueOf(String.valueOf(cbbDoctor.getSelectedItem())));
            controller.addData(addSchedule);
            setUpComboboxData();
            queryData("select * from schedule order by schedule_id asc");
            displayData(this.listOfSchedule);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
        if(confirmOption == JOptionPane.YES_OPTION){
            controller.deleteData(String.valueOf(cbbScheduleID.getSelectedItem()));

            setUpComboboxData();
            queryData("select * from schedule order by schedule_id asc");
            displayData(this.listOfSchedule);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Schedule updateSchedule = new Schedule();
        Integer state = String.valueOf(cbbState.getSelectedItem()).equals("Available") ? 1 : 2;
        updateSchedule.setScheduleID(Integer.valueOf(String.valueOf(cbbScheduleID.getSelectedItem())));
        updateSchedule.setScheduleDate(txtDate.getDate());
        updateSchedule.setState(state);
        updateSchedule.setServiceID(Integer.valueOf(String.valueOf(cbbService.getSelectedItem())));
        updateSchedule.setRoomID(Integer.valueOf(String.valueOf(cbbRoom.getSelectedItem())));
        updateSchedule.setDoctorID(Integer.valueOf(String.valueOf(cbbDoctor.getSelectedItem())));
        
        
        controller.updateData(updateSchedule, currentSchedule);
        queryData("select * from schedule order by schedule_id asc");
        displayData(this.listOfSchedule);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        List<Schedule> listSearchSchedule = new ArrayList();
        Schedule searchSchedule = new Schedule();
        String scheduleID = String.valueOf(cbbScheduleID.getSelectedItem());
        String service = String.valueOf(cbbService.getSelectedItem());
        String room =String.valueOf(cbbRoom.getSelectedItem());
        String doctor = String.valueOf(cbbDoctor.getSelectedItem());
        searchSchedule.setScheduleID(scheduleID.equals("null") ? null : Integer.valueOf(scheduleID));
        searchSchedule.setScheduleDate(txtDate.getDate());
        searchSchedule.setServiceID(service.equals("null") ? null : Integer.valueOf(service));
        searchSchedule.setRoomID(room.equals("null") ? null : Integer.valueOf(room));
        searchSchedule.setDoctorID(doctor.equals("null") ? null : Integer.valueOf(doctor));
        
        controller.searchData(searchSchedule, listSearchSchedule);
        displayData(listSearchSchedule);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        setBlankError();
        queryData("select * from schedule order by schedule_id asc");
        setUpComboboxData();
        displayData(this.listOfSchedule);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbScheduleMouseClicked
        int n = tbSchedule.getSelectedRow();
        // 
        for(int i = 1; i < cbbScheduleID.getItemCount(); i++){
            if(cbbScheduleID.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 0).toString())){
                cbbScheduleID.setSelectedIndex(i);
            }
        }
        
        for(int i = 1; i < cbbState.getItemCount(); i++){
            if(cbbState.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 2).toString())){
                cbbState.setSelectedIndex(i);
            }
        }
        
        txtNextOrdinalNumber.setText(String.valueOf((tbSchedule.getValueAt(n, 3))));

        for(int i = 1; i < cbbService.getItemCount(); i++){
            if(cbbService.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 4).toString())){
                cbbService.setSelectedIndex(i);
            }
        }
        for(int i = 1; i < cbbRoom.getItemCount(); i++){
            if(cbbRoom.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 5).toString())){
                cbbRoom.setSelectedIndex(i);
            }
        }
        for(int i = 1; i < cbbDoctor.getItemCount(); i++){
            if(cbbDoctor.getItemAt(i).equalsIgnoreCase(tbSchedule.getValueAt(n, 6).toString())){
                cbbDoctor.setSelectedIndex(i);
            }
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dateParseTemp = sdf.parse((String)tbSchedule.getValueAt(n, 1));
            String dateString = sdf.format(dateParseTemp);
            Date dateParse = sdf.parse(dateString);
            txtDate.setDate(dateParse);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        currentSchedule = new Schedule();
        Integer state = String.valueOf(cbbState.getSelectedItem()).equals("Available") ? 1 : 2;
        currentSchedule.setScheduleID(Integer.valueOf(String.valueOf(cbbScheduleID.getSelectedItem())));
        currentSchedule.setScheduleDate(txtDate.getDate());
        currentSchedule.setState(state);
        currentSchedule.setServiceID(Integer.valueOf(String.valueOf(cbbService.getSelectedItem())));
        currentSchedule.setRoomID(Integer.valueOf(String.valueOf(cbbRoom.getSelectedItem())));
        currentSchedule.setDoctorID(Integer.valueOf(String.valueOf(cbbDoctor.getSelectedItem())));
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
    private javax.swing.JLabel errorDate;
    private javax.swing.JLabel errorDoctor;
    private javax.swing.JLabel errorRoom;
    private javax.swing.JLabel errorService;
    private javax.swing.JLabel errorState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSchedule;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtNextOrdinalNumber;
    // End of variables declaration//GEN-END:variables
}
