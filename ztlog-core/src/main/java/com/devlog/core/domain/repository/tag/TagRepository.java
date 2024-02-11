package com.devlog.core.domain.repository.tag;

import com.devlog.core.domain.entity.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

}
