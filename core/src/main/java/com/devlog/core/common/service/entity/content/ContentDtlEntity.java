package com.devlog.core.common.service.entity.content;

import com.devlog.core.common.service.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "content_dtl")
public class ContentDtlEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CTNT_NO", nullable = false)
    private Long ctntNo;

    @Column(name = "CTNT_TITLE")
    private String ctntTitle;

    @Column(name = "CTNT_BODY")
    private String ctntBody;

    @Column(name = "CTNT_PATH")
    private String ctntPath;

    @Column(name = "CTNT_NAME")
    private String ctntName;

    @Column(name = "CTNT_EXT")
    private String ctntExt;

    @Column(name = "INP_USER")
    private String inpUser;


}
