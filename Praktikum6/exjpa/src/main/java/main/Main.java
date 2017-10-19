package main;

import dao.SetupDao;
import dao.personDao;
import model.Address;
import model.Person;
import model.Phone;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } finally {
            JpaUtil.closeFactory();
        }
    }

    private void run() {
        new SetupDao().createSchema();
        personDao personDao = new personDao();

        personDao.insertPerson("Jill");
        personDao.insertPerson("Jack");
        Person person = findPersonByName("Jill");
        person.setName("Jillian");
        person.setAddress(new Address("Oak street 1"));
        person.getPhones().addAll(Arrays.asList(new Phone("123"),new Phone("321")));
        personDao.savePerson(person);
        System.out.println(findaAllPerson().size());
        System.out.println(findaAllPerson());

    }


    private List<Person> findaAllPerson() {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();
            TypedQuery<Person> query = em.createQuery("select distinct p from Person p left join fetch p.phones", Person.class);

        return query.getResultList();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
    private Person findPersonByName(String name) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();
            TypedQuery<Person> query = em.createQuery("select p from Person p left join fetch p.phones where name = :name", Person.class);
            query.setParameter("name", name);
            return query.getSingleResult();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
}


