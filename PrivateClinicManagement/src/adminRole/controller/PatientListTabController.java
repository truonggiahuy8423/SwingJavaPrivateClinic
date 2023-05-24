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
import java.util.List;
/**
 *
 * @author GIAHUY
 */
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
                Patient p = new Patient(result.getLong(1), result.getString(2), result.getLong(3), result.getDate(4), result.getDate(5), result.getDate(6), result.getString(7), result.getString(8));
                listOfPatient.add(p);
            }
            connection.close();
        } catch (SQLException e)
        { return false; }
        catch (ClassNotFoundException e)
        { return false;}
        finally{ return true;}        
    }
    
}
