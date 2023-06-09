/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Employee;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EmployeeController {
    private Employee employeeModel = new Employee();
    
    public EmployeeController(){}
    public void queryData(String sql, List<Employee> listOfEmployee){
        employeeModel.getListOfEmployee(sql, listOfEmployee);
    }
}
