/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Attendance;
import Model.Employee;
import Model.Statistic;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class StatisticPageController {
    Attendance attendanceModel = new Attendance();
    Employee employeeModel = new Employee();
    Statistic statisticModel = new Statistic();
    public void queryData(String sql, List<Attendance> listOfAttendance, String sql2, List<Employee> listOfEmployee) {
        try {
            attendanceModel.getListOfAttendance(sql, listOfAttendance);
            employeeModel.getListOfEmployeesForStatistic(sql2, listOfEmployee);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer getNumberOfNewPatients(Calendar begin, Calendar end) throws SQLException {
        return statisticModel.getNumberOfNewPatients(begin, end);
    }
    public Integer getNumberOfNewAppointments(Calendar begin, Calendar end) throws SQLException {
        return statisticModel.getNumberOfNewAppointments(begin, end);
    }
    public Long getRevenue(Calendar begin, Calendar end) throws SQLException {
        return statisticModel.getRevenue(begin, end);
    }
}
