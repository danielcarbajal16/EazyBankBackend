package com.eazybytes.springsection1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/myAccount", "myLoans", "/myCards", "myBalance").authenticated()
            .requestMatchers("/notices", "/contact", "/error").permitAll());
        http.formLogin(Customizer.withDefaults());
        //http.formLogin(login -> login.disable());
        http.httpBasic(Customizer.withDefaults());
        //http.httpBasic(hbc -> hbc.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("daniel").password("{noop}1234").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{noop}4321").authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
