/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.bean;

import com.nmt.pojo.Company;
import com.nmt.pojo.Job;
import com.nmt.service.JobService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Tri Nguyen Minh
 */
@ManagedBean
@Named(value = "jobBean")
@RequestScoped
public class jobBean implements Serializable{
    private int id;
    private String title;
    private String description;
    private Part imgFile;
    private int jobType;
    private float salary;
    private int vacancyNum;
    private Set<Company> company;
    
    private static JobService jobService = new JobService();
    /**
     * Creates a new instance of jobBean
     */
    public jobBean() {
        if(FacesContext.getCurrentInstance().isPostback()){
            String jobId = FacesContext.getCurrentInstance()
                            .getExternalContext().
                            getRequestParameterMap().get("job_id");
            if(jobId !=null && jobId.isEmpty()){
                Job j = jobService.getJobByID(Integer.parseInt(jobId));
                this.id=j.getId();
                this.title=j.getTitle();
                this.description=j.getDescription();
                //this.company=j.getCompany();
                this.salary=j.getSalary();
                this.vacancyNum=j.getVacancyNum();
            }
        }
    }
    public List<Job> getJob(){
        List<Job> job= getJobService().getJob(null);
        return job;
    }
    
    public String addJob(){
        Job j;
        if(this.id > 0)
            j= jobService.getJobByID(this.id);
        else
            j= new Job();
        j.setTitle(this.title);
        j.setDescription(this.description);
        j.setJobType(this.jobType);
        j.setSalary(this.salary);
        j.setVacancyNum(this.vacancyNum);
        //j.setCompany(this.company);
        
        
        try {
            if (this.imgFile !=null){
              this.uploadFile();
              j.setImage("upload/" + this.imgFile.getSubmittedFileName());  
            }                      
        } catch (IOException ex) {
            Logger.getLogger(jobBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(jobService.addJob(j)==true)
            return "index?faces-redirect=true";
        return "jobpost?faces-redirect=true";
    }
    
    public String deleteJob(Job j) throws Exception{
        if(jobService.deleteJob(j))
            return "succesful";
        throw new Exception("somthing wrong");
    }
    
    
    
    private void uploadFile() throws IOException{
//        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/upload")
//                    +"/" + this.imgFile.getSubmittedFileName();
        String path = FacesContext.getCurrentInstance()
                                  .getExternalContext()
                                  .getInitParameter("com.nmt.uploadPath") 
                                  + this.imgFile.getSubmittedFileName();
        try(InputStream in = this.imgFile.getInputStream();
                FileOutputStream out = new FileOutputStream(path)){
            byte[] b= new byte[1024];
            int byteRead;
            while((byteRead = in.read(b))!=-1)
                out.write(b,0,byteRead);
        }
        
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
    public Set<Company> getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Set<Company> company) {
        this.company = company;
    }

    /**
     * @return the jobService
     */
    public static JobService getJobService() {
        return jobService;
    }

    /**
     * @param aJobService the jobService to set
     */
    public static void setJobService(JobService aJobService) {
        jobService = aJobService;
    }

    /**
     * @return the imgFile
     */
    public Part getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }

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
