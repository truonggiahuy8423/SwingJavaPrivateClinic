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
public class Result {

    private Long result_id;
    private Long appointment_id; // appointment
    private String reminder;
    private String diagnosis;
    private Long schedule_id; // schedule
    private Long patient_id; // patient
    private String patient_name;
    private Calendar patient_birthday;
    private Long doctor_id;
    private String doctor_name; // employee
    private String underlying_disease;

    public Result() {
    }

    public Result(Long result_id, Long appointment_id, String reminder, String diagnosis, Long schedule_id, Long patient_id, String patient_name, Calendar patient_birthday, Long doctor_id, String doctor_name, String underlying_disease) {
        this.result_id = result_id;
        this.appointment_id = appointment_id;
        this.reminder = reminder;
        this.diagnosis = diagnosis;
        this.schedule_id = schedule_id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_birthday = patient_birthday;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.underlying_disease = underlying_disease;
    }

    public void setResult_id(Long result_id) {
        this.result_id = result_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_birthday(Calendar patient_birthday) {
        this.patient_birthday = patient_birthday;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setUnderlying_disease(String underlying_disease) {
        this.underlying_disease = underlying_disease;
    }

    public Long getResult_id() {
        return result_id;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public String getReminder() {
        return reminder;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Long getSchedule_id() {
        return schedule_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public Calendar getPatient_birthday() {
        return patient_birthday;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getUnderlying_disease() {
        return underlying_disease;
    }

    public void getListOfResults(String sql, List<Result> listOfResults) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            //System.out.println(rs.next()); 
            // s.result_id, a.appointment_id, r.reminder, r.diagnosis, a.schedule_id, a.patient_id, p.full_name, p.birthday, e.employee_id, e.full_name, p.underlying_disease
            while (result.next()) {
                Calendar birthday = null;
                if (result.getDate(8) != null) {
                    birthday = Calendar.getInstance();
                    birthday.setTimeInMillis(result.getDate(8).getTime());
                }
                Result r = new Result(result.getLong(1), result.getLong(2), result.getString(3), result.getString(4), result.getLong(5), result.getLong(6),
                        result.getString(7), birthday, result.getLong(9), result.getString(10), result.getString(11));
                listOfResults.add(r);
            }

        } catch (ClassNotFoundException e) {

            System.out.println(e);
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

}
