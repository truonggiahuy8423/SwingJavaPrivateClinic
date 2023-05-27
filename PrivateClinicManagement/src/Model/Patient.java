/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
public class Patient {
    private Long patientId;
    private String fullname;
    private String phone;
    private Calendar birthday;
    private Calendar registrationDay;
    private Calendar insuranceExpiration;
    private String address;
    private String underlyingDisease;
    public Patient(){}
    public Patient(Long patientId, String fullname, String phone, Calendar birthday, Calendar registrationDay, Calendar insuranceExpiration, String address, String underlyingDisease) {
        this.patientId = patientId;
        this.fullname = fullname;
        this.phone = phone;
        this.birthday = birthday;
        this.registrationDay = registrationDay;
        this.insuranceExpiration = insuranceExpiration;
        this.address = address;
        this.underlyingDisease = underlyingDisease;
        //birthday.setLenient(false);
        //registrationDay.setLenient(false);
        //insuranceExpiration.setLenient(false);
    }

    // Getters and Setters for the properties
    
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Calendar getRegistrationDay() {
        return registrationDay;
    }

    public Calendar getInsuranceExpiration() {
        return insuranceExpiration;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setRegistrationDay(Calendar registrationDay) {
        this.registrationDay = registrationDay;
    }

    public void setInsuranceExpiration(Calendar insuranceExpiration) {
        this.insuranceExpiration = insuranceExpiration;
    }
    
    
   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnderlyingDisease() {
        return underlyingDisease;
    }

    public void setUnderlyingDisease(String underlyingDisease) {
        this.underlyingDisease = underlyingDisease;
    }
    
}