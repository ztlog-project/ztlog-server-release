package com.devlog.core.repository.tag;

import com.devlog.core.entity.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

}
