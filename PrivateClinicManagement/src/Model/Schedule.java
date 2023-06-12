/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
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
    private Integer nextOrdinalNumber;
    private Integer serviceID;
    private Integer roomID;
    private Integer doctorID;
    private String doctorName;
    private String serviceName;
    private Integer cost;
    public Schedule(){}
    public Schedule( Integer scheduleID, Date scheduleDate, Integer state, Integer nextOrdinalNumber,  Integer serviceID, Integer roomID, Integer doctorID){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.nextOrdinalNumber = nextOrdinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;    
        this.doctorID = doctorID;
    }

    public Schedule(Integer scheduleID, Date scheduleDate, Integer state, Integer nextOrinalNumber,  Integer serviceID, String serviceName, Integer roomID, Integer doctorID, String doctorName, Integer cost){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.nextOrdinalNumber = nextOrinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.doctorName = doctorName;
        this.doctorID = doctorID;
        this.serviceName = serviceName;
        this.cost = cost;
    }
        
    
    public void getListOfSchedules(String sql, List<Schedule> listOfSchedule) throws SQLException 
            {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "UNI4";  // Replace with your username
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

    public void getListOfSchedule(String sql, List<Schedule> listOfSchedule){
        Connection connection = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
           connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
            System.out.println(sql);
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Schedule p = new Schedule(result.getInt(1), utilDate, result.getInt(3), result.getInt(4), 
                        result.getInt(5), result.getInt(6), result.getInt(7));
                listOfSchedule.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);
        }
        finally{
            
        }        
    }

    public void addSchedule(Schedule addSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            String sql = "INSERT INTO SCHEDULE VALUES(schedule_id_sequence.nextval, ?, ?, 1, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;

            statement.setDate(1, new java.sql.Date(addSchedule.getScheduleDate().getTime()));
            statement.setInt(2,addSchedule.getState());
            statement.setInt(3,addSchedule.getServiceID());
            statement.setInt(4,addSchedule.getRoomID());
            statement.setInt(5,addSchedule.getDoctorID());
            statement.executeUpdate();
            connection.close();
            System.out.println(sql);
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }

    public void deleteSchedule(String scheduleID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            String sql = "DELETE FROM SCHEDULE WHERE SCHEDULE_ID = " + scheduleID;
            Statement statement = connection.createStatement() ;
            statement.executeUpdate(sql);
            connection.close();
//                System.out.println(sql);
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }

    public void updateSchedule(Schedule updateSchedule, Schedule currentSchedule){
        String sql = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            sql = "UPDATE SCHEDULE SET ";
            Statement statement = connection.createStatement() ;
            boolean check = false;
            
            if(updateSchedule.getScheduleDate().compareTo(currentSchedule.getScheduleDate()) != 0){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                String dateString = sdf.format(updateSchedule.getScheduleDate());
                sql += "SCHEDULE_DATE = \'" + dateString + "\' ";
                check = true;
            }
            
            if(updateSchedule.getState() != currentSchedule.getState()){
                if(check) sql += ", ";
                sql += "STATE = " + String.valueOf(updateSchedule.getState());
                check = true;
            }
            
            if(updateSchedule.getServiceID() != currentSchedule.getServiceID()){
                if(check) sql += ", ";
                sql += "SERVICE_ID = " + String.valueOf(updateSchedule.getServiceID());
                check = true;
            }
            
            if(updateSchedule.getRoomID() != currentSchedule.getRoomID()){
                if(check) sql += ", ";
                sql += "ROOM_ID = " + String.valueOf(updateSchedule.getRoomID());
                check = true;
            }
            
            if(updateSchedule.getDoctorID() != currentSchedule.getDoctorID()){
                if(check) sql += ", ";
                sql += "EMPLOYEE_ID = " + String.valueOf(updateSchedule.getDoctorID());
                check = true;
            }
            sql += " WHERE SCHEDULE_ID = " + String.valueOf(updateSchedule.getScheduleID());
            statement.executeUpdate(sql);
            connection.close();
            
        } 
        catch (SQLException | ClassNotFoundException e){
                System.out.println(sql);
                JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    private String convert_calendar2(Calendar c)
    {
        return c == null ? "'----/--/--'" : "" + String.format("'%04d", c.get(Calendar.YEAR)) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "-"+ String.format("%02d'", c.get(Calendar.DATE));

    }
    public Schedule getAScheduleWithEmpIDAnDate(Integer employeeID, Calendar date) throws SQLException {
        Schedule schedule = null;
        Connection connection = null;
        try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            String sql = "SELECT * FROM SCHEDULE "
                            + "WHERE "
                                    + "trunc(SCHEDULE_DATE) = TRUNC(DATE " + convert_calendar2(date) + ") AND "
                                    + "EMPLOYEE_ID = "        + "?" ;
                                    
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            statement.setInt(1, employeeID);
            ResultSet result = statement.executeQuery();
            
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                schedule = new Schedule(result.getInt(1), utilDate, result.getInt(3), result.getInt(4), 
                        result.getInt(5), result.getInt(6), result.getInt(7));
                
            }
        } 
        catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());
        }
        finally{
            if (connection != null) connection.close();
        }
        return schedule;
    }
    public void searchSchedule(Schedule searchSchedule, List<Schedule> listSearchSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UNI4", "88888888");
            String sql = "SELECT * FROM SCHEDULE "
                            + "WHERE "
                                    + "SCHEDULE_ID = "        + "?"  +" OR "
                                    + "trunc(SCHEDULE_DATE) = " + "trunc(?)" + " OR "
                                    + "STATE = "                     + "?" +" OR "
                                    + "SERVICE_ID = "            + "?" + " OR "
                                    + "ROOM_ID = "                + "?" + " OR "
                                    + "EMPLOYEE_ID ="        + "?" ;
                                    
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            if(searchSchedule.getScheduleID() == null ){
                statement.setNull(1, Types.INTEGER);
            }
            else{
                statement.setInt(1, searchSchedule.getScheduleID());
            }
            
            if(searchSchedule.getScheduleDate() == null ){
                statement.setNull(2, Types.DATE);
            }
            else{
                statement.setDate(2, new java.sql.Date(searchSchedule.getScheduleDate().getTime()));
            }
            
            if(searchSchedule.getState() == null ){
                statement.setNull(3, Types.INTEGER);
            }
            else{
                statement.setInt(3, searchSchedule.getState());
            }
            
            if(searchSchedule.getServiceID() == null ){
                statement.setNull(4, Types.INTEGER);
            }
            else{
                statement.setInt(4, searchSchedule.getServiceID());
            }
            
            if(searchSchedule.getRoomID() == null ){
                statement.setNull(5, Types.INTEGER);
            }
            else{
                statement.setInt(5, searchSchedule.getRoomID());
            }

            if(searchSchedule.getDoctorID() == null ){
                statement.setNull(6, Types.INTEGER);
            }
            else{
                statement.setInt(6, searchSchedule.getDoctorID());
            }            
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Schedule p = new Schedule(result.getInt(1), utilDate, result.getInt(3), result.getInt(4), 
                        result.getInt(5), result.getInt(6), result.getInt(7));
                listSearchSchedule.add(p);
            }
            connection.close();
//                System.out.println(sql);
        } 
        catch (Exception e){
                JOptionPane.showMessageDialog(null, e.toString());
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setNextOrdinalNumber(Integer nextOrinalNumber) {
        this.nextOrdinalNumber = nextOrinalNumber;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getScheduleID() {
        return scheduleID;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public Integer getState() {
        return state;
    }

    public Integer getNextOrdinalNumber() {
        return nextOrdinalNumber;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Integer getCost() {
        return cost;
    }
    
}
