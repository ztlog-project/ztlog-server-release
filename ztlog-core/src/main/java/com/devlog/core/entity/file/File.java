package com.devlog.core.entity.file;

import com.devlog.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "file_mst")
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_NO", nullable = false)
    private Long fileNo;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_EXT")
    private String fileExt;

    @Column(name = "CTNT_NO")
    private String ctntNo;

    /**
     * File 엔티티 생성 팩토리 메서드
     *
     * @param filePath S3 파일 URL
     * @param fileName 파일명
     * @param fileExt  파일 확장자
     * @param ctntNo   연결된 게시물 번호
     * @return File 엔티티
     */
    public static File created(String filePath, String fileName, String fileExt, String ctntNo) {
        return File.builder()
                .filePath(filePath)
                .fileName(fileName)
                .fileExt(fileExt)
                .ctntNo(ctntNo)
                .build();
    }

}
