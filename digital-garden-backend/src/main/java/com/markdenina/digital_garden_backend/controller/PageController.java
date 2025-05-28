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
        page.setCreatedAt(LocalDateTime.now());
        return pageRepo.save(page);
    }

    @GetMapping("/{userId}")
    public List<Page> getPagesByUser(@PathVariable Long userId) {
        return pageRepo.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id) {
        return pageRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatedPage) {
        return pageRepo.findById(id).map(page -> {
            page.setCreatedAt(LocalDateTime.now());
            page.setTitle(updatedPage.getTitle());
            page.setContent(updatedPage.getContent());
            return ResponseEntity.ok(pageRepo.save(page));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage (@PathVariable Long id) {
        if (!pageRepo.existsById(id)) return ResponseEntity.notFound().build();
        pageRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}