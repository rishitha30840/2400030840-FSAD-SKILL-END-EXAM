package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Payment p = new Payment();
        p.setName("Rishitha");
        p.setDate(new Date());
        p.setStatus("SUCCESS");

        session.save(p);
        System.out.println("Record Inserted");

        tx.commit();

        session.beginTransaction();

        int deleteId = p.getId();

        Query query = session.createQuery("delete from Payment where id = :pid");
        query.setParameter("pid", deleteId);

        int result = query.executeUpdate();
        System.out.println("Deleted Records: " + result);

        session.getTransaction().commit();

        session.close();
        factory.close();
    }
}
