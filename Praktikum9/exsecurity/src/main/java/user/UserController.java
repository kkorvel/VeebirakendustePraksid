package user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.Authenticator;
import java.security.Principal;
import java.util.Objects;

@RestController
public class UserController {

    @GetMapping("/")
    public String frontPage() {
        return "Front page!";
    }

    @GetMapping("/count")
    public String counter(HttpSession session) {

        Object count = session.getAttribute("count");

        if (count != null && count instanceof Integer) {
            count = (Integer) count + 1;
        } else {
            count = 0;
        }

        session.setAttribute("count", count);

        return String.valueOf(count);
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/api/info")
    public String info(Principal principal) {
        return "Current user: " + principal.getName();
    }

    @GetMapping("/api/users/{userName}")
    @PreAuthorize("#userName == authentication.name")
    public User getUserByName(@PathVariable String userName, Principal principal, Authenticator authenticator, HttpServletResponse response) {



//        if(Objects.equals(principal.getName(), userName)){
//            response.setStatus(403);
//            return null;
//        }
        return new UserDao().getUserByUserName(userName);
    }

}