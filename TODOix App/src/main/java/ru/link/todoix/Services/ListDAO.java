package ru.link.todoix.Services;

import org.hibernate.Session;
import ru.link.todoix.HibernateSessionFactory;
import ru.link.todoix.Objects.*;

import java.util.UUID;

public class ListDAO implements ListDAOImpl{
    @Override
    public void create(ListDTO listDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Converter.ListDTOToEntity(listDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ListDTO findById(UUID id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ListEntity listEntity = session.find(ListEntity.class, id);
        session.close();

        return Converter.ListEntityToDTO(listEntity);
    }

    @Override
    public void update(ListDTO listDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(Converter.ListDTOToEntity(listDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(UUID id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.find(ListEntity.class, id));
        session.getTransaction().commit();
        session.close();
    }
}
