package com.devlog.core.common.enumulation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatusCode {

    // 200
    OK_SUCCESS(200, "성공입니다."),
    SIGNUP_SUCCESS(200, "회원가입 성공입니다."),
    LOGIN_SUCCESS(200, "로그인 성공입니다."),
    REFRESH_TOKEN_SUCCESS(200, "토큰 갱신 성공입니다."),

    // 201
    CREATED_SUCCESS(201, "생성 성공입니다."),

    // 400
    RESOURCE_NOT_FOUND(400, "조회된 정보가 없습니다."),
    DELETE_RESOURCE_NOT_FOUND(400, "삭제할 정보가 없습니다."),
    INVALID_REQUIRED(400, "필수 정보가 부족합니다."),
    CANNOT_BE_DELETE(400, "삭제할 수 없습니다."),

    HAS_NO_AUTHORIZATION(401, "권한이 없습니다."),
    NO_HANDLER_NOT_FOUND(404, "잘못된 접근입니다."),
    CHECK_DIR_ERROR(405, "파일 디렉터리 체크 오류입니다."),

    RESOURCE_DATA_CONFLICT(409, "중복된 정보입니다."),

    USER_NOT_FOUND(411, "사용자 정보가 없습니다."),
    ALREADY_TRANSACTION_KEY(411, "이미 사용된 트랜젝션키입니다."),
    NOT_FOUND_TRANSACTION_KEY(411, "해당 트랜젝션키가 존재하지 않습니다."),

    DUPLICATE_USER_ID(421, "중복된 ID가 존재합니다."),
    NEED_NEW_PASSWORD(421, "새 비밀번호를 입력하세요."),
    ERROR_PASSWORD_LENGTH(421, "비밀번호 길이는 9 ~ 15 사이입니다."),
    NEED_SPECIAL_CHAR(421, "비밀번호는 대문자, 소문자, 숫자, 특수문자가 포함되어야 합니다."),
    DUPLICATE_KEY(421, "잘못된 접근 입니다."),
    NOT_MATCH_PASSWORD(421, "비밀번호가 일치하지 않습니다."),
    NEED_NEW_PASSWORD_CONFIRM(421, "새 비밀번호 확인를 입력하세요."),
    DATE_FORMAT_ERROR(421, "요청날짜형식이 잘못되었습니다. yyyyMMdd 형식으로 입력하세요."),
    DATA_DELETE_ERROR(421, "삭제 처리 중 오류가 발생하셨습니다."),
    DATA_CREATE_ERROR(421, "데이터 생성 중 중 오류가 발생하셨습니다."),

    // 900
    DEFAULT_REST_ERROR(901, "API 요청 중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요."),
    REST_HTTP_STATUS_ERROR(901, "API 요청 중 오류응답코드를 받았습니다. 잠시후 다시 시도해 주세요."),
    DATE_PARSE_ERROR(901, "날짜 형식으로 변경 중 오류가 발생하였습니다."),
    DEFAULT_ERROR(999, "일시적인 오류입니다. 잠시후 다시 이용해 주세요."),

    //9000 batch error code
    BATCH_ERROR_9003(9000, "존재하지 않는 사용자"),
    BATCH_ERROR_9006(9000, "데이터 형식 오류"),
    BATCH_ERROR_9999(9000, "정의되지 않은 오류");

    private int status;
    private String message;

    ResponseStatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}