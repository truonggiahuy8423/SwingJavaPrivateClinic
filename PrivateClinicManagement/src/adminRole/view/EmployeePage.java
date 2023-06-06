/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package adminRole.view;

import Model.Employee;
import adminRole.controller.EmployeePageController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.SQLException;
import java.util.Calendar;

import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author GIAHUY
 */

public class EmployeePage extends javax.swing.JPanel {

    /**
     * Creates new form EmployeePage
     */
    private final int NEWEST = 0;
    private final int OLDEST = 1;
    private final int ID_ASC = 2;
    private final int ID_DESC = 3;
    private int sortMode = NEWEST;
    private EmployeePageController controller;
    private EmployeePage parent;
    private List<Employee> listOfEmployee;
    private DefaultTableModel dataOftable;

    private void sortEmployeeList()
    {
        switch (this.sortMode) {
            case ID_ASC:
                Collections.sort(listOfEmployee, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getEmployeeId() > o2.getEmployeeId())
                    return 1;
                else if (o1.getEmployeeId() < o2.getEmployeeId())
                    return -1;
                else return 0;
            }
        });
                break;
            case ID_DESC:
                Collections.sort(listOfEmployee, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getEmployeeId() < o2.getEmployeeId())
                    return 1;
                else if (o1.getEmployeeId() > o2.getEmployeeId())
                    return -1;
                else return 0;
            }
        });
                break;
            case NEWEST:
                Collections.sort(listOfEmployee, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getStartDay() == null && o2.getStartDay() == null) return 0;
                if (o1.getStartDay() == null && o2.getStartDay() != null) return 1;
                if (o1.getStartDay() != null && o2.getStartDay() == null) return -1;
                if (o1.getStartDay().getTimeInMillis() < o2.getStartDay().getTimeInMillis())
                    return 1;
                else if (o1.getStartDay().getTimeInMillis() > o2.getStartDay().getTimeInMillis())
                    return -1;
                else return 0;
            }
        });
                break;
            case OLDEST:
                Collections.sort(listOfEmployee, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getStartDay() == null && o2.getStartDay() == null) return 0;
                if (o1.getStartDay() == null && o2.getStartDay() != null) return 1;
                if (o1.getStartDay() != null && o2.getStartDay() == null) return -1;
                if (o1.getStartDay().getTimeInMillis() > o2.getStartDay().getTimeInMillis())
                    return 1;
                else if (o1.getStartDay().getTimeInMillis() < o2.getStartDay().getTimeInMillis())
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
        return "Employee List"; 
    } 
    private class ImageRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String photoName = value.toString();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/resources/" + photoName).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        return new JLabel(imageIcon);
    }
}
    private void queryData(String sql) // load các record của employee vào list (Lưu ý thứ tự và các cột của câu query phải trùng khớp với lúc lấy data từ ResultSet)
    {
        listOfEmployee.removeAll(listOfEmployee);
        try {
            controller.queryData(sql, this.listOfEmployee); 
        } catch (SQLException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
            //Employee p = null;
        
    }
    private void displayData() // load từ list vào bảng
    {
        dataOftable.setRowCount(0);
        
        for (Employee p : this.listOfEmployee) {
            dataOftable.addRow(new Object[] {p.getEmployeeId(), p.getPortrait(), p.getFullName(), p.getRoleId(), p.getPhone(),
                p.getPassword() == null ? "None" : p.getPassword(), convert_calendar(p.getBirthday()), 
                p.getAddress() == null ? "None" : p.getAddress(), p.getHometown() == null ? "None" : p.getHometown()});
        }
        tableOfEmployee.setModel(dataOftable);
        tableOfEmployee.setRowHeight(100);
        tableOfEmployee.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
    }
//    @Override
    public void refreshData()
    {
        queryData("select employee_id, portrait, full_name, phone, role_id, password, birthday, address, hometown from employee");
        sortEmployeeList();
        displayData();
    }
    
    public EmployeePage() {
        initComponents();
        controller = new EmployeePageController(this);
        this.parent = parent;
        listOfEmployee = new ArrayList<>();
        //set properties components
        tableOfEmployee.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        } );
        sortChooser.removeAllItems();
        sortChooser.addItem("Newest");
        sortChooser.addItem("Oldest");
        sortChooser.addItem("ID ASC");
        sortChooser.addItem("ID DESC");
        sortChooser.setSelectedIndex(0);
        addButton.setBackground(Color.white);
        deleteButton.setBackground(Color.white);
        searchButton.setBackground(Color.WHITE);
        searchTextField.setBackground(Color.WHITE);
        dataOftable = (DefaultTableModel)this.tableOfEmployee.getModel(); 
        dataOftable.setColumnIdentifiers(new Object[]{"Employee ID", "Portrait", "Name", "Phone", "Position title", "Password", "Birthday", "Address", "Hometown"});
        refreshButton.setBackground(Color.WHITE);
        // set action event
        addButton.addActionListener(e -> {
            AddEmployeeForm form = new AddEmployeeForm(null, true, parent); 
            form.setVisible(true);
        }); 
        refreshButton.addActionListener(e -> {refreshData();});
        sortChooser.addItemListener(e -> {
            if (((String)EmployeePage.this.sortChooser.getSelectedItem()).equals("Newest"))
            {
                sortMode = NEWEST; refreshData();
            }
            if (((String)EmployeePage.this.sortChooser.getSelectedItem()).equals("Oldest"))
            {
                sortMode = OLDEST; refreshData();
            }
            if (((String)EmployeePage.this.sortChooser.getSelectedItem()).equals("ID ASC"))
            {
                sortMode = ID_ASC; refreshData();
            }
            if (((String)EmployeePage.this.sortChooser.getSelectedItem()).equals("ID DESC"))
            {
                sortMode = ID_DESC; refreshData();
            }
            refreshData();

        });
        this.deleteButton.addActionListener(e -> {
            int[] selectedRow = this.tableOfEmployee.getSelectedRows(); 
            if (selectedRow.length != 0)
            {
                int id = (int)(tableOfEmployee.getValueAt(selectedRow[0], 0));
                if (JOptionPane.showConfirmDialog(this, "Delete employee " + id, "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
                    return;
                // delete
                try 
                {
                    controller.deleteEmployee(id);
                    JOptionPane.showMessageDialog(this, "Successfully delete " + id, "", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException sqlE)
                {
                    JOptionPane.showMessageDialog(this, (sqlE.getErrorCode() == 2292 ? "Delete role of this employee first" : "Employee information no longer exists") , "", JOptionPane.INFORMATION_MESSAGE);
                }
                sortMode = NEWEST;
                //tableOfEmployee.get
                refreshData();
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "Please choose employee to be deleted!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        tableOfEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                {
                    int id = (int)tableOfEmployee.getValueAt(tableOfEmployee.getSelectedRow(), 0);
                    queryData("select * from Employee");
                    if (listOfEmployee.indexOf(new Employee(id)) == -1)
                    {
                        JOptionPane.showMessageDialog(parent, "Employee doesn't exist", "",  JOptionPane.INFORMATION_MESSAGE);
                        refreshData();
                    }
                }
            }
            
        });
        
        searchButton.addActionListener(e -> {
            if (!isNumber(searchTextField.getText())) 
            {
                JOptionPane.showMessageDialog(parent, "Employee id format is invalid!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String sql = "select employee_id, full_name, phone, role_id, password, birthday, address, hometown from employee "
                    +(searchTextField.getText().equals("") ? "" : ("where employee_id = " + searchTextField.getText()));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOfEmployee = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        sortChooser = new javax.swing.JComboBox<>();
        searchTextField = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1230, 718));
        setMinimumSize(new java.awt.Dimension(1230, 718));
        setPreferredSize(new java.awt.Dimension(1590, 763));
        setLayout(null);

        tableOfEmployee.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableOfEmployee);

        add(jScrollPane1);
        jScrollPane1.setBounds(6, 80, 1578, 607);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        add(addButton);
        addButton.setBounds(961, 39, 72, 23);

        searchButton.setText("Search");
        add(searchButton);
        searchButton.setBounds(6, 39, 72, 23);

        deleteButton.setText("Delete");
        add(deleteButton);
        deleteButton.setBounds(1179, 39, 72, 23);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton);
        refreshButton.setBounds(1281, 39, 72, 23);

        sortChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(sortChooser);
        sortChooser.setBounds(755, 39, 72, 22);

        searchTextField.setText("Employee ID");
        add(searchTextField);
        searchTextField.setBounds(116, 39, 80, 22);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> sortChooser;
    private javax.swing.JTable tableOfEmployee;
    // End of variables declaration//GEN-END:variables
}
