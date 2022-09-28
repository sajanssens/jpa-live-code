package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.domain.Person;

public class JpaDemo {

    public static void main(String[] args) {
        Person p = Person.builder().name("name").age(1).build();

        EntityManager em = Persistence.createEntityManagerFactory("hrm-mysql").createEntityManager();

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
}
