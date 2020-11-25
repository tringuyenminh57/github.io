/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.service;

import com.nmt.jobsite.HibernateUtil;
import com.nmt.pojo.Admin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tri Nguyen Minh
 */
public class AdminService {
    private final static SessionFactory factory= HibernateUtil.getFactory();
    
    public boolean addUser(Admin u){
        try(Session session = factory.openSession()){
            try{
                session.getTransaction().begin();
                u.setPassword(DigestUtils.md5Hex(u.getPassword()));
                session.save(u);               
                session.getTransaction().commit();
                return true;
            }catch(Exception ex){
                session.getTransaction().rollback();      
            }
        }
        return false;
    }
    public boolean login(String username, String password){
        password= DigestUtils.md5Hex(password);
        try(Session session = factory.openSession()){
            CriteriaBuilder builder= session.getCriteriaBuilder();
            CriteriaQuery query= builder.createQuery(Admin.class);
            Root<Admin> root = query.from(Admin.class);
            query.select(root);
            query.where(builder.and(builder.equal(root.get("username").as(String.class),username),
                        builder.equal(root.get("password").as(String.class),password)));
            return session.createQuery(query).getSingleResult() !=null;
        }
    }
}
