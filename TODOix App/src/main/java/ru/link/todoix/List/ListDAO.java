package ru.link.todoix.List;

import org.hibernate.*;
import org.springframework.stereotype.Service;
import ru.link.todoix.HibernateSessionUtil;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListDAO {

    public void create(ListItem listItem){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(listItem);
        transaction.commit();
        session.close();
    }

    public List<ListItem> getAll(){
        List<ListItem> items = HibernateSessionUtil.getSessionFactory().openSession().createQuery("FROM ListItem ").list();
        return items;
    }

}
