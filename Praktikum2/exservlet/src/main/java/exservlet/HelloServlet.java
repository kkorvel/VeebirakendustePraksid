package exservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exservlet.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static List<Person> personList = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().print("Hello!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userInput = Util.asString(req.getInputStream());
//        System.out.println(userInput);
        Person person = new ObjectMapper().readValue(userInput, Person.class);
        System.out.println(person);
        person.setName("Jack");
        person.setId(2L);

//        personList.add(person);

        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getOutputStream(), person);

    }
}
