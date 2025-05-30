package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Accounts;
import com.eazybytes.springsection1.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountsRepository repository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam long id) {
        Accounts accounts = repository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        }
        else {
            return null;
        }
    }
}
