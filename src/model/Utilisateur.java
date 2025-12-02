/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Original Computer
 */
public class Utilisateur {

    private int id;
    private String email;
    private String password;

    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;

        
    }
    
    public Utilisateur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
