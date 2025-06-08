package com.markdenina.digital_garden_backend.controller;

import com.markdenina.digital_garden_backend.model.Topic;
import com.markdenina.digital_garden_backend.repository.TopicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicRepository topicRepo;

    public TopicController(TopicRepository topicRepo) {
        this.topicRepo = topicRepo;
    }

    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        topic.setCreatedAt(LocalDateTime.now());
        return topicRepo.save(topic);
    }

    @GetMapping("/user/{userId}")
    public List<Topic> getTopicsByUser(@PathVariable Long userId) {
        return topicRepo.findByUserId(userId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return topicRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic updatedTopic) {
        return topicRepo.findById(id).map(topic -> {
            topic.setCreatedAt(LocalDateTime.now());
            topic.setTitle(updatedTopic.getTitle());
            topic.setDescription(updatedTopic.getDescription());
            return ResponseEntity.ok(topicRepo.save(topic));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (!topicRepo.existsById(id)) return ResponseEntity.notFound().build();
        topicRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}