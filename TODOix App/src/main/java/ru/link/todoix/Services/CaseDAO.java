package ru.link.todoix.Services;

import org.hibernate.Session;
import ru.link.todoix.HibernateSessionFactory;
import ru.link.todoix.Objects.*;

import javax.persistence.criteria.*;
import java.util.*;

public class CaseDAO implements CaseDAOImpl{

    @Override
    public void create(CaseDTO caseDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Converter.DTOToEntity(caseDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public CaseDTO findById(UUID id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CaseEntity caseEntity = session.find(CaseEntity.class, id);
        session.close();

        return Converter.entityToDTO(caseEntity);
    }

    @Override
    public void update(CaseDTO caseDTO) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(Converter.DTOToEntity(caseDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(UUID id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.find(CaseEntity.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<CaseDTO> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(CaseEntity.class);
        criteriaQuery.from(CaseEntity.class);

        return Converter.ListEntityToDTO(session.createQuery(criteriaQuery).getResultList());
    }

    @Override
    public List<CaseDTO> getPage(int page, int size, String sortBy) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(CaseEntity.class);
        Root<CaseEntity> caseEntityRoot = criteriaQuery.from(CaseEntity.class);
        criteriaQuery.orderBy(builder.asc(caseEntityRoot.get(sortBy)));

        return (List<CaseDTO>)Converter.ListEntityToDTO(
                session.createQuery(criteriaQuery)
                        .setFirstResult(size * page)
                        .setMaxResults(size)
                        .getResultList()
        );
    }
}
