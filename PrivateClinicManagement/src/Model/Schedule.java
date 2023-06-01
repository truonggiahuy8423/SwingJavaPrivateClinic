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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author GIAHUY
 */
public class Schedule {
    private Long scheduleID;
    private Date scheduleDate;
    private Integer state;
    private Long NextOrdinalNumber;
    private Long serviceID;
    private Long roomID;
    private Long employeeID;

    public Schedule(){}
    public Schedule( Long scheduleID, Date scheduleDate, Integer state, Long NextOrdinalNumber,  Long serviceID, Long roomID, Long employeeID){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.NextOrdinalNumber = NextOrdinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.employeeID = employeeID;
    }
    
    public Long getScheduleID(){
        return this.scheduleID;
    }
    
    public void setScheduleID(Long scheduleID){
         this.scheduleID = scheduleID;       
    }
    
    public Date getScheduleDate(){
        return scheduleDate;
    }
    
    public void setScheduleDate(Date scheduleDate){
        this.scheduleDate = scheduleDate;
    }
    
    public Integer getState(){
        return this.state;
    }
    
    public void setState(Integer state){
        this.state = state;
    }
    
    public Long getNextOrdinalNumber(){
        return this.NextOrdinalNumber;
    }
    
    public void setNextOrdinalNumber(Long NextOrdinalNumber){
        this.NextOrdinalNumber = NextOrdinalNumber;        
    }
    
    public Long getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(Long serviceID){
        this.serviceID = serviceID;
    }
    
    public Long getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(Long roomID){
        this.roomID = roomID;
    }
    
    public Long getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID(Long employeeID){
        this.employeeID = employeeID;
    }

    public void getListOfSchedule(String sql, List<Schedule> listOfSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
            System.out.println(sql);
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Schedule p = new Schedule(result.getLong(1), utilDate, result.getInt(3), result.getLong(4), result.getLong(5), result.getLong(6), result.getLong(7));
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
    
        public void executeDataOfSchedule(String sql){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
