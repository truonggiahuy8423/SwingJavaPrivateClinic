/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Employee;
import Model.Schedule;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class LeftContentPaneRecepController {
    private Employee employeeModel = new Employee();
    private Schedule scheduleModel = new Schedule();
    public Employee getAnEmployee(Integer id) throws SQLException {
        return employeeModel.getAnEmployee(id);
    }
    public void getScheduleList(String sql, List<Schedule> listOfSchedule) throws SQLException {
        scheduleModel.getListOfSchedules(sql, listOfSchedule);
    }
}
