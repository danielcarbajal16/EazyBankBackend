package com.eazybytes.springsection1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

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
        UserDetails user = User.withUsername("daniel").password("{bcrypt}$2a$12$U7w4DyvWnQ6fBy79AudPQuObiCr8c5wCuHuHeXF70It9vTV/dAPhG").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$dBJC4U6HK2tjDiyPeQPEYOYngMUfjR5tpRnrsCBy7rFXX8EaPAaS.").authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // It uses BCrypt by default till 14/02/2025
    }

    /*@Bean // This is to check if the password is compromised or not, in case of a password compromise, it will throw an exception
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/
}
