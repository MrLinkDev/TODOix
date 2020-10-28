package ru.link.todoix.Services;

import org.hibernate.*;
import ru.link.todoix.HibernateSessionFactory;
import ru.link.todoix.Objects.*;

import javax.persistence.criteria.*;
import java.util.*;

public class ListDAO implements ListDAOImpl{
    @Override
    public void create(ListDTO listDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Converter.DTOToEntity(listDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ListDTO findById(UUID id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ListEntity listEntity = session.find(ListEntity.class, id);
        session.close();

        return Converter.entityToDTO(listEntity);
    }

    @Override
    public void update(ListDTO listDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(Converter.DTOToEntity(listDTO));
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

    @Override
    public List<ListDTO> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(ListEntity.class);
        criteriaQuery.from(ListEntity.class);

        return Converter.ListEntityToDTO(session.createQuery(criteriaQuery).getResultList());
    }

    @Override
    public List<ListDTO> getPage(int page, int size, String sortBy) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(ListEntity.class);
        Root<ListEntity> listEntityRoot = criteriaQuery.from(ListEntity.class);
        criteriaQuery.orderBy(builder.asc(listEntityRoot.get(sortBy)));

        return (List<ListDTO>)Converter.ListEntityToDTO(
                session.createQuery(criteriaQuery)
                        .setFirstResult(size * page)
                        .setMaxResults(size)
                        .getResultList()
        );
    }
}
