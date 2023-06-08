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
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author GIAHUY
 */
public class Room {
    private Long roomID;
    
    public Room(){}
    
    public Room(Long roomID){
        this.roomID = roomID;
    }
    
    public Long getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(Long roomID){
        this.roomID = roomID;
    }
    
    public void getListOfRoom(List<Room> listOfRoom){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery("select * from room order by room_id asc");

            while (result.next()){
                Room p = new Room(result.getLong(1));
                listOfRoom.add(p);
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
    
    public void addRoom(Room room){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            String sql = "insert into room values(?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            statement.setLong(1, room.getRoomID());
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
    
    public void deleteRoom(String roomID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            String sql = "delete from room where room_id =" + roomID;
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
}
