/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doctorRole.controller;
import adminRole.controller.*;
import adminRole.view.MainView;
import Login.LoginView;
import doctorRole.view.DoctorMainView;
/**
 *
 * @author GIAHUY
 */
public class DoctorMainViewController {
    private DoctorMainView view;
    public DoctorMainViewController(DoctorMainView view) {
        this.view = view;
    }
    public void logout()
    {
        this.view.dispose();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}
