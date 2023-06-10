/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;
import Model.Appointment;
import Model.Employee;
import Model.Schedule;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author GIAHUY
 */
public class LeftContentPaneDoctorController {
    private Employee employeeModel = new Employee();
    private Schedule scheduleModel = new Schedule();
    private Appointment appointmentModel = new Appointment();
    public Employee getAnEmployee(Integer id) throws SQLException {
        return employeeModel.getAnEmployee(id);
    }
    public Schedule getASchedule(Integer employee_id, Calendar date) throws SQLException {
        return scheduleModel.getAScheduleWithEmpIDAnDate(employee_id, date);
    }
    public void getListOfAppointment(Integer schedule_id, List<Appointment> listOfAppointment) throws SQLException {
        
        appointmentModel.getListOfAppointmentsOfSchedule(schedule_id, listOfAppointment);
    }
}
