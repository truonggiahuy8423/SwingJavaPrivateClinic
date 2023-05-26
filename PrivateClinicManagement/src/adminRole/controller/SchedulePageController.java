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

/**
 *
 * @author Admin
 */
public class SchedulePageController {
    private SchedulePage view;

    public SchedulePageController(SchedulePage view) {
         this.view = view;
    }
    
    public boolean queryData(String sql, List<Schedule> listOfPatient){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "C##HELEN", "khanh123");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
//            ResultSet result = statement.executeQuery(sql);
            System.out.println(result.getLong(1)+ "  " + result.getDate(2)+ "  " + result.getString(3)+ "  " + result.getLong(4)+ "  " + result.getLong(5)+ "  " + result.getLong(6)+ "  " + result.getLong(7));
            while (result.next()){
                Schedule p = new Schedule(result.getLong(1), result.getDate(2), result.getString(3), result.getLong(4), result.getLong(5), result.getLong(6), result.getLong(7));
                listOfPatient.add(p);
                System.out.println(result.getLong(1)+ "  " + result.getDate(2)+ "  " + result.getString(3)+ "  " + result.getLong(4)+ "  " + result.getLong(5)+ "  " + result.getLong(6)+ "  " + result.getLong(7));
//                System.out.println(result.getInt(1) + "  " + result.getString(2) + "  " + result.getString(3) + "  " + result.getString(4) );
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            return false; 
        }
        finally{
            return true;
        }        
    }
}
