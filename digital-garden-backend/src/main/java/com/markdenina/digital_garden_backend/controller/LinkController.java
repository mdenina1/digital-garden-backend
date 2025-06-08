package com.markdenina.digital_garden_backend.controller;

import com.markdenina.digital_garden_backend.model.Link;
import com.markdenina.digital_garden_backend.repository.LinkRepository;
import com.markdenina.digital_garden_backend.repository.PageRepository;
import com.markdenina.digital_garden_backend.repository.TopicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    private final LinkRepository linkRepo;
    private final PageRepository pageRepo;
    private final TopicRepository topicRepo;

    public LinkController(LinkRepository linkRepo, PageRepository pageRepo, TopicRepository topicRepo) {
        this.linkRepo = linkRepo;
        this.pageRepo = pageRepo;
        this.topicRepo = topicRepo;
    }

    @PostMapping
    public ResponseEntity<?> createLink(@RequestBody Link link) {
        // Check if duplicate link exists
        boolean duplicateExists = linkRepo.findBySourceTypeAndSourceId(link.getSourceType(), link.getSourceId())
                .stream()
                .anyMatch(existing ->
                        existing.getTargetType().equals(link.getTargetType()) &&
                                existing.getTargetId().equals(link.getTargetId()) &&
                                (link.getRelationshipType() == null || link.getRelationshipType().equals(existing.getRelationshipType()))
                );

        if (duplicateExists) {
            return ResponseEntity.badRequest().body("Link already exists");
        }

        // Validate source
        if (!isValidEntity(link.getSourceType(), link.getSourceId())) {
            return ResponseEntity.badRequest().body("Invalid source reference");
        }

        // Validate target
        if (!isValidEntity(link.getTargetType(), link.getTargetId())) {
            return ResponseEntity.badRequest().body("Invalid target reference");
        }

        return ResponseEntity.ok(linkRepo.save(link));
    }

    @GetMapping("/user/{userId}")
    public List<Link> getLinksByUser(@PathVariable Long userId) {
        return linkRepo.findByUserId(userId);
    }

    @GetMapping("/source/{type}/{id}")
    public List<Link> getLinksBySourceTypeAndSourceId(@PathVariable String type, @PathVariable Long id) {
        return linkRepo.findBySourceTypeAndSourceId(type, id);
    }

    @GetMapping("/target/{type}/{id}")
    public List<Link> getLinksByTargetTypeAndTargetId(@PathVariable String type, @PathVariable Long id) {
        return linkRepo.findByTargetTypeAndTargetId(type, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        if (!linkRepo.existsById(id)) return ResponseEntity.notFound().build();
        linkRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/source/{id}")
    public ResponseEntity<Void> deleteLinksBySourceId(@PathVariable Long id) {
        return deleteLinks(linkRepo.findBySourceId(id));
    }

    @DeleteMapping("/target/{id}")
    public ResponseEntity<Void> deleteLinksByTargetId(@PathVariable Long id) {
        return deleteLinks(linkRepo.findByTargetId(id));
    }

    private boolean isValidEntity(String type, Long id) {
        return switch (type.toLowerCase()) {
            case "page" -> pageRepo.existsById(id);
            case "topic" -> topicRepo.existsById(id);
            default -> false;
        };
    }

    private ResponseEntity<Void> deleteLinks(List<Link> links) {
        if (links.isEmpty()) return ResponseEntity.notFound().build();
        links.forEach(link -> linkRepo.deleteById(link.getId()));
        return ResponseEntity.noContent().build();
    }
}