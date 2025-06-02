package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Loans;
import com.eazybytes.springsection1.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {
    private final LoansRepository repository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam long id) {
        List<Loans> loans = repository.findByCustomerIdOrderByStartDtDesc(id);

        if (loans != null) {
            return loans;
        }
        else {
            return null;
        }
    }
}
