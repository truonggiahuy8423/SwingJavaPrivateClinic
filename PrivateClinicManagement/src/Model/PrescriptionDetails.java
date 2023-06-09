/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
/**
 *
 * @author GIAHUY
 */
public class PrescriptionDetails {
    private Integer result_id;
    private Integer medicine_id;
    private String name;
    private String description;
    
    private String unit;
    private Integer quantity;
    private String instruction;

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    
    public Integer getResult_id() {
        return result_id;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getInstruction() {
        return instruction;
    }
    
    public PrescriptionDetails() {
    }
    
    public PrescriptionDetails(Integer result_id, Integer medicine_id, String name, String description, String unit, int quantity, String instruction) {
        this.result_id = result_id;
        this.medicine_id = medicine_id;
        this.description = description;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.instruction = instruction;
    }
    
    public void getListOfPresc(String sql, List<PrescriptionDetails> listOfPresc) throws SQLException {
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
            // m.medicine_id, rd.result_id, m.medicine_name, m.description, rd.quantity, u.unit_name, rd.instruction
            while (result.next()) {
                PrescriptionDetails pre = new PrescriptionDetails(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(6), result.getInt(5), result.getString(7));
                listOfPresc.add(pre);
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
    public void deleltePresc(Integer result_id, Integer medicine_id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";
            String sql = "delete RESULT_DETAIL where result_id = " + result_id.toString() + " and medicine_id = " + medicine_id.toString();
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");

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
    public void addPresc(PrescriptionDetails presc) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");

            String sql = "insert into result_detail(result_id, medicine_id, quantity, instruction) values(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, presc.getResult_id());
            statement.setInt(2, presc.getMedicine_id());
            statement.setInt(3, presc.getQuantity());
            statement.setString(4, presc.getInstruction());

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
    
}
