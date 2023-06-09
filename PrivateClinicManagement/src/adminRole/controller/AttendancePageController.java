/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Attendance;
import adminRole.view.AttendancePage;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AttendancePageController {
    private AttendancePage view;
    private Attendance attendanceModel = new Attendance();

    public AttendancePageController(AttendancePage view) {
         this.view = view;
    }
    
    public void queryData(String sql, List<Attendance> listOfAttendance){
        attendanceModel.getListOfAttendance(sql, listOfAttendance);
    }
    
    public void addData(Attendance addAttendance){
        attendanceModel.addAttendance(addAttendance);
    }
        
    public void deleteData(String attendanceID){
        attendanceModel.deleteAttendance(attendanceID);
    }
    
    public void updateData(Attendance updateAttendance, Attendance currentAttendance){
        attendanceModel.updateAttendance(updateAttendance, currentAttendance);
    }
    
    public void searchData(Attendance searchAttendance, List<Attendance> listSearchAttendance){
        attendanceModel.searchAttendance(searchAttendance, listSearchAttendance);
    }
}
