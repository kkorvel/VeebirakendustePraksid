package dao;

import model.Post;

import java.util.List;

public interface PostDao {

    void save(Post post);

    List<Post> findAll();

    void delete(Long id);

    Post findById(Long id);
}
