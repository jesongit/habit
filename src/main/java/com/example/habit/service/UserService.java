package com.example.habit.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.example.habit.dto.UserInfo;
import com.example.habit.dto.UserLogin;
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

    User register(UserInfo info);

    SaTokenInfo doLogin(UserLogin login);

    void doLogout();
}
