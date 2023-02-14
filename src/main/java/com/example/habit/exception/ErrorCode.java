package com.example.habit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME(1001, "用户名验证失败"),
    PASSWORD(1002, "密码验证失败"),

    HABIT_NO_FOUND(2001, "习惯不存在"),
    ;

    private long code;
    private String message;
}
