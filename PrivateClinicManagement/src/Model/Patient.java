/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author GIAHUY
 */import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class Patient{
    private Long patientId;
    private String fullname;

    private String phone;

    private Calendar birthday;
    private Calendar registrationDay;
    private Calendar insuranceExpiration;
    private String address;
    private String underlyingDisease;
    public Patient(){}
    public Patient(Long id)
    {
        this.patientId = id;
    }
    public Patient(Long patientId, String fullname, String phone, Calendar birthday, Calendar registrationDay, Calendar insuranceExpiration, String address, String underlyingDisease) {

        this.patientId = patientId;
        this.fullname = fullname;
        this.phone = phone;
        this.birthday = birthday;
        this.registrationDay = registrationDay;
        this.insuranceExpiration = insuranceExpiration;
        this.address = address;
        this.underlyingDisease = underlyingDisease;

        

    }
    public void addPatient(Patient patient) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            String sqlInsert = "insert into patient(patient_id, full_name, phone, birthday, "
                    + "registration_day, insurance_expiration, address, underlying_disease) values(patient_id_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.prepareStatement(sqlInsert);
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
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public void getListOfPatients(String sql, List<Patient> listOfPatient) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "AD";  // Replace with your username
        String password = "88888888";  // Replace with your password
        connection = null; statement = null; result = null;
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
            
            
        }
        catch (ClassNotFoundException e)
        { 

            System.out.println(e);
        }
        finally { 
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            
        }        
    }
    public Patient getAPatient(Long patient_id) throws SQLException
    {
        Patient patient = null;
        Connection connection = null;
        Statement statement = null; 
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement() ;  
            result = statement.executeQuery("select * from PATIENT where patient_id = " + String.valueOf(patient_id));
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
        } catch (ClassNotFoundException e) {}
        finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return patient;
    }
    public void deletePatient(Long patientID) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";
            String sql = "delete PATIENT where patient_id = " + patientID;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            if (statement.getUpdateCount() == 0) throw new SQLException();
            
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally { 
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public void updatePatient(Patient updatedPatient) throws SQLException
    {
        String sqlUpdate = "update patient "
                                + "set full_name = " + "?"
                                + ", phone = " + "?"
                                + ", birthday = " + "?"
                                + ", registration_day = " + "?"
                                + ", insurance_expiration = " + "?"
                                + ", address = " + "?"
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
    // Getters and Setters for the properties
    
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Calendar getRegistrationDay() {
        return registrationDay;
    }

    public Calendar getInsuranceExpiration() {
        return insuranceExpiration;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setRegistrationDay(Calendar registrationDay) {
        this.registrationDay = registrationDay;
    }

    public void setInsuranceExpiration(Calendar insuranceExpiration) {
        this.insuranceExpiration = insuranceExpiration;
    }
    
    
   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnderlyingDisease() {
        return underlyingDisease;
    }

    public void setUnderlyingDisease(String underlyingDisease) {
        this.underlyingDisease = underlyingDisease;
    }

    @Override
    public boolean equals(Object obj) {
        return this.patientId == ((Patient)obj).patientId;
    }
   
    
    
    
}