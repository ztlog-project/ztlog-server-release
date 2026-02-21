package com.devlog.admin.controller.file;


import com.devlog.admin.dto.file.response.FileUploadResDto;
import com.devlog.admin.service.file.FileService;
import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 컨트롤러
 */
@Slf4j
@Tag(name = "파일 컨트롤러", description = "파일 업로드 및 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {

    private final FileService fileService;

    /**
     * 이미지 파일 업로드
     *
     * @param file      업로드할 파일 (JPEG, PNG, GIF, WebP, 최대 10MB)
     * @param directory S3 디렉토리 경로 (기본값: images/content)
     * @param ctntNo    연결된 게시물 번호 (선택사항)
     * @return 업로드된 파일 정보 (S3 URL 포함)
     */
    @Operation(summary = "이미지 파일 업로드", description = "이미지 파일을 AWS S3에 업로드하고 메타데이터를 DB에 저장합니다. 지원 형식: JPEG, PNG, GIF, WebP (최대 10MB)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "파일 업로드 성공",content = @Content(schema = @Schema(implementation = FileUploadResDto.class))),
            @ApiResponse(responseCode = "400",description = "잘못된 요청 (파일 없음, 유효하지 않은 데이터)",content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패 (JWT 토큰 없음 또는 유효하지 않음)", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "413", description = "파일 크기 초과 (최대 10MB)", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "415", description = "지원하지 않는 파일 형식", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 (S3 업로드 실패, DB 저장 실패 등)", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<FileUploadResDto>> uploadImage(
            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart("file") MultipartFile file,

            @Parameter(description = "S3 디렉토리 경로", example = "images/content")
            @RequestParam(value = "directory", defaultValue = "images/content") String directory,

            @Parameter(description = "연결된 게시물 번호 (선택사항)", example = "1")
            @RequestParam(value = "ctntNo", required = false) String ctntNo
    ) {
        log.info("File upload request received: filename={}, directory={}, ctntNo={}", file.getOriginalFilename(), directory, ctntNo);
        FileUploadResDto result = fileService.uploadImage(file, directory, ctntNo);
        return Response.success(ResponseCode.CREATED_SUCCESS, result);
    }

}
