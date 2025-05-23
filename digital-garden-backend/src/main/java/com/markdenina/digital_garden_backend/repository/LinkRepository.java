package com.markdenina.digital_garden_backend.repository;

import com.markdenina.digital_garden_backend.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByUserId(Long userId);

    List<Link> findBySourceTypeAndSourceId(String sourceType, Long sourceId);

    List<Link> findByTargetTypeAndTargetId(String targetType, Long targetId);
}