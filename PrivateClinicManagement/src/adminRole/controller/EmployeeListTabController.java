/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Employee;
import adminRole.view.EmployeeListTab;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.PreparedStatement;

/**
 *
 * @author sun sun
 */
public class EmployeeListTabController {
    private EmployeeListTab view;
    private Employee employeeModel = new Employee();
    public EmployeeListTabController(EmployeeListTab view)
    {
         this.view = view;
    }
    
    public void queryData(String sql, List<Employee> listOfEmployee) throws SQLException
    {
        employeeModel.getListOfEmployees(sql, listOfEmployee);
    }
    
    public void addEmployee(Employee employee) throws SQLException
    {
        employeeModel.addEmployee(employee);
    }
    public void deleteEmployee(int employeeID) throws SQLException
    {
        employeeModel.deleteEmployee(employeeID);
    }
}
