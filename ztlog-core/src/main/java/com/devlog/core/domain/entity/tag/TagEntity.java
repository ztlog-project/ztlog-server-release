package com.devlog.core.domain.entity.tag;

import com.devlog.core.domain.entity.BaseTimeEntity;
import com.devlog.core.domain.entity.content.ContentTagsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags_mst")
public class TagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_NO", nullable = false)
    private Long tagNo;

    @Column(name = "TAG_NAME", nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    List<ContentTagsEntity> contentTags = new ArrayList<>();

}
