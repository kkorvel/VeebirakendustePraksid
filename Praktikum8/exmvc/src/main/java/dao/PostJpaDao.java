package dao;

import model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostJpaDao implements PostDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("SELECT p FROM Post p", Post.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.createQuery("delete from Post p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Post findById(Long id) {
        return em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class)
                .setParameter("id", id)
                .getSingleResult();

    }

}
