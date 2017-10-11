package exservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exservlet.model.Person;
import exservlet.model.Post;

import java.util.Arrays;

public class JsonConverter {

    public static void main(String[] args) throws Exception {

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Convert Json");
        post.setHidden(false);
        post.setTags(Arrays.asList("java", "json"));

        String json = new ObjectMapper().writeValueAsString(Arrays.asList(post, post));
        System.out.println(json);

//        String input = "{\"id\":1,\"name\":\"Jill\"}";
        String input = Util.readFileFromClasspath("people.json");
        Person person = new ObjectMapper().readValue(input, Person.class);

        System.out.println(person);
    }


}
