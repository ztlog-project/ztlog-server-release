package com.devlog.core.common.service.repository.content;

import com.devlog.core.common.service.entity.content.ContentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<ContentEntity, Long> {
}
