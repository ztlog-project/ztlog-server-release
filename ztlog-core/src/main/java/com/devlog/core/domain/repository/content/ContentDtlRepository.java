package com.devlog.core.domain.repository.content;

import com.devlog.core.domain.entity.content.ContentDtlEntity;
import com.devlog.core.domain.entity.content.ContentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDtlRepository extends CrudRepository<ContentDtlEntity, Long> {
}
