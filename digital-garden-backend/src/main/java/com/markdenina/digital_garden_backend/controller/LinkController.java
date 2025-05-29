package com.markdenina.digital_garden_backend.controller;

import com.markdenina.digital_garden_backend.model.Link;
import com.markdenina.digital_garden_backend.repository.LinkRepository;
import com.markdenina.digital_garden_backend.repository.PageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/link")
public class LinkController {
    private final LinkRepository linkRepo;

    public LinkController(LinkRepository linkRepo) {
        this.linkRepo = linkRepo;
    }

    @PostMapping
    public Link createLink(@RequestBody Link link) {

    }
}