package com.markdenina.digital_garden_backend.repository;

import com.markdenina.digital_garden_backend.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByUserId(Long userId);
}
