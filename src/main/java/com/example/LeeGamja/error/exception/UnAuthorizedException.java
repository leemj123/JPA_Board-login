package com.example.LeeGamja.error.exception;

import com.example.LeeGamja.error.ErrorCode;

public class UnAuthorizedException extends BusinessException{
    public UnAuthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
