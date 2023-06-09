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
import java.sql.Types;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author GIAHUY
 */
public class Unit {
    private Integer unitID;
    private String unitName;
    
    public Unit(){
        
    }
    public Unit(Integer unitID, String unitName){
        this.unitID = unitID;
        this.unitName  = unitName;
    }
    
    public void setUnitID(Integer unitID){
        this.unitID = unitID;
    }
    
    public Integer getUnitID(){
        return this.unitID;
    }
    
    public void setUnitName(String unitName){
        this.unitName  = unitName;
    }
    
    public String getUnitName(){
        return this.unitName;
    }
    
    public void getListOfUnit(List<Unit> listOfUnit){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery("select * from UNIT order by unit_id asc");

            while (result.next()){
                Unit p = new Unit(result.getInt(1), result.getString(2));
                listOfUnit.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void addUnit(Unit unit){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "insert into UNIT values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            statement.setInt(1, unit.getUnitID());
            statement.setString(2, unit.getUnitName());
            statement.executeUpdate();
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void deleteUnit(String unitID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "delete from UNIT where unit_id =" + unitID;
            Statement statement = connection.createStatement() ;  
            statement.executeUpdate(sql);
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void updateUnit(Unit updateUnit, Unit currentUnit){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "UPDATE UNIT "
                            + "SET "
                                + "UNIT_ID = ?, "
                                + "UNIT_NAME = ? "
                            + "WHERE "
                                + "UNIT_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            if(updateUnit.getUnitID() == currentUnit.getUnitID()){
                statement.setNull(1, Types.INTEGER);
            }
            else{
                statement.setInt(1, updateUnit.getUnitID());
            }
            
            if(updateUnit.getUnitName().equalsIgnoreCase(currentUnit.getUnitName())){
                statement.setNull(2, Types.NVARCHAR);
            }
            else{
                statement.setString(2, updateUnit.getUnitName());
            }
            
            statement.setInt(3, currentUnit.getUnitID());
            statement.executeUpdate();
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }      
    }
}
