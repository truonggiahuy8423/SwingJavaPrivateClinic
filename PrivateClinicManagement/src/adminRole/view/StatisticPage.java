/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Attendance;
import Model.Employee;
import adminRole.controller.StatisticPageController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIAHUY
 */
public class StatisticPage extends javax.swing.JPanel {

    private List<Attendance> listOfAttendance = new ArrayList<>();
    private DefaultTableModel dataOfAttendanceTable;
    private List<Employee> listOfEmployee = new ArrayList<>();
    private DefaultTableModel dataOfEmployeeTable;
    private Integer newPatients;
    private Integer newAppointments;
    private Long revenue;
    private Long totalSalary;
    private Long totalProfit;
    private Long otherCost_;
    /**
     * Creates new form StatisticPage
     */
    
    public StatisticPage() {
        initComponents();
        statisticButton.setBackground(Color.WHITE);
        begin.getCalendarButton().setBackground(Color.WHITE);
        end.getCalendarButton().setBackground(Color.WHITE);
        resetButton.setBackground(Color.WHITE);
        attendanceTable.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
        });
        dataOfAttendanceTable = (DefaultTableModel)attendanceTable.getModel();
        attendanceTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataOfAttendanceTable.setColumnIdentifiers(new Object[] {"Attendance ID", "Date", "Employee ID", "Name"});
        employeeTable.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
        });
        dataOfEmployeeTable = (DefaultTableModel)employeeTable.getModel();
        employeeTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataOfEmployeeTable.setColumnIdentifiers(new Object[] {"Employee ID", "Name", "Job Title", "Salary Per Day", "Working Days", "Salary"});

        //listener
        this.resetButton.addActionListener(e -> resetView());
        this.statisticButton.addActionListener(e -> {
            if (begin.getCalendar() == null || end.getCalendar() == null || begin.getCalendar().getTimeInMillis() > end.getCalendar().getTimeInMillis()) {
                JOptionPane.showMessageDialog(StatisticPage.this, "Invalid starting date and ending date", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            getStatistic();
        });
        resetView();
    }
    private void resetView() {
        listOfAttendance.removeAll(listOfAttendance);
        listOfEmployee.removeAll(listOfEmployee);
        dataOfAttendanceTable.setRowCount(0);
        dataOfEmployeeTable.setRowCount(0);
        otherCost.setText("");
        begin.setCalendar(null);
        end.setCalendar(null);
        title.setText("Report from yyyy/mm/dd - yyyy/mm/dd");
        newPatientTextField.setText("");
        newAppointmentTextField.setText("");
        revenueField.setText("");
        otherCostField.setText("");
        totalEmployeeSalaryField.setText("");
        totalProfitField.setText("");
    }
    private String convert_calendar2(Calendar c)
    {
        return c == null ? "'----/--/--'" : "" + String.format("'%02d", c.get(Calendar.YEAR)) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "-"+ String.format("%02d'", c.get(Calendar.DATE));

    }
    private String convert_calendar(Calendar c)
    {
        return c == null ? "----/--/--" : "" + String.format("%02d", c.get(Calendar.YEAR)) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "-"+ String.format("%02d", c.get(Calendar.DATE));

    }
    private void getStatistic() {
        queryData();
        displayData();
    }
    private void queryData() {
        String sql = "SELECT a.ATTENDANCE_ID, a.ATTEND_DATE, a.EMPLOYEE_ID, e.FULL_NAME "
                        + "FROM ATTENDANCE a, EMPLOYEE e "
                        + "WHERE a.EMPLOYEE_ID = e.EMPLOYEE_ID AND a.ATTEND_DATE >= " + "DATE " + convert_calendar2(begin.getCalendar()) + " AND a.ATTEND_DATE <= " + "DATE " + convert_calendar2(end.getCalendar())
                        + " and e.role_id <> 3 ORDER BY a.ATTEND_DATE ASC";
        String sql2 = "select employee.*, role.role_name, count(*), count(*)*employee.salary_per_day from employee inner join role on employee.role_id = role.role_id "
                + "inner join attendance a on employee.employee_id = a.employee_id where trunc(a.attend_date) >= trunc(DATE " + convert_calendar2(begin.getCalendar()) + ") and trunc(a.attend_date) <= trunc(DATE " + convert_calendar2(end.getCalendar())
                + ") group by employee.employee_id, employee.full_name, employee.phone, employee.birthday, employee.start_day, employee.address, employee.hometown, employee.password, employee.salary_per_day, employee.role_id, role.role_name";
        listOfAttendance.removeAll(listOfAttendance);
        listOfEmployee.removeAll(listOfEmployee);
        new StatisticPageController().queryData(sql, listOfAttendance, sql2, listOfEmployee);
        // thong so khac
        try {
            newPatients = new StatisticPageController().getNumberOfNewPatients(begin.getCalendar(), end.getCalendar());
            newAppointments = new StatisticPageController().getNumberOfNewAppointments(begin.getCalendar(), end.getCalendar());
            revenue = new StatisticPageController().getRevenue(begin.getCalendar(), end.getCalendar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        otherCost_ = Long.valueOf(otherCost.getText());
        } catch (NumberFormatException e) {
            otherCost_ = (long)0;
        }
        totalSalary = (long)0;
        for (Employee e : listOfEmployee) {
            totalSalary += e.getSalary();
        }
        totalProfit = revenue - totalSalary - otherCost_;

    }
    private void displayData() {
        dataOfAttendanceTable.setRowCount(0);
        dataOfEmployeeTable.setRowCount(0);
        for (Attendance a : listOfAttendance) {
            Calendar c = Calendar.getInstance(); c.setTimeInMillis(a.getAttendDate().getTime());
            dataOfAttendanceTable.addRow(new Object[] {a.getAttendanceID(), convert_calendar(c), a.getEmployeeID(), a.getEmployeeName()});
        }
        for (Employee e : listOfEmployee) {
            dataOfEmployeeTable.addRow(new Object[] {e.getEmployeeID(), e.getName(), e.getRoleName(), e.getSalaryPerDay(), e.getWorkingDay(), e.getSalary()});
        }
        // thong so khac
        Calendar beginCa = begin.getCalendar();
        Calendar endCa = end.getCalendar();
        title.setText(String.format("Report from %04d/%02d/%02d - %04d/%02d/%02d", beginCa.get(Calendar.YEAR), beginCa.get(Calendar.MONTH) + 1, beginCa.get(Calendar.DATE), endCa.get(Calendar.YEAR), endCa.get(Calendar.MONTH) + 1, endCa.get(Calendar.DATE)));
        newPatientTextField.setText(newPatients.toString());
        newAppointmentTextField.setText(newAppointments.toString());
        revenueField.setText(revenue.toString());
        otherCostField.setText(otherCost_.toString());
        totalEmployeeSalaryField.setText(totalSalary.toString());
        totalProfitField.setText(totalProfit.toString());       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        begin = new com.toedter.calendar.JDateChooser();
        end = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        statisticButton = new javax.swing.JButton();
        otherCost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        newAppointmentTextField = new javax.swing.JTextField();
        newPatientTextField = new javax.swing.JTextField();
        revenueField = new javax.swing.JTextField();
        totalEmployeeSalaryField = new javax.swing.JTextField();
        otherCostField = new javax.swing.JTextField();
        title = new javax.swing.JLabel();
        totalProfitField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1550, 760));
        setMinimumSize(new java.awt.Dimension(1550, 760));
        setPreferredSize(new java.awt.Dimension(1550, 760));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("To:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("From:");

        statisticButton.setText("Statistic");
        statisticButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        statisticButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticButtonActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Other cost:");

        resetButton.setText("Reset");
        resetButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(716, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otherCost, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(begin, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(statisticButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(begin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(otherCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statisticButton)
                    .addComponent(resetButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(attendanceTable);

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(employeeTable);

        totalEmployeeSalaryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalEmployeeSalaryFieldActionPerformed(evt);
            }
        });

        otherCostField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherCostFieldActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        title.setText("Report from yyyy/mm/dd - yyyy/mm/dd");

        totalProfitField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalProfitFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("New patients:");

        jLabel6.setText("New appointments:");

        jLabel7.setText("Revenue:");

        jLabel8.setText("Total employee salary:");

        jLabel9.setText("Other cost:");

        jLabel11.setText("Total profit:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(newPatientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalEmployeeSalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(revenueField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(newAppointmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(142, 142, 142))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(totalProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(otherCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newPatientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))
                            .addComponent(newAppointmentTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(revenueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(otherCostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalEmployeeSalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(totalProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void statisticButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statisticButtonActionPerformed

    private void totalEmployeeSalaryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalEmployeeSalaryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalEmployeeSalaryFieldActionPerformed

    private void otherCostFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherCostFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otherCostFieldActionPerformed

    private void totalProfitFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalProfitFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalProfitFieldActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendanceTable;
    private com.toedter.calendar.JDateChooser begin;
    private javax.swing.JTable employeeTable;
    private com.toedter.calendar.JDateChooser end;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField newAppointmentTextField;
    private javax.swing.JTextField newPatientTextField;
    private javax.swing.JTextField otherCost;
    private javax.swing.JTextField otherCostField;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField revenueField;
    private javax.swing.JButton statisticButton;
    private javax.swing.JLabel title;
    private javax.swing.JTextField totalEmployeeSalaryField;
    private javax.swing.JTextField totalProfitField;
    // End of variables declaration//GEN-END:variables
}
