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
    private Schedule scheduleModel = new Schedule();

    public SchedulePageController(SchedulePage view) {
         this.view = view;
    }
    
    public void queryData(String sql, List<Schedule> listOfSchedule){
        scheduleModel.getListOfSchedule(sql, listOfSchedule);
    }
    
    public void addData(String sql){
        scheduleModel.addSchedule(sql, scheduleModel);
    }
    
    public void deleteData(String sql){
        scheduleModel.deleteSchedule(sql, scheduleModel);
    }
    
    public void updateData(String sql){
        scheduleModel.updateSchedule(sql, scheduleModel);
    }
}
