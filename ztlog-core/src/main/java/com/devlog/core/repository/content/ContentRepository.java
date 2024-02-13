package com.devlog.core.repository.content;

import com.devlog.core.entity.content.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Page<Content> findAllByCtntTitleContaining(String ctntTitle, Pageable pageable);

}
