package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Cards;
import com.eazybytes.springsection1.model.Customer;
import com.eazybytes.springsection1.repository.CardsRepository;
import com.eazybytes.springsection1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardsController {
    private final CardsRepository repository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        if (optionalCustomer.isPresent()) {
            List<Cards> cards = repository.findByCustomerId(optionalCustomer.get().getId());

            if (cards != null) {
                return cards;
            }
            else {
                return null;
            }
        } else {
            return null;
        }
    }
}
