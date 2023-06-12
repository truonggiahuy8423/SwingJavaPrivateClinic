/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Model.Employee;
import Model.UserModel;
import java.sql.SQLException;

/**
 *
 * @author GIAHUY
 */
public class ForgotPasswordFormController {
    private Employee employeeModel = new Employee();
    public Employee login(UserModel user) throws SQLException
    {
        return employeeModel.getAnEmployee(Integer.valueOf(user.getAccount()));
    }
    public void updatePassword(Employee updatedEmployee)throws SQLException {
        employeeModel.updateEmployee(updatedEmployee);
    }
    
}
