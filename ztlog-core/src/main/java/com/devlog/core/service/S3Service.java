package com.devlog.core.service;

import com.devlog.core.config.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

/**
 * AWS S3 파일 업로드 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region.static}")
    private String region;

    /**
     * S3에 파일 업로드
     *
     * @param file      업로드할 파일
     * @param directory S3 내 디렉토리 경로 (예: "images/content")
     * @return          S3 파일 URL
     * @throws InternalServerException S3 업로드 실패 시
     */
    public String uploadFile(MultipartFile file, String directory) {
        try {
            // UUID 기반 고유 파일명 생성
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";
            String uniqueFileName = UUID.randomUUID() + fileExtension;

            // S3 키 생성 (디렉토리/파일명)
            String s3Key = directory.endsWith("/") ? directory + uniqueFileName : directory + "/" + uniqueFileName;

            log.info("Uploading file to S3: bucket={}, key={}, size={} bytes", bucketName, s3Key, file.getSize());

            // PutObjectRequest 생성
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            // S3에 파일 업로드
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            // S3 URL 생성
            String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, s3Key);

            log.info("File uploaded successfully: {}", s3Url);
            return s3Url;

        } catch (S3Exception e) {
            log.error("S3 upload failed: bucket={}, error={}", bucketName, e.awsErrorDetails().errorMessage(), e);
            throw new InternalServerException("S3 파일 업로드에 실패했습니다: " + e.awsErrorDetails().errorMessage());
        } catch (IOException e) {
            log.error("File read failed during S3 upload", e);
            throw new InternalServerException("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
