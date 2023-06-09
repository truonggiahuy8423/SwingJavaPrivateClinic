/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

//import adminRole.controller.UnitController;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private Integer unitID;
    private String medicineName;
    private String unit;
    private String description;

    public void setMedicineID(Integer medicineID) {
        this.medicineID = medicineID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMedicineID() {
        return medicineID;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }
    
    public Medicine(){
        
    }
    public Medicine(Integer medicineID, /*String medicineImage,*/ String medicineName, String unit, String description){
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.unit = unit;
        this.description = description;
    }
    public Medicine(Integer medicineID, String medicineName, String decription, Integer unitID, String unit) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.description = decription;
        this.unitID = unitID;
        this.unit = unit;
    }


    public void getListOfMedicine_(String sql, List<Medicine> listOfMedicine) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "AD";  // Replace with your username
        String password = "88888888";  // Replace with your password
        connection = null; statement = null; result = null;
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
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
    public void getListOfMedicine(String sql, List<Medicine> listOfMedicine){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                Medicine p = new Medicine(result.getInt(1), result.getString(2),  result.getString(3),  result.getString(4));
                listOfMedicine.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);
        }
        finally{
            System.out.println("Successful"); 
        }
    }

    public void addMedicine(Medicine addMedicine, Integer unitID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
 
            String sql = "INSERT INTO MEDICINE VALUES (medicine_id_sequence.nextval, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            
            statement.setString(1,addMedicine.getMedicineName());
            statement.setString(2, addMedicine.getDescription());
            statement.setInt(3, unitID);
            
            statement.executeUpdate();
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void updateMedicine(Medicine updateMedicine, Medicine currentMedicine, Integer unitID){
        String sql = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            sql = "UPDATE MEDICINE SET ";
            Statement statement = connection.createStatement() ;
            boolean check = false;
            
            if(!updateMedicine.getMedicineName().equals(currentMedicine.getMedicineName())){
                if(check) sql += ", ";
                sql += "MEDICINE_NAME = \'" + String.valueOf(updateMedicine.getMedicineName()) + "\'";
                check = true;
            }
            
            if(!updateMedicine.getUnit().equals(currentMedicine.getUnit())){
                if(check) sql += ", ";
                sql += "UNIT_ID = " + String.valueOf(unitID);
                check = true;
            }
            
            if(!updateMedicine.getDescription().equals(currentMedicine.getDescription())){
                if(check) sql += ", ";
                sql += "DESCRIPTION = \'" + String.valueOf(updateMedicine.getDescription()) + "\'";
                check = true;
            }
            
            sql += " WHERE MEDICINE_ID = " + String.valueOf(currentMedicine.getMedicineID());
            statement.executeUpdate(sql);
            connection.close();
            
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc tên của đối tượng muốn tìm.");
        }
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc chọn 1 đối tượng.");
        }
        finally{
            System.out.println("Successful"); 
        }   
    }
    
    public void deleteMedicine(String medicineID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "DELETE FROM MEDICINE WHERE MEDICINE_ID = " + medicineID;
            Statement statement = connection.createStatement() ;
            statement.executeUpdate(sql);
            connection.close();
        } 
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc chọn 1 đối tượng.");
        }
        catch (SQLException | ClassNotFoundException e){
//            JOptionPane.showMessageDialog(null, e.toString());
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc chọn 1 đối tượng.");
        }
        finally{
            System.out.println("Successful"); 
        }
    }
    
    public  void searchMedicine(Medicine searchMedicine, List<Medicine> listSearchMedicine){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "SELECT * FROM MEDICINE "
                            + "WHERE "
                                + "MEDICINE_ID = " + " ? " + " OR "
                                + "MEDICINE_NAME = " + " ? " ;
            PreparedStatement statement = connection.prepareStatement(sql) ;
            
            if(searchMedicine.getMedicineID() == null){
                statement.setNull(1, java.sql.Types.INTEGER);
            }
            else{
                statement.setInt(1, searchMedicine.getMedicineID());
            }
            
            statement.setString(2, searchMedicine.getMedicineName());
            
            ResultSet result = statement.executeQuery();
            String unitName = "";
            //UnitController unitController = new UnitController();
            List<Unit>listOfUnit = new ArrayList<>();
            //unitController.queryData(listOfUnit);

            
//            while (result.next()){
//                for(Unit u: listOfUnit){
//                    if(u.getUnitID() == result.getInt(4)){
//                        unitName += u.getUnitName();
//                    }
//                }
//                Medicine p = new Medicine(result.getInt(1), result.getString(2), unitName, result.getString(3));
//                listSearchMedicine.add(p);
//            }
            connection.close();
        } 
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc tên của đối tượng muốn tìm.");
        }
        
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());
//                JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc tên của đối tượng muốn tìm.");
        }
        finally{
            System.out.println("Successful"); 
        }
    }
}
