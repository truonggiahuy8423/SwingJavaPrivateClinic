/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author GIAHUY
 */
public class Schedule {
    private long scheduleID;
    private Date scheduleDate;
    private String state;
    private long nextOrinalNumber;
    private long serviceID;
    private long roomID;
    private long employeeID;

    public Schedule(){}
    public Schedule( long scheduleID, Date scheduleDate, String state, long nextOrinalNumber,  long serviceID, long roomID, long employeeID){
        this.scheduleID = scheduleID;
        this.scheduleDate = scheduleDate;
        this.state = state;
        this.nextOrinalNumber = nextOrinalNumber;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.employeeID = employeeID;
    }
    
    public long getScheduleID(){
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
    
    public String getState(){
        return this.state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public long getNextOrinalNumber(){
        return this.nextOrinalNumber;
    }
    
    public void setNextOrinalNumber(long nextOrinalNumber){
        this.nextOrinalNumber = nextOrinalNumber;        
    }
    
    public long getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(long serviceID){
        this.serviceID = serviceID;
    }
    
    public long getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(long roomID){
        this.roomID = roomID;
    }
    
    public long getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID(long employeeID){
        this.employeeID = employeeID;
    }

}
