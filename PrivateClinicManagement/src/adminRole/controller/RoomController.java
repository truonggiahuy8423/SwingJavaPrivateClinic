/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Room;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RoomController {
    private Room roomModel = new Room();
    public RoomController(){}
    public void queryData(List<Room> listOfRoom){
        roomModel.getListOfRoom(listOfRoom);
    }
    
    public void addRoom(Room room){
        roomModel.addRoom(room);
    }
    
    public void deleteRoom(String roomID){
        roomModel.deleteRoom(roomID);
    }
}
