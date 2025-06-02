package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.model.Notice;
import com.eazybytes.springsection1.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {
    private final NoticeRepository repository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = repository.findAllActiveNotices();

        if (notices != null) {
            return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(notices);
        }
        else {
            return null;
        }
    }
}
