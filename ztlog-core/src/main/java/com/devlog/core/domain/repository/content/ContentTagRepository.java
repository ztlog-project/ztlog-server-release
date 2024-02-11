package com.devlog.core.domain.repository.content;

import com.devlog.core.domain.entity.content.ContentTagsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentTagRepository extends CrudRepository<ContentTagsEntity, Long> {

}
