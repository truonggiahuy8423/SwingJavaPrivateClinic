/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Appointment;
import Model.Result;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author GIAHUY
 */
public class AppointmentTabController {
    private Appointment appointmentModel = new Appointment();
    private Result resultModel = new Result();
    public Appointment queryData(Long appointment_id, String sql, List<Result> listOfResults) throws SQLException
    {
        // appointment
        Appointment appointment = null;
        if ((appointment = appointmentModel.getAnAppointment(appointment_id)) != null )
        {
            resultModel.getListOfResults(sql, listOfResults);
        }
        return appointment;
        // list of results
    }
}
