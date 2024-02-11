package com.devlog.core.domain.repository.content;

import com.devlog.core.domain.entity.content.ContentTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentTagRepository extends JpaRepository<ContentTagsEntity, Long> {

}
