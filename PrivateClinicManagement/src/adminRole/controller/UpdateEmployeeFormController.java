/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Employee;
import java.sql.SQLException;
/**
 *
 * @author GIAHUY
 */
public class UpdateEmployeeFormController {
    private Employee employeeModel = new Employee();
    public void updateEmployee(Employee employee) throws SQLException {
        employeeModel.updateEmployee(employee);
    }
}
