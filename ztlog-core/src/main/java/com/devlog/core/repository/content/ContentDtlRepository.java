package com.devlog.core.repository.content;

import com.devlog.core.entity.content.ContentDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDtlRepository extends JpaRepository<ContentDtlEntity, Long> {
}
