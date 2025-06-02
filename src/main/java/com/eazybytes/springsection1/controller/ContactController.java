package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Contact;
import com.eazybytes.springsection1.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository repository;

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceRequiredNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));

        return repository.save(contact);
    }

    public String getServiceRequiredNumber() {
        Random random = new Random();
        int randomNum = random.nextInt(999999999 - 9999) + 9999;

        return "SR" + randomNum;
    }
}
