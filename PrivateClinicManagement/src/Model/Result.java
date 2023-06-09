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
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class Result {

    private Integer result_id;
    private Integer appointment_id; // appointment
    private String reminder;
    private String diagnosis;
    private Integer schedule_id; // schedule
    private Integer patient_id; // patient
    private String patient_name;
    private Calendar patient_birthday;
    private Integer doctor_id;
    private String doctor_name; // employee
    private String underlying_disease;

    public Result() {
    }

    public Result(Integer result_id, Integer appointment_id, String reminder, String diagnosis, Integer schedule_id, Integer patient_id, String patient_name, Calendar patient_birthday, Integer doctor_id, String doctor_name, String underlying_disease) {
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

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_birthday(Calendar patient_birthday) {
        this.patient_birthday = patient_birthday;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setUnderlying_disease(String underlying_disease) {
        this.underlying_disease = underlying_disease;
    }

    public Integer getResult_id() {
        return result_id;
    }

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public String getReminder() {
        return reminder;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public Calendar getPatient_birthday() {
        return patient_birthday;
    }

    public Integer getDoctor_id() {
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
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");

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
                Result r = new Result(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6),
                        result.getString(7), birthday, result.getInt(9), result.getString(10), result.getString(11));
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
    public void addResult(Result result) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "insert into result(result_id, appointment_id) values(result_id_sequence.nextval, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, result.getAppointment_id());
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void deleteResult(Integer result_id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";
            String sql = "delete result where result_id = " + result_id;
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
    public Result getAResult(Integer result_id) throws SQLException {
        Result result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select r.result_id, r.appointment_id, r.reminder, r.diagnosis, a.schedule_id, a.patient_id, p.full_name, p.birthday, e.employee_id, e.full_name, p.underlying_disease "
                + "from result r inner join appointment a on r.appointment_id = a.appointment_id "
                + "inner join patient p on a.patient_id = p.patient_id "
                + "inner join schedule s on a.schedule_id = s.schedule_id "
                + "inner join employee e on s.employee_id = e.employee_id where r.result_id = " +  String.valueOf(result_id));
            if (!resultSet.next()) {
                return result;
            }
            Calendar birthday = Calendar.getInstance(); birthday.setTimeInMillis(resultSet.getDate(8).getTime());
            result = new Result(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6),
                        resultSet.getString(7), birthday, resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11));
        } catch (ClassNotFoundException e) {
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    public void updateResult(Result updatedResult) throws SQLException {
        String sqlUpdate = "update result "
                + "set appointment_id = ? , "
                + "reminder = ? , "
                + "diagnosis = ? "
                + "where result_id = " + "?";
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
            statement.setInt(1, updatedResult.getAppointment_id());
            statement.setString(2, updatedResult.getReminder());
            statement.setString(3, updatedResult.getDiagnosis());
            statement.setInt(4, updatedResult.getResult_id());
            System.out.println(sqlUpdate);
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
