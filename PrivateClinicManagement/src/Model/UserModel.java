/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author GIAHUY
 */
public class UserModel {
    private String account;
    private String passwword;
    private Integer role;

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public UserModel(String account, String passwword, Integer role) {
        this.account = account;
        this.passwword = passwword;
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPasswword(String passwword) {
        this.passwword = passwword;
    }

    public String getPasswword() {
        return passwword;
    }
    
}
