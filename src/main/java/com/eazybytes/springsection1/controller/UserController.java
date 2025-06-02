package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Customer;
import com.eazybytes.springsection1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Customer customer) {
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return new ResponseEntity<>("Given user details are successfully registered.", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("User registration failed.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());

        return optionalCustomer.orElse(null);
    }
}
