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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author GIAHUY
 */
public class Attendance {
    private Integer attendanceID;
    private Date attendDate;
    private Integer employeeID;
    private String employeeName;
    public Attendance(){
        
    }
    
    public Attendance(Integer attendanceID, Date attendanceDate, Integer employeeID, String employeeName){
        this.attendanceID = attendanceID;
        this.attendDate = attendanceDate;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }
    
    public Integer getAttendanceID(){
        return this.attendanceID;
    }
    
    public void setAttendanceID(Integer attendanceID){
        this.attendanceID = attendanceID;
    }
    
    public Date getAttendDate(){
        return this.attendDate;
    }
    
    public void setAttendanceDate(Date attendanceDate){
        this.attendDate = attendanceDate;
    }
    
    public Integer getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID( Integer employeeID){
        this.employeeID = employeeID;
    }
    
    public String getEmployeeName(){
        return this.employeeName;
    }
    
    public void setEmployeeName(String employeeName){
        this.employeeID = employeeID;
    }
    
    public void getListOfAttendance(String sql, List<Attendance> listOfAttendance){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);
            System.out.println(sql);
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Attendance p = new Attendance(result.getInt(1), utilDate, result.getInt(3), result.getString(4));
                listOfAttendance.add(p);
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
    
    public void addAttendance(Attendance addAttendance){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "INSERT INTO ATTENDANCE VALUES(attendance_id_sequence.nextval, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;

            statement.setDate(1, new java.sql.Date(addAttendance.getAttendDate().getTime()));
            statement.setInt(2,addAttendance.getEmployeeID());
            statement.executeUpdate();
            connection.close();
            System.out.println(sql);
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void deleteAttendance(String attendanceID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "DELETE FROM ATTENDANCE WHERE ATTENDANCE_ID = " + attendanceID;
            Statement statement = connection.createStatement() ;
            statement.executeUpdate(sql);
            connection.close();
//                System.out.println(sql);
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    
    public void updateAttendance(Attendance updateAttendance, Attendance currentAttendance){
        String sql = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            sql = "UPDATE ATTENDANCE SET ";
            Statement statement = connection.createStatement() ;
            boolean check = false;
            
            if(updateAttendance.getAttendDate().compareTo(currentAttendance.getAttendDate()) != 0){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                String dateString = sdf.format(updateAttendance.getAttendDate());
                sql += "ATTENDANCE_DATE = \'" + dateString + "\' ";
                check = true;
            }
            
            if(updateAttendance.getEmployeeID() != currentAttendance.getEmployeeID()){
                if(check) sql += ", ";
                sql += "EMPLOYEE_ID = " + String.valueOf(updateAttendance.getEmployeeID());
            }
            
            sql += " WHERE ATTENDANCE_ID = " + String.valueOf(updateAttendance.getAttendanceID());
            statement.executeUpdate(sql);
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
                System.out.println(sql);
                JOptionPane.showMessageDialog(null, e.toString() + "\n" + sql);

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
    public void searchAttendance(Attendance searchAttendance, List<Attendance> listSearchAttendance){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "SELECT a.ATTENDANCE_ID, a.ATTEND_DATE, a.EMPLOYEE_ID, e.FULL_NAME FROM ATTENDANCE a, EMPLOYEE e"
                            + "WHERE a.EMPLOYEE_ID = e.EMPLOYEE_ID AND ("
                                    + "ATTENDANCE_ID = " + "?"  +" OR "
                                    + "ATTEND_DATE = " + "?" + " OR "
                                    + "EMPLOYEE_ID = " + "?" + ")" ;
                                    
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            if(searchAttendance.getAttendanceID() == null ){
                statement.setNull(1, Types.INTEGER);
            }
            else{
                statement.setInt(1, searchAttendance.getAttendanceID());
            }
            
            if(searchAttendance.getAttendDate() == null ){
                statement.setNull(2, Types.DATE);
            }
            else{
                statement.setDate(2, new java.sql.Date(searchAttendance.getAttendDate().getTime()));
            }
            
            if(searchAttendance.getEmployeeID() == null ){
                statement.setNull(3, Types.INTEGER);
            }
            else{
                statement.setInt(3, searchAttendance.getEmployeeID());
            }
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()){
                java.util.Date  utilDate = new java.util.Date(result.getDate(2).getTime());
                Attendance p = new Attendance(result.getInt(1), utilDate, result.getInt(3), result.getString(4));
                listSearchAttendance.add(p);
            }
            connection.close();
//                System.out.println(sql);
        } 
        catch (SQLException | ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, e.toString());

        }
        finally{
            System.out.println("Successful"); 
        }        
    }
}
