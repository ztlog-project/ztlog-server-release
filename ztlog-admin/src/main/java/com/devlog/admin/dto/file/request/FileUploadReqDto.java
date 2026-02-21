package com.devlog.admin.dto.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FileUploadReqDto {

    @Schema(description = "업로드할 파일", required = true)
    private MultipartFile file;

    @Schema(description = "S3 디렉토리 경로 (예: images/content)", example = "images/content")
    private String directory;

    @Schema(description = "연결된 게시물 번호 (선택사항)", example = "1")
    private String ctntNo;
}
