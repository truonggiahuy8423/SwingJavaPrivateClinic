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

    public PatientTabController() {
    }
    public Patient queryData(Long paitent_id, String sql, List<Appointment> listOfAppointment) throws SQLException
    {
        Patient patient = null;
        Connection connection = null;
        Statement statement = null; 
        Statement statement2 = null;
        ResultSet result = null;
        ResultSet result2 = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement() ;  
            result = statement.executeQuery("select * from PATIENT where patient_id = " + String.valueOf(paitent_id));
            if (!result.next())
                return patient;
            
            Calendar birthday = null;
            if (result.getDate(4) != null)
            {
                birthday = Calendar.getInstance();
                birthday.setTimeInMillis(result.getDate(4).getTime());
            }
            Calendar regis_date =null;
            if (result.getDate(5) != null)
            {
                regis_date = Calendar.getInstance();
                regis_date.setTimeInMillis(result.getDate(5).getTime());
            }
            Calendar expi_date = null; 
            if (result.getDate(6) != null)
            {
                expi_date = Calendar.getInstance();
                expi_date.setTimeInMillis(result.getDate(6).getTime());
            }
            patient = new Patient(result.getLong(1), result.getString(2), result.getString(3), birthday, regis_date, expi_date, result.getString(7), result.getString(8));
            
            
            // query list of Appointment of this patient
            statement2 = connection.createStatement();
            result2 = statement2.executeQuery(sql);
            while (result2.next())
            {
                Appointment appointment = new Appointment();
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (result2 != null) result2.close();
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (statement2 != null) statement2.close();
            if (connection != null) connection.close();
        }
        return patient;
    }
    
    public void updatePatient(Patient updatedPatient) throws SQLException
    {
        String sqlUpdate = "update patient "
                                + "set fullname = " + "?"
                                + ", phone = " + "?"
                                + ", birthday = " + "?"
                                + ", registration_day = " + "?"
                                + ", insurance_expiration = " + "?"
                                + ", adress = " + "?"
                                + ", underlying_disease = " + "?"
                                + "where patient_id = " + "?";
        Connection connection = null;
        PreparedStatement statement = null; 
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, updatedPatient.getFullname());
            statement.setString(2, updatedPatient.getPhone());
            statement.setDate(3, updatedPatient.getBirthday() == null ? null : new java.sql.Date(updatedPatient.getBirthday().getTimeInMillis()));
            statement.setDate(4, updatedPatient.getRegistrationDay() == null ? null : new java.sql.Date(updatedPatient.getRegistrationDay().getTimeInMillis()));
            statement.setDate(5, updatedPatient.getInsuranceExpiration() == null ? null : new java.sql.Date(updatedPatient.getInsuranceExpiration().getTimeInMillis()));
            statement.setString(6, updatedPatient.getAddress());
            statement.setString(7, updatedPatient.getUnderlyingDisease());
            statement.setLong(8, updatedPatient.getPatientId());
            statement.executeUpdate();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        
    }
}
