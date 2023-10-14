package jp.co.axa.apidemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig is a configuration class responsible for defining security policies
 * in the application. It configures authentication and authorization settings for both
 * in-memory authentication (for testing purposes) and user details service with password
 * encoding. It also specifies access control rules for various API endpoints and the
 * H2 Database Console.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure in-memory authentication for testing
        auth.inMemoryAuthentication()
            .withUser("user@boot.com").password(passwordEncoder().encode("password")).roles("USER");
        
        // Configure user details service and password encoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/v1/employees/**").authenticated() // Require authentication for API endpoints
                .antMatchers("/api/v1/users/**").authenticated() // Require authentication for User endpoints
                .antMatchers("/api/v1/roles/**").authenticated() // Require authentication for Role endpoints
                .antMatchers("/h2-console/**").authenticated() // Require authentication for H2 Console
                .and()
            .httpBasic()
                .and()
            .csrf().disable(); // Disable CSRF for RESTful API

        // Enable H2 console with proper security settings
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
