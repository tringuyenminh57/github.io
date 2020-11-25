/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.bean;

import com.nmt.pojo.Applicant;
import com.nmt.service.ApplicantService;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;

/**
 *
 * @author Tri Nguyen Minh
 */

@Named(value = "profileBean")
@ManagedBean
@RequestScoped
public class applicantBean implements Serializable {
    private final static ApplicantService proService = new ApplicantService();

    /**
     * Creates a new instance of ProfileBean
     */
    public applicantBean() {
    }
    
    public List<Applicant> getApplicant(){
        return proService.getApplicant();
    }
    
}
