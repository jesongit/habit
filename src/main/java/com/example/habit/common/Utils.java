package com.example.habit.common;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.habit.exception.ApiException;
import com.example.habit.exception.ErrorCode;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class Utils {

    private final static String key = "LD4pMdPI9R0ruBK";
    private final static String basePath = "static/img/";

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

    // 位记录
    public static int bit_set(int value, int index) {
        return value ^ (1 << (index - 1));
    }

    // 查询位记录
    public static boolean bit_is_set(int value, int index) {
        return (value & (1 << (index - 1))) > 0;
    }

    public static void print_bit(int value) {
        if(value == 0)
            return;
        print_bit(value >> 1);
        System.out.print(value & 1);
    }

    // 保存图片
    public static File save_img(MultipartFile file) {
        try {
            String filename = String.format("%s.%s", IdUtil.simpleUUID(), FileUtil.getSuffix(file.getOriginalFilename()));
            String path = ResourceUtils.getURL("classpath:").getPath() + basePath;

            File save_file = new File(path, filename);
            file.transferTo(save_file);
            Utils.iAssert(save_file.exists(), ErrorCode.NOTE_IMG_ERROR);

            log.debug(StrUtil.format("{}> filename: {}", Utils.getLine(), filename));
            return save_file;
        } catch (IOException e) {
            throw new ApiException(ErrorCode.NOTE_IMG_ERROR);
        }
    }

    public static void main(String[] args) {
        int ans = 0;
        ans = bit_set(ans, 1);
        ans = bit_set(ans, 2);
        print_bit(ans);
    }
}
