/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */
public class Service {
    private long serviceID;
    
    public Service(){}
    
    public Service(long serviceID){
        this.serviceID = serviceID;
    }
    
    public long getServiceID(){
        return this.serviceID;
    }
    
    public void setServiceID(long serviceID){
        this.serviceID = serviceID;
    }
}
