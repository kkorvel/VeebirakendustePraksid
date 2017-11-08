package security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public RestAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        LoginCredentials loginCredentials;

        try {

        loginCredentials = new ObjectMapper().readValue(request.getInputStream(), LoginCredentials.class);
        } catch (Exception e){
            throw new ServletException("login error");
        }

        System.out.println(loginCredentials);

        // Read info from HttpServletRequest.

        // Use ObjectMapper to convert Json to LoginCredentials object.

        // Info from LoginCredentials is used below.

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(
                    loginCredentials.getUserName(),
                        loginCredentials.getPassword());

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
