package com.devlog.core.domain.repository.tag;

import com.devlog.core.domain.entity.tag.TagEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {

    @Query(value = "" +
            "SELECT tm.TAG_NO, tm.TAG_NAME " +
            "FROM tags_mst tm  " +
            "INNER JOIN contents_tags ct ON tm.TAG_NO = ct.TAG_NO " +
            "WHERE ct.CTNT_NO = :ctntNo ", nativeQuery = true)
    List<TagEntity> findTagNameListByCtntNo(Long ctntNo);

}
