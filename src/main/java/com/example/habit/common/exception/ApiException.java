package com.example.habit.common.exception;

import com.example.habit.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return errorCode.getMessage();
    }
}
