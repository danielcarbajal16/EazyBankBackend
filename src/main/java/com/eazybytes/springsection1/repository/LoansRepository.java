package com.eazybytes.springsection1.repository;

import com.eazybytes.springsection1.model.Loans;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoansRepository extends CrudRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);
}
