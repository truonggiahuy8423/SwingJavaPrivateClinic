/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import java.util.List;

/**
 *
 * @author Admin
 */
public class EmployeeController {
    Employee employeeModel = new Employee();
    public EmployeeController(){}
    public void queryData(String sql, List<Employee> listOfEmployee){
        try {
            employeeModel.getListOfEmployees(sql, listOfEmployee);
        }
        catch (SQLException ee){
            ee.printStackTrace();
        }

    }
}
