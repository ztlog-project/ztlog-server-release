package com.devlog.core.entity.content;

import com.devlog.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import static com.devlog.core.common.constants.CommonConstants.SUBTITLE_SIZE;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "contents_mst")
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTNT_NO", nullable = false)
    private Long ctntNo;

    @Column(name = "CTNT_TITLE", nullable = false)
    private String ctntTitle;

    @Column(name = "CTNT_SUBTITLE", nullable = false)
    private String ctntSubTitle;

    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "CTNT_NO")
    private ContentDetail contentDetail;

    @OneToMany(mappedBy = "contents", fetch = FetchType.LAZY)
    @OrderBy("sort asc")
    private List<ContentTag> contentTags = new ArrayList<>();

    @Column(name = "INP_USER", nullable = false)
    private String inpUser;

    public static Content created(ContentDetail contentDetail) {
        Content content = Content.builder()
                .ctntTitle(contentDetail.getCtntTitle())
                .ctntSubTitle(truncated(contentDetail.getCtntBody()))
                .contentDetail(contentDetail)
                .contentTags(Optional.ofNullable(contentDetail.getContent())
                        .map(Content::getContentTags)
                        .orElse(null))
                .inpUser(contentDetail.getInpUser())
                .build();

        // ContentDetail에게 부모(Content)를 연결 (MapsId 에러 해결 지점)
        contentDetail.updated(content.ctntTitle, content.ctntSubTitle, content);

        return content;
    }

    private static String truncated(String text) {
        if (text == null || text.length() <= SUBTITLE_SIZE) {
            return text;
        }
        return text.substring(0, SUBTITLE_SIZE);
    }

    public void updated(String title, String body) {
        this.ctntTitle = title;
        this.ctntSubTitle = body.length() > SUBTITLE_SIZE ? body.substring(0, SUBTITLE_SIZE) : body;
    }

}
