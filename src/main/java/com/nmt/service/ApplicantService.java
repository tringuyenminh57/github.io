/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.service;

import com.nmt.jobsite.HibernateUtil;
import com.nmt.pojo.Applicant;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tri Nguyen Minh
 */
public class ApplicantService {
    private final static SessionFactory factory = HibernateUtil.getFactory();
    
    public List<Applicant> getApplicant(){
        try( Session session = factory.openSession() ){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Applicant> query = builder.createQuery(Applicant.class);
            Root<Applicant> root = query.from(Applicant.class);
            query.select(root);
            
            return session.createQuery(query).getResultList();
        }
    }
    
    public Applicant getApplicantById(int appId){
        try(Session session = factory.openSession()){
            return session.get(Applicant.class, appId);
        }
    }
    
}
