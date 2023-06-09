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

/**
 *
 * @author GIAHUY
 */
public class Service {
    private Integer serviceID;
    private String serviceName;
    private Integer serviceCost;
    
    public Service(){}
    
    public Service(Integer serviceID, String serviceName, Integer serviceCost){
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(Integer serviceID){
        this.serviceID = serviceID;
    }
    
    public String getServiceName(){
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }
    
    public Integer getServiceCost(){
        return this.serviceCost;
    }
    
    public void setServiceCost(Integer serviceCost){
        this.serviceCost = serviceCost;
    }
    

    
    public void getListOfService(List<Service> listOfService){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery("select * from SERVICE order by service_id asc");

            while (result.next()){
                Service p = new Service(result.getInt(1), result.getString(2), result.getInt(3));
                listOfService.add(p);
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
    
    public void addService(Service service){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "insert into SERVICE values(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql) ;  
            statement.setInt(1, service.getServiceID());
            statement.setString(2, service.getServiceName());
            statement.setInt(3, service.getServiceCost());

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
    
    public void deleteService(String serviceID){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "delete from SERVICE where service_id =" + serviceID;
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
    
    public void updateService(Service updateService, Service currentService){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
            String sql = "UPDATE SERVICE "
                            + "SET "
                                + "SERVICE_ID = ?, "
                                + "SERVICE_NAME = ?, "
                                + "COST = ? "
                            + "WHERE "
                                + "SERVICE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            if(updateService.getServiceID() == currentService.getServiceID()){
                statement.setNull(1, Types.INTEGER);
            }
            else{
                statement.setInt(1, updateService.getServiceID());
            }
            
            if(updateService.getServiceName().equalsIgnoreCase(currentService.getServiceName())){
                statement.setNull(2, Types.NVARCHAR);
            }
            else{
                statement.setString(2, updateService.getServiceName());
            }
            
            if(!(updateService.getServiceCost() == currentService.getServiceCost())){
                statement.setNull(3, Types.INTEGER);
            }
            else{
                statement.setInt(3, updateService.getServiceCost());
            }
            
            statement.setInt(4, currentService.getServiceID());
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
