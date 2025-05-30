package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Cards;
import com.eazybytes.springsection1.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {
    private final CardsRepository repository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam long id) {
        List<Cards> cards = repository.findByCustomerId(id);

        if (cards != null) {
            return cards;
        }
        else {
            return null;
        }
    }
}
