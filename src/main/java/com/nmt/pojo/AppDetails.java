/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.pojo;

import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tri Nguyen Minh
 */
public class AppDetails {
    private int id;
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
