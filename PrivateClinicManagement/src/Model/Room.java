/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */
public class Room {
    private long roomID;
    
    public Room(){}
    
    public Room(long roomID){
        this.roomID = roomID;
    }
    
    public long getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(long roomID){
        this.roomID = roomID;
    }
}
