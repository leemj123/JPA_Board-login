package com.example.LeeGamja.error.exception;

import com.example.LeeGamja.error.ErrorCode;

public class BadRequestException extends BusinessException{

    BadRequestException(String message, ErrorCode errorCode) {
        super(errorCode, message);
    }
}
