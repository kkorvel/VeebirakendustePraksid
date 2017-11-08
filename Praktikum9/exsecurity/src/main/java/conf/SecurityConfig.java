package conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import security.RestAuthenticationFilter;
import security.handlers.RestLogoutSuccessHandler;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

//        http.formLogin();
        http.authorizeRequests().antMatchers("/api/hello").permitAll();
        http.authorizeRequests().antMatchers("/api/**").hasAnyRole("USER");

        http.logout().logoutUrl("/api/logout").logoutSuccessHandler(new RestLogoutSuccessHandler());

        http.addFilterAfter(restLoginFilter("/api/login"), LogoutFilter.class);
        // other configurations

    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        builder.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password("$2a$10$8tcT0AVRoRoZBdrYkelz1OJDZYBJWTBtPGRlbUF6jImrvYbXtDcgO")
                .roles("USER");
        // configure user and password info

    }

    public Filter restLoginFilter(String url) throws Exception {
        RestAuthenticationFilter filter = new RestAuthenticationFilter(url);

        filter.setAuthenticationManager(authenticationManager());

        // add success and failure handlers

        return filter;
    }
}