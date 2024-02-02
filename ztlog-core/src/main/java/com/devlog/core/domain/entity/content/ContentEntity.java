package com.devlog.core.domain.entity.content;

import com.devlog.core.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "content")
public class ContentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CTNT_NO", nullable = false)
    private Long ctntNo;

    @Column(name = "CTNT_TITLE")
    private String ctntTitle;

    @Column(name = "CTNT_SUBTITLE")
    private String ctntSubTitle;

    @Column(name = "INP_USER")
    private String inpUser;


}
