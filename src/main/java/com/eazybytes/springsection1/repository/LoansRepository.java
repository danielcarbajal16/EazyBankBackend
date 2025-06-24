package com.eazybytes.springsection1.repository;

import com.eazybytes.springsection1.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoansRepository extends CrudRepository<Loans, Long> {
    @PreAuthorize("hasRole('USER')")
    List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);
}
