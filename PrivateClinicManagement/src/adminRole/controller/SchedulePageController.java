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
    
//    public void executeData(String sql){
//        scheduleModel.executeDataOfSchedule(sql);
//    }
    
    public void addData(Schedule addSchedule){
        scheduleModel.addSchedule(addSchedule);
    }
        
    public void deleteData(String scheduleID){
        scheduleModel.deleteSchedule(scheduleID);
    }
    
    public void updateData(Schedule updateSchedule, Schedule currentSchedule){
        scheduleModel.updateSchedule(updateSchedule, currentSchedule);
    }
    
    public void searchData(Schedule searchSchedule, List<Schedule> listSearchSchedule){
        scheduleModel.searchSchedule(searchSchedule, listSearchSchedule);
    }
}
