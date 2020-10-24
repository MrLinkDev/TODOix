package ru.link.todoix.List;

import org.hibernate.*;
import org.springframework.stereotype.Service;
import ru.link.todoix.HibernateSessionFactory;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListDAO<ListEntity>{

    public void create(ListEntity listEntity){
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        //session.beginTransaction();
        //session.save(listEntity);
        //session.getTransaction().commit();
        //session.close();
    }

    //p/ublic List<ListEntity> getAll(){
        //List<ListEntity> items = HibernateSessionFactory.getSessionFactory().openSession().createQuery("FROM ListEntity ").list();
        //return items;
    //}

}
