/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Patient;
import adminRole.view.PatientListTab;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.sql.PreparedStatement;


public class PatientListTabController {
    private PatientListTab view;
    private Patient patientModel = new Patient();
    public PatientListTabController(PatientListTab view)
    {
         this.view = view;
    }
    
    public void queryData(String sql, List<Patient> listOfPatient) throws SQLException
    {
        patientModel.getListOfPatients(sql, listOfPatient);
    }
    
    public void addPatient(Patient patient) throws SQLException
    {
        patientModel.addPatient(patient);
    }
    public void deletePatient(Integer patientID) throws SQLException
    {
        patientModel.deletePatient(patientID);
    }

}

