/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Tri Nguyen Minh
 */
@Entity
@Table (name = "job")
public class Job implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String image;
    private int jobType;
    private float salary;
    private int vacancyNum;
        
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private Set<Applicant> applicant;
    
    @ManyToMany
    @JoinTable(
            name= "application_details",
            joinColumns = {
                @JoinColumn(name= "job_id")
            },
            inverseJoinColumns={
                @JoinColumn(name = "applicant_id")
            }
    )
    
    
    @ManyToOne
    @JoinColumn (name = "job_category_id")
    private JobCate jobcate;
    
    @ManyToOne
    @JoinColumn (name = "job_location_id")
    private JobLocate joblocate;

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

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the jobType
     */
    public int getJobType() {
        return jobType;
    }

    /**
     * @param jobType the jobType to set
     */
    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    /**
     * @return the salary
     */
    public float getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * @return the vacancyNum
     */
    public int getVacancyNum() {
        return vacancyNum;
    }

    /**
     * @param vacancyNum the vacancyNum to set
     */
    public void setVacancyNum(int vacancyNum) {
        this.vacancyNum = vacancyNum;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the jobcate
     */
    public JobCate getJobcate() {
        return jobcate;
    }

    /**
     * @param jobcate the jobcate to set
     */
    public void setJobcate(JobCate jobcate) {
        this.jobcate = jobcate;
    }

    /**
     * @return the joblocate
     */
    public JobLocate getJoblocate() {
        return joblocate;
    }

    /**
     * @param joblocate the joblocate to set
     */
    public void setJoblocate(JobLocate joblocate) {
        this.joblocate = joblocate;
    }

    /**
     * @return the applicant
     */
    public Set<Applicant> getApplicant() {
        return applicant;
    }

    /**
     * @param applicant the applicant to set
     */
    public void setApplicant(Set<Applicant> applicant) {
        this.applicant = applicant;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
   
    
    
    
}
