package ru.link.todoix.Test;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;
import ru.link.todoix.HibernateSessionFactory;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TestController {
    private static final String template = "Это всего лишь тест, %s:)";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test")
    public void test(){
        Session session = new HibernateSessionFactory().createSessionFactory().openSession();

        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setContinent("EUROPE");
        weatherEntity.setLatitude(123.2);
        weatherEntity.setLongitude(3.1);
        weatherEntity.setReportTime(new Timestamp(System.currentTimeMillis()));
        weatherEntity.setTemperature(2);

        session.beginTransaction();
        session.save(weatherEntity);
        session.getTransaction().commit();
        session.close();

        session.close();
    }
}
