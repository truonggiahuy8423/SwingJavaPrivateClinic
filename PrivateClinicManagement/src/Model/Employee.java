/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class Employee {
    private Integer employeeID;
    private String name;
    private String phone;
    
    public Employee(){}
    
    public Employee(Integer employeeID, String name, String phone){
        this.employeeID = employeeID;
        this.name = name;
         this.phone = phone;
    }
    
    public Integer getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID(Integer employeeID){
        this.employeeID = employeeID;
    }    

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return this.phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public void getListOfEmployee(String sql, List<Employee> listOfEmployee){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Employee p = new Employee(result.getInt(1), result.getString(2), result.getString(3));
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
