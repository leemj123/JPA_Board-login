package com.example.LeeGamja.error.exception;

import com.example.LeeGamja.error.ErrorCode;

public class InternerServerException extends BusinessException{
    public InternerServerException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
