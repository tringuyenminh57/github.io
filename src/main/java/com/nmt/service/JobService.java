/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.service;

import com.nmt.jobsite.HibernateUtil;
import com.nmt.pojo.Applicant;
import com.nmt.pojo.Job;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tri Nguyen Minh
 */
public class JobService {
    private final static SessionFactory factory = HibernateUtil.getFactory();
    
    public List<Job> getJob(String kw){
        try( Session session = factory.openSession() ){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Job> query = builder.createQuery(Job.class);
            Root<Job> root = query.from(Job.class);
            query.select(root);
            
            if (kw !=null && !kw.isEmpty()){
                String j = String.format("%%%s%%", kw);
                Predicate j1= builder.like(root.get("title").as(String.class),j);
                Predicate j2= builder.like(root.get("description").as(String.class),j);
                
                query = query.where(builder.or(j1,j2));
                
            }
            return session.createQuery(query).getResultList();
        }
    }
    public boolean addJob(Job j){
        try(Session session = factory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(j);
                session.getTransaction().commit();
            }catch( Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public boolean deleteJob(Job j){
        try(Session session = factory.openSession()){
            try{
                session.getTransaction().begin();
                session.delete(j);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }           
        }
        return true;
    }
    
    public Job getJobByID(int jobId){
        try(Session session = factory.openSession()){
            return session.get(Job.class,jobId);
        }
    }
}
