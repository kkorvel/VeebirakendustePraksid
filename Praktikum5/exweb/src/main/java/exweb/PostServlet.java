package exweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/api/posts")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    PostDao dao = new PostDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        new ObjectMapper().writeValue(response.getOutputStream(), dao.getPosts());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Post post = new ObjectMapper().readValue(request.getInputStream(), Post.class);
        dao.insertPosts(post);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        dao.deletePost(id);
    }
}
