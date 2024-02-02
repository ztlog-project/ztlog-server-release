package com.devlog.core.domain.entity.tags;

import com.devlog.core.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags_mst")
public class TagsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAG_NO", nullable = false)
    private Long tag_no;

    @Column(name = "TAG_NAME")
    private String tagName;

}
