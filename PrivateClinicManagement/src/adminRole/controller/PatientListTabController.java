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

    public PatientListTabController(PatientListTab view) {
         this.view = view;
    }
    public boolean queryData(String sql, List<Patient> listOfPatient)
    {
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "AD";  // Replace with your username
        String password = "88888888";  // Replace with your password
        Connection connection =null; Statement statement = null; ResultSet result = null;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement() ;  
            result = statement.executeQuery(sql);
            //System.out.println(rs.next()); 
            while (result.next())
            {
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
                Patient p = new Patient(result.getLong(1), result.getString(2), result.getString(3), birthday, regis_date, 
                        expi_date, result.getString(7), result.getString(8));
                listOfPatient.add(p);
            }
            connection.close();
        } catch (SQLException e)
        { 
            System.out.println(e);
            return false; 
        }
        catch (ClassNotFoundException e)
        { 
            System.out.println(e);
            return false;
        }
        catch (Exception e)
        { e.printStackTrace();}
        finally{ 
            return true;
        }        
    }
    
    public void addPatient(Patient patient) throws SQLException
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            String sqlInsert = "insert into patient(patient_id, fullname, phone, birthday, "
                    + "registration_day, insurance_expiration, address, underlying_disease) values(68, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, patient.getFullname());
            statement.setString(2, patient.getPhone());
            statement.setDate(3, patient.getBirthday() == null ? null : new java.sql.Date(patient.getBirthday().getTimeInMillis()));
            statement.setDate(4, patient.getRegistrationDay() == null ? null : new java.sql.Date(patient.getRegistrationDay().getTimeInMillis()));
            statement.setDate(5, patient.getInsuranceExpiration() == null ? null : new java.sql.Date(patient.getInsuranceExpiration().getTimeInMillis()));
            statement.setString(6, patient.getAddress());
            statement.setString(7, patient.getUnderlyingDisease());
            statement.executeUpdate(); // co van de o day
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
    }
}

