package com.eazybytes.springsection1.repository;

import com.eazybytes.springsection1.model.Cards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardsRepository extends CrudRepository<Cards, Long> {
    List<Cards> findByCustomerId(long customerId);
}
