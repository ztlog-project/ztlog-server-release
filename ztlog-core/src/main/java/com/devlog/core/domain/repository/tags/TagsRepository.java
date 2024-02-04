package com.devlog.core.domain.repository.tags;

import com.devlog.core.domain.entity.tags.TagsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends CrudRepository<TagsEntity, Long> {

}
