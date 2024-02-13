package com.devlog.core.repository.content;

import com.devlog.core.entity.content.ContentTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentTagRepository extends JpaRepository<ContentTagsEntity, Long> {

}
