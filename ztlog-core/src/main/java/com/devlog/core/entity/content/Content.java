package com.devlog.core.entity.content;

import com.devlog.core.common.enumulation.DELETED_YN;
import com.devlog.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.YesNoConverter;

import java.util.*;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "DELETED_YN", converter = YesNoConverter.class)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "DELETED_YN", nullable = false)
    private DELETED_YN deletedYn;

    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "CTNT_NO")
    private ContentDetail contentDetail;

    @OneToMany(mappedBy = "contents", fetch = FetchType.EAGER)
    @OrderBy("sort asc")
    private List<ContentTag> contentTags = new ArrayList<>();

    @Column(name = "INP_USER", nullable = false)
    private String inpUser;

    public static Content created(String title, String subTitle, String body, String userId) {
        Content content = Content.builder()
                .ctntTitle(title)
                .ctntSubTitle(subTitle)
                .deletedYn(DELETED_YN.N)
                .inpUser(userId)
                .build();
        content.contentDetail = ContentDetail.created(title, body, userId, content);
        return content;
    }

    public void updated(String title, String subtitle) {
        this.ctntTitle = title;
        this.ctntSubTitle = subtitle;
    }

}
