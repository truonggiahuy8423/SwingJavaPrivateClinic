/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Appointment;
import Model.Patient;
import Model.Schedule;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author GIAHUY
 */
public class AddAppointmentFormController {
    private Schedule scheduleModel = new Schedule();
    private Patient patientModel = new Patient();
    private Appointment appointmentModel = new Appointment();
    public void queryData(String sql, List<Schedule> listOfSchedule) throws SQLException
    {
        scheduleModel.getListOfSchedules(sql, listOfSchedule);
    }
    public Patient getAPatient(Long patient_id) throws SQLException
    {
        return patientModel.getAPatient(patient_id);
    }
    public void addAnAppointment(Appointment appointment) throws SQLException
    {
        appointmentModel.addAnAppointment(appointment);
    }
}
