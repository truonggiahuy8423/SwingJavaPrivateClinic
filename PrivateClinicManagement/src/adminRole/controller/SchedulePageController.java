/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Schedule;
import adminRole.view.SchedulePage;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SchedulePageController {
    private SchedulePage view;

    public SchedulePageController(SchedulePage view) {
         this.view = view;
    }
    
    public void queryData(String sql, List<Schedule> listOfSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##uni4", "123");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
            System.out.println(sql);
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Schedule p = new Schedule(result.getInt(1), utilDate, result.getInt(3), result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7));
                listOfSchedule.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void executeData(String sql){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##uni4", "123");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            System.out.println(sql);
            statement.executeUpdate(sql);
            
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);
            
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    

}
