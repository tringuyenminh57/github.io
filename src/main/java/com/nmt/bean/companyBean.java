/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.bean;

import com.nmt.pojo.Company;
import com.nmt.service.CompanyService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;

/**
 *
 * @author Tri Nguyen Minh
 */
@ManagedBean
@Named(value = "companyBean")
@SessionScoped
public class companyBean implements Serializable {
    private final static CompanyService comService = new CompanyService();
    /**
     * Creates a new instance of companyBean
     */
    public companyBean() {
    }
    
    public List<Company> getCompanies(){
        return comService.getCompanies();
    }
    
}
