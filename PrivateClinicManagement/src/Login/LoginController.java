/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Model.Employee;
import Model.UserModel;
import javax.swing.JOptionPane;
import adminRole.view.MainView;
import java.sql.SQLException;
/**
 *
 * @author GIAHUY
 */
public class LoginController {
    private LoginView view;
    private Employee employeeModel = new Employee();
    public LoginController(LoginView view) {
        this.view = view;
    }
    
    public Employee login(UserModel user) throws SQLException
    {
        return employeeModel.getAnEmployee(Integer.valueOf(user.getAccount()));
    }
    
}
