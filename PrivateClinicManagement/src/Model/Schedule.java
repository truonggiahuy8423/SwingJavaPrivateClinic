/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author GIAHUY
 */
public class Schedule {
    private Integer scheduleID;
    private Date scheduleDate;
    private Integer state;
    private Integer NextOrdinalNumber;
    private Integer serviceID;
    private Integer roomID;
    private Integer doctorID;

    public Schedule(){}
    public Schedule( Integer scheduleID, Date scheduleDate, Integer state, Integer NextOrdinalNumber,  Integer serviceID, Integer roomID, Integer doctorID){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.NextOrdinalNumber = NextOrdinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.doctorID = doctorID;
    }
    
    public Integer getScheduleID(){
        return this.scheduleID;
    }
    
    public void setScheduleID(Integer scheduleID){
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
    
    public Integer getNextOrdinalNumber(){
        return this.NextOrdinalNumber;
    }
    
    public void setNextOrdinalNumber(Integer NextOrdinalNumber){
        this.NextOrdinalNumber = NextOrdinalNumber;        
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(Integer serviceID){
        this.serviceID = serviceID;
    }
    
    public Integer getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(Integer roomID){
        this.roomID = roomID;
    }
    
    public Integer getDoctorID(){
        return this.doctorID;
    }
    
    public void setDoctorID(Integer doctorID){
        this.doctorID = doctorID;
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
            System.out.println("Successful"); 
        }        
    }

    public void addSchedule(Schedule addSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
    public void searchSchedule(Schedule searchSchedule, List<Schedule> listSearchSchedule){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "SELECT * FROM SCHEDULE "
                            + "WHERE "
                                    + "SCHEDULE_ID = "        + "?"  +" OR "
                                    + "SCHEDULE_DATE = " + "?" + " OR "
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
            
            if(searchSchedule.getState() == null ){
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
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
}
