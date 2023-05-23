/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import javax.swing.JOptionPane;
import adminRole.view.MainView;

/**
 *
 * @author GIAHUY
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }
    
    public void login()
    {
        //check account trong database
        if (true)
        {
            // check account là Admin/Docter/Receptionist
            if (true)
            {
                this.view.dispose();
                adminRole.view.MainView mainViewforAdmin = new MainView();
                
                mainViewforAdmin.setVisible(true);
                JOptionPane.showMessageDialog(mainViewforAdmin, "Login successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (false)
            {}
            else
            {}
        }
        else
        {
            
        }
    }
    
}
