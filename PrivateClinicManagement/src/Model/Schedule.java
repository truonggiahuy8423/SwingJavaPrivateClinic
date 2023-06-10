/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author GIAHUY
 */
public class Schedule {
    private Integer scheduleID;
    private Date scheduleDate;
    private Integer state;
    private Integer nextOrinalNumber;
    private Integer serviceID;
    private Integer roomID;
    private Integer employeeID;
    private String doctorName;
    private String serviceName;
    private Integer cost;
    public Schedule(){}
    public Schedule(Integer scheduleID, Date scheduleDate, Integer state, Integer nextOrinalNumber,  Integer serviceID, String serviceName, Integer roomID, Integer employeeID, String doctorName, Integer cost){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.nextOrinalNumber = nextOrinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.doctorName = doctorName;
        this.employeeID = employeeID;
        this.serviceName = serviceName;
        this.cost = cost;
    }
    public Schedule( Integer scheduleID, Date scheduleDate, Integer state, Integer nextOrinalNumber,  Integer serviceID, Integer roomID, Integer employeeID){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.nextOrinalNumber = nextOrinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.employeeID = employeeID;
        this.doctorName = doctorName;
        this.cost = cost;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setNextOrinalNumber(Integer nextOrinalNumber) {
        this.nextOrinalNumber = nextOrinalNumber;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Integer getCost() {
        return cost;
    }
    
    public Integer getScheduleID(){
        return this.scheduleID;
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
    
    public Integer getNextOrinalNumber(){
        return this.nextOrinalNumber;
    }
    
    
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    
    
    public Integer getRoomID(){
        return this.roomID;
    }
    
    
    
    public Integer getEmployeeID(){
        return this.employeeID;
    }
    
    
    public void getListOfSchedules(String sql, List<Schedule> listOfSchedule) throws SQLException 
            {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "AD";  // Replace with your username
        String password = "88888888";  // Replace with your password
        connection = null; statement = null; result = null;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement() ;  
            result = statement.executeQuery(sql);
            //System.out.println(rs.next()); 
            while (result.next())
            {
                // sc.schedule_id, sc.schedule_date, sc.state, sc.next_orinal_number, sc.service_id, sv.service_name, sc.room_id, sc.employee_id, e.full_name, sv.cost 
                Integer schedule_id = result.getInt(1);
                Date schedule_date = result.getDate(2);
                int state = result.getInt(3);
                Integer next_ordinal_number = result.getInt(4);
                Integer service_id = result.getInt(5);
                String service_name = result.getString(6);
                Integer room_id = result.getInt(7);
                Integer doctor_id = result.getInt(8);
                String doctor_name = result.getString(9);
                Integer cost = result.getInt(10);
                Schedule schedule = new Schedule(schedule_id, schedule_date, state, next_ordinal_number, service_id, service_name, room_id, doctor_id, doctor_name, cost);
                listOfSchedule.add(schedule);
            }
        }
        catch (ClassNotFoundException e)
        { 

            System.out.println(e);
        }
        finally { 
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();           
        }        
    }

}
