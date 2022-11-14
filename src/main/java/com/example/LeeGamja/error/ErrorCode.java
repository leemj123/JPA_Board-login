package com.example.LeeGamja.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.nio.file.AccessDeniedException;

@Getter
public enum ErrorCode {
    Runtime_Exception(HttpStatus.BAD_REQUEST,"E0000","400 Error.. 잘못된 요청방식입니다."),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED,"E0010","401 Error.. 로그인하세요."),
    DUPLICATE_USER_EXCEPTION(HttpStatus.UNAUTHORIZED,"E0011","401 Error 이미 사용되고 있는 ID입니다."),
    NONEXISTENT_EXCEPTION(HttpStatus.UNAUTHORIZED,"E0012","401 Error 없는 아이디입니다."),
    NOT_APPLICABLE_EXCEPTION(HttpStatus.UNAUTHORIZED,"E0013","401 Error 잘못된 비밀번호입니다."),
    FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN,"E0020","403 Error 권한이 없습니다."),

    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"E0040","404 Not Found..잘못된 url입니다."),
    NONEXISTENT_BOARD_EXCEPTION(HttpStatus.NOT_FOUND,"E0041","404 Not Found..없는 게시글 입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"E0050","500 Error.. 예기치 못한 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
