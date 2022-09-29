package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.domain.Person;
import org.example.domain.PersonDao;
import org.example.domain.Team;
import org.example.domain.TeamDao;

import java.util.Optional;

public class JpaDemo {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("hrm-mysql").createEntityManager();

        PersonDao dao = new PersonDao(em);
        TeamDao teamDao = new TeamDao(em);

        Team team = Team.builder().name("Starters Java sept 2022").build();
        Person p = Person.builder().name("name").age(1).myTeam(team).build();

        // C
        dao.create(p);

        // String resume = p.getResume();
        // System.out.println(resume);

        Optional<Person> read = dao.read(1);

        // // R
        // Optional<Person> optionalPerson = dao.read(p.getId());
        //
        // // U
        // optionalPerson.ifPresent(ps -> ps.setName("Updated"));
        // optionalPerson.ifPresent(dao::update);
        //
        // dao.updateName(p.getId(), "Bram");
        //
        // // D
        // Person p2 = Person.builder().name("name2").age(12).build();
        // dao.create(p2);
        // dao.delete(p2);

    }
}
