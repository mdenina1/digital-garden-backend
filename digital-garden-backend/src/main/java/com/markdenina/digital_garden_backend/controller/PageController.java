package com.markdenina.digital_garden_backend.controller;

import com.markdenina.digital_garden_backend.model.Page;
import com.markdenina.digital_garden_backend.repository.PageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {
    private final PageRepository pageRepo;

    public PageController(PageRepository pageRepo) {
        this.pageRepo = pageRepo;
    }

    @PostMapping
    public Page createPage(@RequestBody Page page) {
        page.
    }
}