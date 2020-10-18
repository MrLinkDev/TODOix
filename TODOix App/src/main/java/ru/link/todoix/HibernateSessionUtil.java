package ru.link.todoix;

import jdk.jfr.StackTrace;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.link.todoix.List.ListItem;

public class HibernateSessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(ListItem.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
