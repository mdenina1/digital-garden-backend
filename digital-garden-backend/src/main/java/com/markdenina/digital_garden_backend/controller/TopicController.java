package com.markdenina.digital_garden_backend.controller;

import com.markdenina.digital_garden_backend.model.Topic;
import com.markdenina.digital_garden_backend.repository.TopicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/topic")
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
}