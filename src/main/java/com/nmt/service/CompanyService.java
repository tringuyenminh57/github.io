/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.service;

import com.nmt.jobsite.HibernateUtil;
import com.nmt.pojo.Company;
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
public class CompanyService {
    private final static SessionFactory factory = HibernateUtil.getFactory();
    
    public List<Company> getCompanies(){
        try( Session session = factory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Company> query = builder.createQuery(Company.class);
            Root<Company> root = query.from(Company.class);
            query.select(root);
            
            return session.createQuery(query).getResultList();
            
        }
    }
    public Company getCompanyById(int comId){
        try(Session session = factory.openSession()){
            return session.get(Company.class, comId);
        }
        
    }
}
