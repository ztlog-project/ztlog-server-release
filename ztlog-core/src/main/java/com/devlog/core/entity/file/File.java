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


}
