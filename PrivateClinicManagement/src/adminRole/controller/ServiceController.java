/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Service;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ServiceController {
    private Service serviceModel = new Service();
    public ServiceController(){}
    public void queryData(List<Service> listOfService){
        serviceModel.getListOfService(listOfService);
    }
    
    public void addService(Service service){
        serviceModel.addService(service);
    }
    
    public void deleteService(String serviceID){
        serviceModel.deleteService(serviceID);

    }
}
