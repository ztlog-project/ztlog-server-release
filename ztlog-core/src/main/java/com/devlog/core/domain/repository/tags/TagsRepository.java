package com.devlog.core.domain.repository.tags;

import com.devlog.core.domain.entity.tags.TagsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends CrudRepository<TagsEntity, Long> {

//    @Query(value = "" +
//            "SELECT tm.TAG_NO , tm.TAG_NAME , COUNT(ct.CTNT_NO) AS tag_set \n" +
//            "FROM tags_mst tm \n" +
//            "INNER JOIN contents_tags ct ON tm.TAG_NO = ct.TAG_NO \n" +
//            "GROUP BY tm.TAG_NO ", nativeQuery = true)
//    List<TagsEntity> countAllByCtntNo();
}
