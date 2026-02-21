package com.devlog.admin.service.file;

import com.devlog.admin.dto.file.response.FileUploadResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.ValidationException;
import com.devlog.core.entity.file.File;
import com.devlog.core.repository.file.FileRepository;
import com.devlog.core.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final S3Service s3Service;
    private final FileRepository fileRepository;

    /**
     * 이미지 파일을 S3에 업로드하고 메타데이터를 DB에 저장
     *
     * @param file      업로드할 파일
     * @param directory S3 디렉토리 경로
     * @param ctntNo    연결된 게시물 번호 (선택사항)
     * @return FileUploadResDto
     * @throws ValidationException 유효하지 않은 파일인 경우
     */
    public FileUploadResDto uploadImage(MultipartFile file, String directory, String ctntNo) {
        // 1. 파일 유효성 검증
        this.validateImageFile(file);

        // 2. 파일명 및 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String fileExtension = extractFileExtension(originalFilename);

        log.info("Uploading image file: filename={}, size={}, contentType={}", originalFilename, file.getSize(), file.getContentType());

        // 3. S3에 파일 업로드
        String s3Url = s3Service.uploadFile(file, directory);

        // 4. DB에 파일 메타데이터 저장
        File savedFile = File.created(s3Url, originalFilename, fileExtension, ctntNo);
        fileRepository.save(savedFile);

        log.info("File uploaded successfully: fileNo={}, s3Url={}", savedFile.getFileNo(), s3Url);

        // 5. 응답 DTO 반환
        return FileUploadResDto.of(savedFile);
    }

    /**
     * 이미지 파일 유효성 검증
     *
     * @param file 검증할 파일
     * @throws ValidationException 유효하지 않은 파일인 경우
     */
    private void validateImageFile(MultipartFile file) {
        // null 체크
        if (file == null || file.isEmpty()) {
            log.warn("File upload validation failed: file is null or empty");
            throw new ValidationException("파일이 비어있습니다", ResponseCode.INVALID_DATA_ERROR);
        }

        // 파일 크기 체크 (10MB)
        if (file.getSize() > CommonConstants.MAX_FILE_SIZE) {
            log.warn("File upload validation failed: file size {} exceeds limit {}", file.getSize(), CommonConstants.MAX_FILE_SIZE);
            throw new ValidationException("파일 크기가 제한을 초과했습니다", ResponseCode.PAYLOAD_TOO_LARGE_ERROR);
        }

        // Content-Type 체크
        String contentType = file.getContentType();
        if (contentType == null || !CommonConstants.ALLOWED_IMAGE_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            log.warn("File upload validation failed: unsupported content type {}", contentType);
            throw new ValidationException("지원하지 않는 파일 형식입니다", ResponseCode.UNSUPPORTED_MEDIA_TYPE_ERROR);
        }

        // 파일 확장자 체크
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.contains(".")) {
            log.warn("File upload validation failed: no file extension");
            throw new ValidationException("파일 확장자가 없습니다", ResponseCode.UNSUPPORTED_MEDIA_TYPE_ERROR);
        }

        String extension = extractFileExtension(filename);
        if (!CommonConstants.ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            log.warn("File upload validation failed: unsupported extension {}", extension);
            throw new ValidationException("지원하지 않는 파일 확장자입니다", ResponseCode.UNSUPPORTED_MEDIA_TYPE_ERROR);
        }
    }

    /**
     * 파일명에서 확장자 추출
     *
     * @param filename 파일명
     * @return 확장자 (소문자)
     */
    private String extractFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
}
