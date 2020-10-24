package ru.link.todoix;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    public SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addResource("weather.hbm.xml");
        return configuration.buildSessionFactory();
    }

}
