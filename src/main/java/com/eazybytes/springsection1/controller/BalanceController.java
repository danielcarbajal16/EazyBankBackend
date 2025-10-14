package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.AccountTransactions;
import com.eazybytes.springsection1.model.Customer;
import com.eazybytes.springsection1.repository.AccountTransactionsRepository;
import com.eazybytes.springsection1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BalanceController {
    private final AccountTransactionsRepository repository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        if (optionalCustomer.isPresent()) {
            List<AccountTransactions> accountTransactions = repository.findByCustomerIdOrderByTransactionDtDesc(optionalCustomer.get().getId());

            if (accountTransactions != null) {
                return accountTransactions;
            }
            else {
                return null;
            }
        } else {
            return null;
        }
    }
}
