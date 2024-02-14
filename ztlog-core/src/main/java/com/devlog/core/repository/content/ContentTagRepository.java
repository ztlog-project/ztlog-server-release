package com.devlog.core.repository.content;

import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {

}
