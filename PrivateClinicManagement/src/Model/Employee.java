/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */
public class Employee {
    private Long employeeID;
    private String name;
    private String phone;
    
    public Employee(){}
    
    public Employee(Long employeeID, String name, String phone){
        this.employeeID = employeeID;
        this.name = name;
         this.phone = phone;
    }
    
    public Long getEmployeeID(){
        return this.employeeID;
    }
    
    public void setEmployeeID(Long employeeID){
        this.employeeID = employeeID;
    }    

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return this.phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
}
