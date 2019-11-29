package ie.stephen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //        Allow all requests to the root url (“/”)
                .authorizeRequests().antMatchers("/").permitAll().and()
                //        Allow all requests to the H2 database console url (“/console/*”)
                .authorizeRequests().antMatchers("/console/**").permitAll();

        // Disable CSRF protection
        httpSecurity.csrf().disable();

        // Disable X-Frame-Options in Spring Security
        httpSecurity.headers().frameOptions().disable();
    }
}
