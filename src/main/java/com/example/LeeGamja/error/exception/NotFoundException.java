package com.example.LeeGamja.error.exception;

import com.example.LeeGamja.error.ErrorCode;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
