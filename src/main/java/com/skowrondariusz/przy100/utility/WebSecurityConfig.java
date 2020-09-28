package com.skowrondariusz.przy100.utility;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/hello").authenticated();
        http.authorizeRequests().antMatchers("/album/{authorName}").authenticated();
        http.authorizeRequests().antMatchers("/artists/{authorName}").authenticated();
        http.authorizeRequests().antMatchers("/albums/{albumId}").authenticated();
        http.authorizeRequests().antMatchers("/xx").authenticated();


    }

//    @Configuration
//    protected static class AuthenticationConfiguration extends
//            GlobalAuthenticationConfigurerAdapter {
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
////          As for the configure(AuthenticationManagerBuilder) method, it sets up
////          an in-memory user store with a single user. That user is given a
////          username of "user", a password of "password", and a role of "USER".
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("darekskowron1@gmail.com").password("4t76uzhr").roles("USER");
//        }
//    }
}
