package com.devlog.admin.dto.file.response;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.file.File;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class FileUploadResDto {

    @Schema(description = "파일 번호")
    private Long fileNo;

    @Schema(description = "S3 파일 URL")
    private String filePath;

    @Schema(description = "파일명")
    private String fileName;

    @Schema(description = "파일 확장자")
    private String fileExt;

    @Schema(description = "연결된 게시물 번호")
    private String ctntNo;

    @Schema(description = "파일 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    /**
     * File 엔티티로부터 FileUploadResDto 생성
     *
     * @param file File 엔티티
     * @return FileUploadResDto
     */
    public static FileUploadResDto from(File file) {
        return FileUploadResDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .fileName(file.getFileName())
                .fileExt(file.getFileExt())
                .ctntNo(file.getCtntNo())
                .inpDttm(file.getInpDttm())
                .build();
    }
}
