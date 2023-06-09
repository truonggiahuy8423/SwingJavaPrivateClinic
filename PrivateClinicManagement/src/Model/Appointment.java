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
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class Appointment {

    private Integer appointmentID;
    private Integer scheduleID;
    private Integer patientID;
    private Integer doctorID;
    private String doctorName;
    private String patientName;
    private Integer ordinalNumber;
    private Calendar date;
    private Integer room;
    private String service;
    private Integer final_cost;

    public void setFinal_cost(Integer final_cost) {
        this.final_cost = final_cost;
    }

    public Integer getFinal_cost() {
        return final_cost;
    }

    public Appointment() {
    }

    public Appointment(Integer appointmentID, Integer scheduleID, Integer patientID, Integer doctorID, String doctorName, String patientName, Integer ordinalNumber, Calendar date, Integer room, String service, Integer final_cost) {
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

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public void setDoctorID(Integer doctorID) {
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

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public Integer getScheduleID() {
        return scheduleID;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public Integer getDoctorID() {
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

    public void getListOfAppointmentsOfPatient(Integer patient_id, String sql, List<Appointment> listOfAppointment) throws SQLException {
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
            while (result2.next()) {
                Integer appointmentID = result2.getInt(1);
                Integer scheduleID = result2.getInt(2);
                Integer patientID = patient_id;
                Integer doctorID = result2.getInt(5);
                String doctorName = result2.getString(6);
                String patientName = result2.getString(4);
                Integer ordinalNumber = result2.getInt(7);
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(result2.getDate(8).getTime());
                Integer room = result2.getInt(9);
                String service = result2.getString(10);
                Integer final_cost = result2.getInt(11);

                Appointment appointment = new Appointment(appointmentID, scheduleID, patientID, doctorID, doctorName, patientName, ordinalNumber, date, room, service, final_cost);
                listOfAppointment.add(appointment);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (result2 != null) {
                result2.close();
            }
            if (statement2 != null) {
                statement2.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void addAnAppointment(Appointment appointment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "insert into appointment(appointment_id, schedule_id, patient_id, fee, ordinal_number) values(appointment_id_sequence.nextval, ?, ?, 0, 0)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, appointment.getScheduleID());
            statement.setInt(2, appointment.getPatientID());
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {

        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAppointment(Integer appointment_id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";
            String sql = "delete APPOINTMENT where appointment_id = " + appointment_id;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            if (statement.getUpdateCount() == 0) {
                throw new SQLException();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Appointment getAnAppointment(Integer appointment_id) throws SQLException {
        Appointment appointment = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            result = statement.executeQuery("select a.appointment_id, s.schedule_id, p.patient_id, p.full_name, e.employee_id, e.full_name, a.ordinal_number, s.schedule_date, s.room_id, sv.service_name, a.fee "
                    + "from Appointment a inner join Patient p on a.patient_id = p.patient_id "
                    + "inner join Schedule s on a.schedule_id = s.schedule_id "
                    + "inner join Employee e on s.employee_id = e.employee_id "
                    + "inner join Service sv on s.service_id = sv.service_id where appointment_id = " + String.valueOf(appointment_id));
            if (!result.next()) {
                return appointment;
            }
            Integer appointmentID = appointment_id;
            Integer scheduleID = result.getInt(2);
            Integer patientID = result.getInt(3);
            Integer doctorID = result.getInt(5);
            String doctorName = result.getString(6);
            String patientName = result.getString(4);
            Integer ordinalNumber = result.getInt(7);
            Calendar date = Calendar.getInstance();
            date.setTimeInMillis(result.getDate(8).getTime());
            Integer room = result.getInt(9);
            String service = result.getString(10);
            Integer final_cost = result.getInt(11);

            appointment = new Appointment(appointmentID, scheduleID, patientID, doctorID, doctorName, patientName, ordinalNumber, date, room, service, final_cost);
        } catch (ClassNotFoundException e) {
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return appointment;
    }
}
