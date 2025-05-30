package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.AccountTransactions;
import com.eazybytes.springsection1.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BalanceController {
    private final AccountTransactionsRepository repository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions = repository.findByCustomerIdOrderByTransactionDtDesc(id);

        if (accountTransactions != null) {
            return accountTransactions;
        }
        else {
            return null;
        }
    }
}
