/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Calendar;

/**
 *
 * @author GIAHUY
 */
public class Appointment {
    private Long appointmentID;
    private Long scheduleID;
    private Long patientID;
    private Long doctorID;
    private String docterName;
    private String patientName;
    private Integer ordinalNumber;
    private Calendar date;
    private Long room;
    private String service;

    public Appointment() {
    }

    public Appointment(Long appointmentID, Long scheduleID, Long patientID, Long doctorID, String docterName, String patientName, Integer ordinalNumber, Calendar date, Long room, String service) {
        this.appointmentID = appointmentID;
        this.scheduleID = scheduleID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.docterName = docterName;
        this.patientName = patientName;
        this.ordinalNumber = ordinalNumber;
        this.date = date;
        this.room = room;
        this.service = service;
    }
    

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setScheduleID(Long scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public void setDocterName(String docterName) {
        this.docterName = docterName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public Long getScheduleID() {
        return scheduleID;
    }

    public Long getPatientID() {
        return patientID;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public String getDocterName() {
        return docterName;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public Long getRoom() {
        return room;
    }

    public String getService() {
        return service;
    }
    
}
