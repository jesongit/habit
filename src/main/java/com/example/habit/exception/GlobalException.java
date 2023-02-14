package com.example.habit.exception;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalException {

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerException(NotLoginException e) {
        log.info(e.getMessage());
        return SaResult.error(e.getMessage());
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handlerException(NotPermissionException e) {
        log.info(e.getMessage());
        return SaResult.error("缺少权限：" + e.getPermission());
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public SaResult handlerException(NotRoleException e) {
        log.info(e.getMessage());
        return SaResult.error("缺少角色：" + e.getRole());
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public SaResult handlerException(NotSafeException e) {
        log.info(e.getMessage());
        return SaResult.error("二级认证校验失败：" + e.getService());
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public SaResult handlerException(DisableServiceException e) {
        log.info(e.getMessage());
        return SaResult.error("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
    }

    // 拦截：Http Basic 校验失败异常
    @ExceptionHandler(NotBasicAuthException.class)
    public SaResult handlerException(NotBasicAuthException e) {
        log.info(e.getMessage());
        return SaResult.error(e.getMessage());
    }

    // 拦截自定义异常
    @ExceptionHandler(ApiException.class)
    public SaResult handlerException(ApiException e) {
        log.info(e.getMessage());
        return SaResult.error(e.getMessage());
    }

    // 拦截：其它所有异常
    @ExceptionHandler(Exception.class)
    public SaResult handlerException(Exception e) {
        log.info(e.getMessage());
        return SaResult.error(e.getMessage());
    }

}
