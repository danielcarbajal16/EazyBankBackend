package com.eazybytes.springsection1.config;

import com.eazybytes.springsection1.exceptionhandling.CustomAccessDeniedHandler;
import com.eazybytes.springsection1.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            //.requiresChannel(rcf -> rcf.anyRequest().requiresInsecure()) // This is to allow http requests, to allow https requests change this to rcf.anyRequest().requiresSecure()
            .sessionManagement(smc -> smc.maximumSessions(1).maxSessionsPreventsLogin(true))
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "myLoans", "/myCards", "/myBalance").authenticated()
                .requestMatchers("/notices", "/contact", "/error", "/register").permitAll());
        http.formLogin(Customizer.withDefaults());
        //http.formLogin(login -> login.disable());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

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
