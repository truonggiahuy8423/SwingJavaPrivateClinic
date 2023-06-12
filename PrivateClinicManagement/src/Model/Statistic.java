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

/**
 *
 * @author GIAHUY
 */

public class Statistic {
    private String convert_calendar2(Calendar c)
    {
        return c == null ? "'----/--/--'" : "" + String.format("'%04d", c.get(Calendar.YEAR)) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "-"+ String.format("%02d'", c.get(Calendar.DATE));

    }
    public Integer getNumberOfNewPatients(Calendar begin, Calendar end) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "select count(*) from patient p where p.REGISTRATION_DAY >= DATE " + convert_calendar2(begin) + " and p.REGISTRATION_DAY <= DATE " + convert_calendar2(end);
        int numberOfnewPatients = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "UNI4";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                numberOfnewPatients = result.getInt(1);
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
        return numberOfnewPatients;
    }
    public Integer getNumberOfNewAppointments(Calendar begin, Calendar end)throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "select count(*) from schedule s inner join appointment a on s.schedule_id = a.schedule_id where s.schedule_date >= DATE " + convert_calendar2(begin) + " and s.schedule_date <= DATE " + convert_calendar2(end);
        int numberOfnewAppointments = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "UNI4";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                numberOfnewAppointments = result.getInt(1);
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
        return numberOfnewAppointments;
    }
    public Long getRevenue(Calendar begin, Calendar end) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "select sum(a.fee) from schedule s inner join appointment a on s.schedule_id = a.schedule_id where s.schedule_date >= DATE " + convert_calendar2(begin) + " and s.schedule_date <= DATE " + convert_calendar2(end);
        long revenue = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "UNI4";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                revenue = result.getInt(1);
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
        return revenue;
    }
}
