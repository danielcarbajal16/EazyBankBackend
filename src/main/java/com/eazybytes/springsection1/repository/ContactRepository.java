package com.eazybytes.springsection1.repository;

import com.eazybytes.springsection1.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
