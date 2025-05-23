package com.markdenina.digital_garden_backend.repository;

import com.markdenina.digital_garden_backend.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByUserId(Long userId);

    List<Page> findByTopicId(Long topicId);

    List<Page> findByUserIdAndTopicId(Long userId, Long topicId);
}