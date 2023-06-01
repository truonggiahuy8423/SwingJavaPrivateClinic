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
    private Long scheduleID;
    private Date scheduleDate;
    private Integer state;
    private Long nextOrinalNumber;
    private Long serviceID;
    private Long roomID;
    private Long employeeID;
    private String doctorName;
    private String serviceName;
    private Long cost;
    public Schedule(){}
    public Schedule(Long scheduleID, Date scheduleDate, Integer state, Long nextOrinalNumber,  Long serviceID, String serviceName, Long roomID, Long employeeID, String doctorName, Long cost){
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
    public Schedule( Long scheduleID, Date scheduleDate, Integer state, Long nextOrinalNumber,  Long serviceID, Long roomID, Long employeeID){
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

    public void setScheduleID(Long scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setNextOrinalNumber(Long nextOrinalNumber) {
        this.nextOrinalNumber = nextOrinalNumber;
    }

    public void setServiceID(Long serviceID) {
        this.serviceID = serviceID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Long getCost() {
        return cost;
    }
    
    public Long getScheduleID(){
        return this.scheduleID;
    }
    
    public void setScheduleID(long scheduleID){
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
    
    public Long getNextOrinalNumber(){
        return this.nextOrinalNumber;
    }
    
    public void setNextOrinalNumber(long nextOrinalNumber){
        this.nextOrinalNumber = nextOrinalNumber;        
    }
    
    public Long getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(long serviceID){
        this.serviceID = serviceID;
    }
    
    public Long getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(long roomID){
        this.roomID = roomID;
    }
    
    public Long getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID(long employeeID){
        this.employeeID = employeeID;
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
                Long schedule_id = result.getLong(1);
                Date schedule_date = result.getDate(2);
                int state = result.getInt(3);
                Long next_ordinal_number = result.getLong(4);
                Long service_id = result.getLong(5);
                String service_name = result.getString(6);
                Long room_id = result.getLong(7);
                Long doctor_id = result.getLong(8);
                String doctor_name = result.getString(9);
                Long cost = result.getLong(10);
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
