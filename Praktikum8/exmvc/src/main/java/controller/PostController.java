package controller;

import dao.PostDao;
import dao.PostMemoryDao;
import javafx.geometry.Pos;
import model.Post;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    @Resource
    private PostDao dao;

    @GetMapping("posts")
    public List<Post> getPosts(){
        return dao.findAll();

    }

    @PostMapping("posts")
    public void savePost(@RequestBody @Valid Post post){
        dao.save(post);
    }

    @DeleteMapping("posts/{id}")
    public void savePost(@PathVariable Long id){
        dao.delete(id);
    }




}