/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author sun sun
 */
public class RoleController {
    public RoleController(){}
//    public void queryData(String sql, List<Role> listOfRole){
//        try{
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##uni4", "123");
////            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
//            Statement statement = connection.createStatement() ;  
//            ResultSet result = statement.executeQuery(sql);
//
//            while (result.next()){
//                Role p = new Role(result.getLong(1));
//                listOfRole.add(p);
//            }
//            connection.close();
//        } 
//        catch (SQLException | ClassNotFoundException e){
//            System.out.println(e.toString()); 
//        }
//        finally{
//            System.out.println("Successful"); 
//        }        
//    }   
}
