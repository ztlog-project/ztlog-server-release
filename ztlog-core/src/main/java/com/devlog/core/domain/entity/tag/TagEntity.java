package com.devlog.core.domain.entity.tag;

import com.devlog.core.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags_mst")
public class TagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAG_NO", nullable = false)
    private Long tagNo;

    @Column(name = "TAG_NAME", nullable = false)
    private String tagName;

}
