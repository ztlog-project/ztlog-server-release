package com.devlog.core.domain.entity.content;

import com.devlog.core.domain.entity.tag.TagEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ContentTagsPKEntity.class)
@Table(name = "contents_tags")
public class ContentTagsEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_NO")
    private TagEntity tags;

    @Column(name = "SORT", nullable = false)
    private Integer sort;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTNT_NO", nullable = false)
    private ContentEntity contents;

}
