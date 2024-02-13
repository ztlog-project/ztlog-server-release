package com.devlog.core.entity.content;

import com.devlog.core.entity.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ContentTagPK.class)
@Table(name = "contents_tags")
public class ContentTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_NO")
    private Tag tags;

    @Column(name = "SORT", nullable = false)
    private Integer sort;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTNT_NO", nullable = false)
    private Content contents;

}
