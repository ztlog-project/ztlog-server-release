package com.devlog.core.entity.content;

import com.devlog.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
        return Content.builder()
                .ctntNo(contentDetail.getCtntNo())
                .ctntTitle(contentDetail.getCtntTitle())
                .ctntSubTitle(contentDetail.getCtntBody().substring(0, 300))
                .contentDetail(contentDetail)
                .contentTags(contentDetail.content.getContentTags())
                .inpUser(contentDetail.getInpUser())
                .build();
    }

}
