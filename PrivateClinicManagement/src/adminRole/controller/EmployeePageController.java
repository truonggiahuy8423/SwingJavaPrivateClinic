
package adminRole.controller;

import Model.Employee;
import adminRole.view.EmployeePage;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sun sun
 */
public class EmployeePageController {
    private EmployeePage view;
    private Employee employeeModel = new Employee();

    public EmployeePageController() {
    }
    
    public EmployeePageController(EmployeePage view)
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
