/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Unit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UnitController {
    private Unit unitModel = new Unit();
    
    public UnitController(){}
    
    public void queryData(List<Unit> listOfUnit){
        unitModel.getListOfUnit(listOfUnit);
    }
    
    public void addUnit(Unit unit){
        unitModel.addUnit(unit);
    }
    
    public void deleteUnit(String unitID){
        unitModel.deleteUnit(unitID);
    }
    
    public void updateUnit(Unit updateUnit, Unit currentUnit){
        unitModel.updateUnit(updateUnit, currentUnit);
    }
}
