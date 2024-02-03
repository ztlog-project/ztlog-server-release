package com.devlog.core.domain.repository.content;

import com.devlog.core.domain.entity.content.ContentEntity;
import com.devlog.core.domain.entity.content.ContentTagsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentTagRepository extends CrudRepository<ContentTagsEntity, Long> {

    @Query(value = "" +
            "SELECT tm.TAG_NAME " +
            "FROM tags_mst tm  " +
            "INNER JOIN contents_tags ct ON tm.TAG_NO = ct.TAG_NO " +
            "WHERE ct.CTNT_NO = :ctntNo ", nativeQuery = true)
    List<String> findTagNameListByCtntNo(Long ctntNo);

    @Query(value = "" +
            "SELECT cm.CTNT_NO, cm.CTNT_TITLE, cm.CTNT_SUBTITLE, cm.INP_USER, cm.INP_DTTM, cm.UPD_DTTM " +
            "FROM contents_tags ct " +
            "INNER JOIN contents_mst cm ON ct.CTNT_NO = cm.CTNT_NO " +
            "WHERE ct.TAG_NO = :tagNo ", nativeQuery = true)
    List<ContentEntity> findAllByTagNo(Long tagNo);
}
