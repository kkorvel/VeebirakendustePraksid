package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Person;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDao {

    @Resource
    private JdbcTemplate template;

    @PersistenceContext
    private EntityManager em;

    public List<Person> getAllPersons() {
        return template.query("SELECT * FROM person",
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> getAllPersonsJpa() {
        TypedQuery<Person> fromPersonP = em.createQuery("select p from Person p", Person.class);
        return fromPersonP.getResultList();
    }

    @Transactional
    public void save(Person person) {
        if (person.getId() == null) {
            em.persist(person);
        } else {
            em.merge(person);
        }
    }

    private static class PersonMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

            Person person = new Person(rs.getString(2));
            person.setId(rs.getLong(1));



            return person;
        }
    }
}
