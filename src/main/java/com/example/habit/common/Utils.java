package com.example.habit.common;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.example.habit.exception.ApiException;
import com.example.habit.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

public class Utils {

    private final static String key = "LD4pMdPI9R0ruBK";

    // RAS 加密
    public static String encrypt(String password) {
        return SaSecureUtil.aesEncrypt(key, password);
    }

    // RAS 解密
    public static String decrypt(String password) {
        return SaSecureUtil.aesDecrypt(key, password);
    }

    public static String getMod() {
        return getMod(2);
    }
    public static String getMod(int index) {
        return Thread.currentThread().getStackTrace()[index].getClassLoaderName();
    }

    public static String getFun() {
        return getFun(2);
    }
    public static String getFun(int index) {
        return Thread.currentThread().getStackTrace()[index].getMethodName();
    }

    public static int getLine() {
        return getLine(2);
    }
    public static int getLine(int index) {
        return Thread.currentThread().getStackTrace()[index].getLineNumber();
    }

    // 断言
    public static void iAssert(boolean expr, ErrorCode errorCode) {
        if(!expr) throw new ApiException(errorCode);
    }

    public static void main(String[] args) {
    }
}
