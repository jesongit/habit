package com.example.habit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME(1001, "用户名验证失败"),
    PASSWORD(1002, "密码验证失败"),

    HABIT_NO_FOUND(2001, "习惯不存在"),
    CHECK_IN_REPEAT(2002, "重复打卡"),

    NOTE_IMG_ERROR(3001, "图片上传失败"),
    CONTEXT_VALID(3002, "内容为空"),
    NOTE_NO_FOUND(3003, "笔记不存在"),
    NOTE_LIKE_VALID(3004, "不能点赞自己或已经点赞"),
    ;

    private long code;
    private String message;
}
