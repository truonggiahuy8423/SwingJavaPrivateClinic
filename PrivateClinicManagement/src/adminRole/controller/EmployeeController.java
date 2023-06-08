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
    public EmployeeController(){}
    public void queryData(String sql, List<Employee> listOfEmployee){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##uni4", "123");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Calendar birthday = null;
                if (result.getDate(4) != null)
                {
                    birthday = Calendar.getInstance();
                    birthday.setTimeInMillis(result.getDate(4).getTime());
                }
                Calendar start_date = null;
                if (result.getDate(5) != null)
                {
                    start_date = Calendar.getInstance();
                    start_date.setTimeInMillis(result.getDate(5).getTime());
                }
                Employee p = new Employee(result.getInt(1), result.getString(2), result.getString(3), birthday, start_date, result.getString(6), result.getString(7), result.getString(8), result.getInt(9), result.getString(10), result.getInt(11));
                listOfEmployee.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
}
