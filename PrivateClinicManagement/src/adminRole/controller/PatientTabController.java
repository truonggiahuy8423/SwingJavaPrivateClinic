/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;
import java.sql.PreparedStatement;
import Model.Appointment;
import Model.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class PatientTabController {

    private Patient patientModel = new Patient();
    private Appointment appointmentModel = new Appointment();
    public PatientTabController() {
    }
    public Patient queryData(Integer patient_id, String sql, List<Appointment> listOfAppointment) throws SQLException
    {
        Patient patient = patientModel.getAPatient(patient_id);
        if (patient != null)
        {
            appointmentModel.getListOfAppointmentsOfPatient(patient_id, sql, listOfAppointment);
        }
        return patient;
    }
    
    public void updatePatient(Patient updatedPatient) throws SQLException
    {
        patientModel.updatePatient(updatedPatient);    
    }
    
    public void deleteAppointment(Integer appointment_id) throws SQLException
    {
        appointmentModel.deleteAppointment(appointment_id);
    }
}
