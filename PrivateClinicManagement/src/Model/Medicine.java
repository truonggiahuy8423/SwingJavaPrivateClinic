/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
/**
 *
 * @author GIAHUY
 */
public class Medicine {
    private Integer medicineID;
    private String name;
    private String decription;
    private Integer unitID;
    private String unitName;

    public Medicine(Integer medicineID, String name, String decription, Integer unitID, String unitName) {
        this.medicineID = medicineID;
        this.name = name;
        this.decription = decription;
        this.unitID = unitID;
        this.unitName = unitName;
    }
    public Medicine() {
        
    }
    public void getListOfMedicine(String sql, List<Medicine> listOfMedicine) throws SQLException {
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
                //m.medicine_id, m.medicine_name, m.description, u.unit_id, u.unit_name
                Medicine m = new Medicine(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5));
                listOfMedicine.add(m);
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
    public void setMedicineID(Integer medicineID) {
        this.medicineID = medicineID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getMedicineID() {
        return medicineID;
    }

    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public String getUnitName() {
        return unitName;
    }
    
    
}
