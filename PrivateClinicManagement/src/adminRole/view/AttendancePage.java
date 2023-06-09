/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Attendance;
import Model.Employee;
import adminRole.controller.AttendancePageController;
import adminRole.controller.EmployeeController;
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
public class AttendancePage extends javax.swing.JPanel {

    /**
     * Creates new form AttendancePage
     */
    private List<Attendance> listOfAttendance;
    private List<Employee> listOfEmployee;
    private Attendance currentAttendance;
    private AttendancePageController controller;
    private EmployeeController employeeController;  
    private DefaultTableModel dataOftable;
    public AttendancePage() {
        initComponents();
        
        controller = new AttendancePageController(this);
        listOfAttendance = new ArrayList();
        
        cbbAttendanceID.setBackground(Color.WHITE);
        txtAttendDate.setBackground(Color.WHITE);
        cbbEmployeeID.setBackground(Color.WHITE);
        txtEmployeeName.setBackground(Color.WHITE);
        btnAdd.setBackground(Color.WHITE);
        btnDelete.setBackground(Color.WHITE);
        btnUpdate.setBackground(Color.WHITE);
        btnSearch.setBackground(Color.WHITE);
        btnRefresh.setBackground(Color.WHITE);
        txtEmployeeName.setEditable(false);
        dataOftable = (DefaultTableModel)this.tbAttendance.getModel();
        dataOftable.setColumnIdentifiers(new Object[]{"Attendance ID", "Attend Date", "Employee ID", "Employee Name"});
        

        refreshData(this.listOfAttendance);
    }
    
    private void setUpComboboxData(){
        List<Attendance> dataAttendance = new ArrayList();
        List<Employee> dataEmployee = new ArrayList();
        
        // Clear data in List and combobox before load/reload
        dataAttendance.clear();
        dataEmployee.clear();
        
        cbbAttendanceID.removeAllItems();
        cbbEmployeeID.removeAllItems();
       
        // Load data
        String sql = "SELECT a.ATTENDANCE_ID, a.ATTEND_DATE, a.EMPLOYEE_ID, e.FULL_NAME "
                        + "FROM ATTENDANCE a, EMPLOYEE e "
                        + "WHERE a.EMPLOYEE_ID = e.EMPLOYEE_ID "
                        + "ORDER BY a.ATTENDANCE_ID ASC";
        
        employeeController = new EmployeeController(); 
        employeeController.queryData("select * from employee",dataEmployee);
        controller.queryData(sql, dataAttendance);
        
        // Add data into combobox      
        cbbAttendanceID.insertItemAt(null, 0);
        for(Attendance p: dataAttendance){
            cbbAttendanceID.addItem(String.valueOf(p.getAttendanceID()));
        }
        
        cbbEmployeeID.insertItemAt(null, 0);
        for(Employee p: dataEmployee){
            cbbEmployeeID.addItem(String.valueOf(p.getEmployeeID()));
        }
        
        txtAttendDate.setDate(null);
        txtEmployeeName.setText("");
   } 
    
    public void queryData(String sql){
        this.listOfAttendance.clear();
        controller.queryData(sql, this.listOfAttendance);
    }
    
    public void displayData(List<Attendance> listOfAttendance){
        dataOftable.setNumRows(0);

        for (Attendance p : listOfAttendance){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = sdf.format(p.getAttendDate());
            dataOftable.addRow(new Object[] {p.getAttendanceID(), dateString, p.getEmployeeID(), p.getEmployeeName()});
        }
    }
    
    public void setNullError(){
        errorAttendanceID.setText("");
        errorAttendDate.setText("");
        errorEmpID.setText("");
        errorEmpName.setText("");
    }
    
    public boolean checkNull(){
        boolean isOk = true;
        if(txtAttendDate.getDate() == null){
            errorAttendDate.setText("Date must not be empty");
            isOk = false;
        }
        
        if(cbbEmployeeID.getSelectedItem() == null){
            errorEmpID.setText("Employ ID must not be null");
        }
        return isOk;
    }
    
    public void refreshData(List<Attendance> listOfAttendance){
        String sql = "SELECT a.ATTENDANCE_ID, a.ATTEND_DATE, a.EMPLOYEE_ID, e.FULL_NAME "
                        + "FROM ATTENDANCE a, EMPLOYEE e "
                        + "WHERE a.EMPLOYEE_ID = e.EMPLOYEE_ID "
                        + "ORDER BY a.ATTENDANCE_ID ASC";

        setUpComboboxData();
        setNullError();
        queryData(sql);
        displayData(listOfAttendance);
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
        tbAttendance = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbAttendanceID = new javax.swing.JComboBox<>();
        cbbEmployeeID = new javax.swing.JComboBox<>();
        txtAttendDate = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmployeeName = new javax.swing.JTextField();
        errorAttendanceID = new javax.swing.JLabel();
        errorAttendDate = new javax.swing.JLabel();
        errorEmpID = new javax.swing.JLabel();
        errorEmpName = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1625, 765));

        tbAttendance.setModel(new javax.swing.table.DefaultTableModel(
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
        tbAttendance.setRowHeight(40);
        tbAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAttendanceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAttendance);

        jLabel1.setText("Attendance ID:");

        jLabel2.setText("Attend date:");

        jLabel3.setText("EmployeeID:");

        cbbAttendanceID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbEmployeeID.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbbEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbEmployeeIDActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel4.setText("Employee's Name");

        errorAttendanceID.setForeground(new java.awt.Color(255, 0, 0));
        errorAttendanceID.setText("error");
        errorAttendanceID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorAttendDate.setForeground(new java.awt.Color(255, 0, 0));
        errorAttendDate.setText("error");
        errorAttendDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorEmpID.setForeground(new java.awt.Color(255, 0, 0));
        errorEmpID.setText("error");
        errorEmpID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        errorEmpName.setForeground(new java.awt.Color(255, 0, 0));
        errorEmpName.setText("error");
        errorEmpName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbbAttendanceID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(errorAttendanceID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAttendDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(errorAttendDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbbEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(errorEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(cbbAttendanceID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd)
                        .addComponent(btnDelete)
                        .addComponent(btnUpdate)
                        .addComponent(btnSearch)
                        .addComponent(btnRefresh)
                        .addComponent(jLabel4)
                        .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAttendDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorAttendanceID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(errorAttendDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorEmpID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(checkNull() == true){
            Attendance addAttendance = new Attendance();
            addAttendance.setAttendanceID(null);
            addAttendance.setAttendDate(txtAttendDate.getDate());
            addAttendance.setEmployeeID(Integer.valueOf(String.valueOf(cbbEmployeeID.getSelectedItem())));
            addAttendance.setEmployeeName(String.valueOf(txtEmployeeName.getText()));
            controller.addData(addAttendance);
            refreshData(this.listOfAttendance);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(cbbAttendanceID.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn attendance muốn xóa");
            errorAttendanceID.setText("ID must not be null");
        }
        else{
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                controller.deleteData(String.valueOf(cbbAttendanceID.getSelectedItem()));
                refreshData(this.listOfAttendance);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(cbbAttendanceID.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn attendance muốn cập nhật");
            errorAttendanceID.setText("ID must not be null");
        }
        else if(checkNull()){
            int confirmOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn cập nhật?", "Cập nhật", JOptionPane.YES_NO_OPTION);
            if(confirmOption == JOptionPane.YES_OPTION){
                Attendance updateAttendance = new Attendance();
                updateAttendance.setAttendanceID(Integer.valueOf(String.valueOf(cbbAttendanceID.getSelectedItem())));
                updateAttendance.setAttendDate(txtAttendDate.getDate());
                updateAttendance.setEmployeeID(Integer.valueOf(String.valueOf(cbbEmployeeID.getSelectedItem())));
                updateAttendance.setEmployeeName(String.valueOf(txtEmployeeName.getText()));
                controller.updateData(updateAttendance, currentAttendance);
                refreshData(this.listOfAttendance);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        List<Attendance> listSearchAttendance = new ArrayList();
        Attendance searchAttendance = new Attendance();
        String attendanceID = String.valueOf(cbbAttendanceID.getSelectedItem());
        String empID = String.valueOf(cbbEmployeeID.getSelectedItem());
        searchAttendance.setAttendanceID(attendanceID.equals("null") ? null : Integer.valueOf(attendanceID));
        searchAttendance.setAttendDate(txtAttendDate.getDate());
        searchAttendance.setEmployeeID(empID.equals("null") ? null : Integer.valueOf(empID));
        
        controller.searchData(searchAttendance, listSearchAttendance);
        displayData(listSearchAttendance);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshData(this.listOfAttendance);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbAttendanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAttendanceMouseClicked
        int n = tbAttendance.getSelectedRow();
        // 
        for(int i = 1; i < cbbAttendanceID.getItemCount(); i++){
            if(cbbAttendanceID.getItemAt(i).equalsIgnoreCase(tbAttendance.getValueAt(n, 0).toString())){
                cbbAttendanceID.setSelectedIndex(i);
            }
        }
        
        for(int i = 1; i < cbbEmployeeID.getItemCount(); i++){
            if(cbbEmployeeID.getItemAt(i).equalsIgnoreCase(tbAttendance.getValueAt(n, 2).toString())){
                cbbEmployeeID.setSelectedIndex(i);
            }
        }
        
        txtEmployeeName.setText(String.valueOf((tbAttendance.getValueAt(n, 3))));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dateParseTemp = sdf.parse((String)tbAttendance.getValueAt(n, 1));
            String dateString = sdf.format(dateParseTemp);
            Date dateParse = sdf.parse(dateString);
            txtAttendDate.setDate(dateParse);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        currentAttendance = new Attendance();
        currentAttendance.setAttendanceID(Integer.valueOf(String.valueOf(cbbAttendanceID.getSelectedItem())));
        currentAttendance.setAttendDate(txtAttendDate.getDate());
        currentAttendance.setEmployeeID(Integer.valueOf(String.valueOf(cbbEmployeeID.getSelectedItem())));
        currentAttendance.setEmployeeName(String.valueOf(txtEmployeeName.getText()));
    }//GEN-LAST:event_tbAttendanceMouseClicked

    private void cbbEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbEmployeeIDActionPerformed
        listOfEmployee = new ArrayList();
        String EmpID = String.valueOf(cbbEmployeeID.getSelectedItem());
        if(EmpID.equals("null")){
            txtEmployeeName.setText("");
        }else{
            employeeController.queryData("select * from employee where employee_id = " + EmpID, listOfEmployee);
            for(Employee p: listOfEmployee){
                if(Integer.parseInt(EmpID) == p.getEmployeeID()){
                    txtEmployeeName.setText(p.getName());
                }
            }
        }
        
    }//GEN-LAST:event_cbbEmployeeIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbAttendanceID;
    private javax.swing.JComboBox<String> cbbEmployeeID;
    private javax.swing.JLabel errorAttendDate;
    private javax.swing.JLabel errorAttendanceID;
    private javax.swing.JLabel errorEmpID;
    private javax.swing.JLabel errorEmpName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbAttendance;
    private com.toedter.calendar.JDateChooser txtAttendDate;
    private javax.swing.JTextField txtEmployeeName;
    // End of variables declaration//GEN-END:variables
}
