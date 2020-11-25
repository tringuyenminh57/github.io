/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.jobsite;
import com.nmt.pojo.Admin;
import com.nmt.pojo.Applicant;
import com.nmt.pojo.Company;
import com.nmt.pojo.Job;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


/**
 *
 * @author Tri Nguyen Minh
 */
public class HibernateUtil {
    private final static SessionFactory FACTORY;
    
    static{
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        props.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL,"jdbc:mysql://localhost:3306/jobportaldb");
        props.put(Environment.USER,"root");
        props.put(Environment.PASS,"123456");
        conf.setProperties(props);
        
        conf.addAnnotatedClass(Job.class);
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(Applicant.class);
        conf.addAnnotatedClass(Admin.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties())
                .build();
        
        FACTORY = conf.buildSessionFactory(registry);
        
    }

    /**
     * @return the FACTORY
     */
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static SessionFactory getFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
