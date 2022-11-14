package com.example.LeeGamja.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorEntity {
    private String errorCode;
    private String message;

    @Builder
    ErrorEntity(String errorCode,String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
