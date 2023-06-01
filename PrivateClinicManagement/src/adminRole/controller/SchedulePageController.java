/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Schedule;
import adminRole.view.SchedulePage;
import java.util.List;

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
    
    public void executeData(String sql){
        scheduleModel.executeDataOfSchedule(sql);
    }
}
