package com.devlog.core.domain.entity.content;

import com.devlog.core.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "content_tags")
public class ContentTagsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAG_NO", nullable = false)
    private Long tagNo;

    @Column(name = "SORT", nullable = false)
    private Integer sort;

    @Column(name = "ctnt_no", nullable = false)
    private Long ctntNo;

}
