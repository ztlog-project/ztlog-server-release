package com.devlog.core.domain.repository.tag;

import com.devlog.core.domain.entity.tag.TagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {

}
