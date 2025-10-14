package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Customer;
import com.eazybytes.springsection1.model.Loans;
import com.eazybytes.springsection1.repository.CustomerRepository;
import com.eazybytes.springsection1.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoansController {
    private final LoansRepository repository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        if (optionalCustomer.isPresent()) {
            List<Loans> loans = repository.findByCustomerIdOrderByStartDtDesc(optionalCustomer.get().getId());

            if (loans != null) {
                return loans;
            }
            else {
                return null;
            }
        } else {
            return null;
        }
    }
}
