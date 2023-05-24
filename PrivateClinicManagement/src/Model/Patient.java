/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */import java.time.LocalDate;
import java.util.Date;
public class Patient {
    private long patientId;
    private String fullname;
    private long phone;
    private Date birthday;
    private Date registrationDay;
    private Date insuranceExpiration;
    private String address;
    private String underlyingDisease;
    public Patient(){}
    public Patient(long patientId, String fullname, long phone, Date birthday, Date registrationDay, Date insuranceExpiration, String address, String underlyingDisease) {
        this.patientId = patientId;
        this.fullname = fullname;
        this.phone = phone;
        this.birthday = birthday;
        this.registrationDay = registrationDay;
        this.insuranceExpiration = insuranceExpiration;
        this.address = address;
        this.underlyingDisease = underlyingDisease;
    }

    // Getters and Setters for the properties

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDay() {
        return registrationDay;
    }

    public void setRegistrationDay(Date registrationDay) {
        this.registrationDay = registrationDay;
    }

    public Date getInsuranceExpiration() {
        return insuranceExpiration;
    }

    public void setInsuranceExpiration(Date insuranceExpiration) {
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