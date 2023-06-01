/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
public class Appointment {
    private Long appointmentID;
    private Long scheduleID;
    private Long patientID;
    private Long doctorID;
    private String doctorName;
    private String patientName;
    private Integer ordinalNumber;
    private Calendar date;

    public void setFinal_cost(Long final_cost) {
        this.final_cost = final_cost;
    }
    private Integer room;
    private String service;
    private Long final_cost;

    public Long getFinal_cost() {
        return final_cost;
    }
    public Appointment() {
    }

    public Appointment(Long appointmentID, Long scheduleID, Long patientID, Long doctorID, String doctorName, String patientName, Integer ordinalNumber, Calendar date, Integer room, String service, Long final_cost) {
        this.appointmentID = appointmentID;
        this.scheduleID = scheduleID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.ordinalNumber = ordinalNumber;
        this.date = date;
        this.room = room;
        this.service = service;
        this.final_cost = final_cost;
    }
    

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setScheduleID(Long scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public void setDocterName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public Long getScheduleID() {
        return scheduleID;
    }

    public Long getPatientID() {
        return patientID;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public Integer getRoom() {
        return room;
    }

    public String getService() {
        return service;
    }
    public void getListOfAppointmentsOfPatient(Long patient_id, String sql, List<Appointment> listOfAppointment) throws SQLException
    {
        Connection connection = null;
        Statement statement2 = null;
        ResultSet result2 = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // query list of Appointment of this patient
            statement2 = connection.createStatement();
            result2 = statement2.executeQuery(sql);
            while (result2.next())
            {
                Long appointmentID = result2.getLong(1);
                Long scheduleID = result2.getLong(2);
                Long patientID = patient_id;
                Long doctorID = result2.getLong(5);
                String doctorName = result2.getString(6);
                String patientName = result2.getString(4);
                Integer ordinalNumber = result2.getInt(7);
                Calendar date = Calendar.getInstance();
                        date.setTimeInMillis(result2.getDate(8).getTime());
                Integer room = result2.getInt(9);
                String service = result2.getString(10);
                Long final_cost = result2.getLong(11);
                        
                Appointment appointment = new Appointment(appointmentID, scheduleID, patientID, doctorID, doctorName, patientName, ordinalNumber, date, room, service, final_cost);
                listOfAppointment.add(appointment);
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (result2 != null) result2.close();
            if (statement2 != null) statement2.close();
            if (connection != null) connection.close();
        }
    }
}
