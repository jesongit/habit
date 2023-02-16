package com.example.habit.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.example.habit.dto.UserDto;
import com.example.habit.dto.LoginDto;
import com.example.habit.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author posase
 * @since 2023-02-14
 */
public interface UserService extends IService<User> {

    User register(UserDto info);

    SaTokenInfo doLogin(LoginDto login);

    void doLogout();

    User signIn(Long userId);

    User covert(int count);
}
