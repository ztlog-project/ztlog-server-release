package com.devlog.core.domain.repository.content;

import com.devlog.core.domain.entity.content.ContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<ContentEntity, Long> {
    Page<ContentEntity> findAll(Pageable pageable);

    Page<ContentEntity> findAllByCtntTitleContaining(String ctntTitle, Pageable pageable);
    
}
