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
import java.sql.Types;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author GIAHUY
 */
public class Room {
    private Integer roomID;
    
    public Room(){}
    
    public Room(Integer roomID){
        this.roomID = roomID;
    }
    
    public Integer getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(Integer roomID){
        this.roomID = roomID;
    }
    
    public void getListOfRoom(List<Room> listOfRoom){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");

            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery("select * from room order by room_id asc");

            while (result.next()){
                Room p = new Room(result.getInt(1));
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
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");

            String sql = "insert into room values(?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            statement.setInt(1, room.getRoomID());
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
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
    
    public void updateRoom(Room updateRoom, Room currentRoom){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "UPDATE ROOM "
                            + "SET ROOM_ID = " + "?"
                            + " WHERE ROOM_ID = "+ "?";
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            if(updateRoom.getRoomID() == currentRoom.getRoomID()){
                statement.setNull(1, Types.INTEGER);
            }
            else{
                statement.setInt(1, updateRoom.getRoomID());
            }
            
            statement.setInt(2, currentRoom.getRoomID());
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
