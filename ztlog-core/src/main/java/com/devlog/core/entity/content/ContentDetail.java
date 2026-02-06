package com.devlog.core.entity.content;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "contents_dtl")
public class ContentDetail {

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
    Content content;

    public static ContentDetail created(String title, String body, String user) {
        return ContentDetail.builder()
                .ctntTitle(title)
                .ctntBody(body)
                .inpUser(user)
                .build();
    }

    public void updated(String title, String body) {
        this.ctntTitle = title;
        this.ctntBody = body;
    }

}
