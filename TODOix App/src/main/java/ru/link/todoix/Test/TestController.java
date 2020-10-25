package ru.link.todoix.Test;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.HibernateSessionFactory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TestController {
    private static final String template = "Это всего лишь тест, %s:)";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test")
    public void test(){
        /**EntityManagerFactory factory = Persistence.createEntityManagerFactory("ru.link.todoix.Test");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        TestEntity testEntity = new TestEntity();
        testEntity.setId(12);
        testEntity.setText("12312313");

        manager.persist(testEntity);
        manager.getTransaction().commit();
        manager.close();*/

        /**ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(standardRegistry)
                .addAnnotatedClass(TestEntity.class)
                .buildMetadata()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        TestEntity testEntity = new TestEntity();
        testEntity.setText("12312313");

        session.persist(testEntity);*/

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(TestEntity.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        TestEntity testEntity = new TestEntity();
        testEntity.setText("12312313");

        session.save(testEntity);
        session.getTransaction().commit();
        session.close();
    }
}
