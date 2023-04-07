package com.peppermint100.userservice.security;

import com.peppermint100.userservice.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.IpAddressMatcher;


@Configuration // bean 등록 우선순위가 높음
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment env;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.env = env;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests(authorize -> {
                    try {
                        authorize
//                                .antMatchers("/users/**").permitAll()
                                .antMatchers("/actuator/**").permitAll()
                                .antMatchers("/**").permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll()
                                .and()
                                .addFilter(getAuthenticationFilter());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
