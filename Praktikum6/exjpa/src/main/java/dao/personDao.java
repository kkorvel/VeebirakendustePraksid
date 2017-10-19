package dao;

import model.Person;
import util.JpaUtil;

import javax.persistence.EntityManager;

public class personDao {

    public void insertPerson(String name) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();
            Person person = new Person();
            person.setName(name);
            em.persist(person);
            em.getTransaction().commit();
            System.out.println(1);

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
    public void savePerson(Person person) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.merge(person);
            em.getTransaction().commit();
            System.out.println(1);

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
}
