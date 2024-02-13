package com.devlog.core.entity.content;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "contents_dtl")
public class ContentDtlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTNT_NO", nullable = false)
    private Long ctntNo;

    @Column(name = "CTNT_TITLE", nullable = false)
    private String ctntTitle;

    @Column(name = "CTNT_BODY", nullable = false)
    private String ctntBody;

    @Column(name = "CTNT_PATH")
    private String ctntPath;

    @Column(name = "CTNT_NAME")
    private String ctntName;

    @Column(name = "CTNT_EXT")
    private String ctntExt;

    @Column(name = "INP_USER", nullable = false)
    private String inpUser;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CTNT_NO")
    ContentEntity content;


}
