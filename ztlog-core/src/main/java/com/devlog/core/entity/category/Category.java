package com.devlog.core.entity.category;

import com.devlog.core.common.enumulation.UseYN;
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
@Table(name = "cate_mst")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATE_NO", nullable = false)
    private Long cateNo;

    @Column(name = "CATE_NM", nullable = false)
    private String cateNm;

    @Column(name = "CATE_DEPTH", nullable = false)
    private Integer cateDepth;

    @Column(name = "DISP_ORD", nullable = false)
    private Integer dispOrd;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_YN")
    private UseYN useYn;

    @Column(name = "INP_USER")
    private String inpUser;

     // 계층 구조 설정을 위한 자기 참조 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_CATE_NO")
    private Category upperCategory;

    @OneToMany(mappedBy = "upperCategory")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    public static Category created(String cateNm, Integer cateDepth, Integer dispOrd, String inpUser) {
        return Category.builder()
                .cateNm(cateNm)
                .cateDepth(cateDepth)
                .dispOrd(dispOrd)
                .inpUser(inpUser)
                .upperCategory(upperCategory)
                .useYn(UseYN.Y)
                .build();
    }

    public void updated(String cateNm, Integer cateDepth, Integer dispOrd, String inpUser) {
        this.cateNm = cateNm;
        this.cateDepth = cateDepth;
        this.dispOrd = dispOrd;
        this.inpUser = inpUser;
    }

}
