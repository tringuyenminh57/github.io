/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.bean;

import com.nmt.pojo.Admin;
import com.nmt.service.AdminService;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Transient;

/**
 *
 * @author Tri Nguyen Minh
 */
@Named(value = "admin")
@RequestScoped
public class adminBean {
    private String name;
    private String email;
    private String username;
    private String password;
    @Transient
    private String confirmPwd;
    private static AdminService adminService = new AdminService();
    
    public String registry(){
        if(!this.password.isEmpty() && this.password.equals(this.confirmPwd)){
            Admin u= new Admin();
            u.setName(name);
            u.setEmail(email);
            u.setUsername(username);
            u.setPassword(password);
            if(adminService.addUser(u)==true){
                return "index?faces-redirect=true";
            }            
        }
        return "registry";
    }

    /**
     * Creates a new instance of admin
     */
    public adminBean() {
    }
    
}
