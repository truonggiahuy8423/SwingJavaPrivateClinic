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
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class Service {
    private Long serviceID;
    private String serviceName;
    private Long serviceCost;
    
    public Service(){}
    
    public Service(Long serviceID, String serviceName, Long serviceCost){
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }
    
    public Long getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(Long serviceID, String serviceName, Long serviceCost){
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }
    
    public String getServiceName(){
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }
    
    public Long getServiceCost(){
        return this.serviceCost;
    }
    
    public void setServiceCost(Long serviceCost){
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
                Service p = new Service(result.getLong(1), result.getString(2), result.getLong(3));
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
            statement.setLong(1, service.getServiceID());
            statement.setString(2, service.getServiceName());
            statement.setLong(3, service.getServiceCost());

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
}
