package com.example.habit.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.example.habit.common.Utils;
import com.example.habit.common.enums.ErrorCode;
import com.example.habit.dto.UserDto;
import com.example.habit.dto.LoginDto;
import com.example.habit.entity.User;
import com.example.habit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    @Operation(summary = "用户注册")
    @ApiResponse(description = "返回用户信息")
    public SaResult register(@RequestBody UserDto info) {
        log.debug(String.format("%s: %s", Utils.getFun(), info));
        User user = userService.register(info);
        return SaResult.data(user);
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    @ApiResponse(description = "返回Token信息")
    public SaResult doLogin(@RequestBody LoginDto login) {
        log.debug(String.format("%s: %s", Utils.getFun(), login));
        SaTokenInfo tokenInfo = userService.doLogin(login);
        System.out.println(tokenInfo);
        return SaResult.data(tokenInfo);
    }

    @GetMapping("logout")
    @Operation(summary = "用户注销")
    @ApiResponse(description = "返回ok")
    public SaResult doLogout() {
        log.debug(String.format("%s: %s", Utils.getFun(), StpUtil.getLoginId()));
        userService.doLogout();
        return SaResult.ok();
    }

    @PutMapping("update")
    @Operation(summary = "修改个人信息")
    @ApiResponse(description = "返回新的个人信息")
    public SaResult update(@RequestBody User user) {
        log.debug(String.format("%s: %s", Utils.getFun(), user));
        userService.updateById(user);
        return SaResult.data(user);
    }

    @GetMapping("signin")
    @Operation(summary = "签到")
    @ApiResponse(description = "返回签到积分")
    public SaResult signIn() {
        Long userId = StpUtil.getLoginIdAsLong();
        log.debug(StrUtil.format("{}> userId: {}", Utils.getLine(), userId));
        User user = userService.signIn(userId);
        return SaResult.data(user.getPoints());
    }

    @GetMapping("covert")
    @Operation(summary = "积分兑换会员")
    @ApiResponse(description = "返回会员过期时间")
    public SaResult covert(@RequestParam int count) {
        Utils.iAssert(count > 0, ErrorCode.POINTS_LACK);

        Long userId = StpUtil.getLoginIdAsLong();
        log.debug(StrUtil.format("{}> userId: {}", Utils.getLine(), userId));

        User user = userService.covert(count);
        return SaResult.data(user.getVipTime());
    }
}
