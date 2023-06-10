/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package receptionistRole.controller;
import doctorRole.controller.*;
import adminRole.controller.*;
import adminRole.view.MainView;
import Login.LoginView;
import doctorRole.view.DoctorMainView;
import receptionistRole.view.ReceptionistMainView;
/**
 *
 * @author GIAHUY
 */
public class ReceptionistMainViewController {
    private ReceptionistMainView view;
    public ReceptionistMainViewController(ReceptionistMainView view) {
        this.view = view;
    }
    public void logout()
    {
        this.view.dispose();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}
